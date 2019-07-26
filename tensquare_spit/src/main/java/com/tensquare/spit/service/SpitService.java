package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @Autoer: Nick Chen
 * @Date: 2019/7/26.11 50
 * @Description:
 */
@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;



    /**
     * 查询所有
     * @return
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    /**
     * 新增
     * @param spit
     */
    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setThumbup(0);
        spit.setShare(0);
        spit.setState("0"); //状态
        spit.setComment(0); //回复数
        spitDao.insert(spit);
        //如果当前添加的吐槽有父节点,那么父节点的回复数也要加1
        if(spit.getParentid()!=null&&!"".equals(spit.getParentid())){
            Query query=new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update=new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
    }

    /**
     * 修改
     * @param spit
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 删除
     * @param spitid
     */
    public void deleteById(String spitid){
        spitDao.deleteById(spitid);
    }

    public Page<Spit> findByParentId(String parentid,int page,int size){
        PageRequest pageable = PageRequest.of(page - 1, size);
       return spitDao.findByParentid(parentid, pageable);
    }

    public void thumbup(String spitid){
        //方式一，效率问题
//        Spit spit = spitDao.findById(spitid).get();
//        spit.setThumbup((spit.getThumbup()==null ? 0:spit.getThumbup()) + 1);
//        spitDao.save(spit);

        //方式二 使用原生命令来自增 db.spit.update({_id="1"},{$inc:{thumpup:NumberInt(1}})
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(spitid)); //查询的位置 即ID的位置
        Update update=new Update();
        update.inc("thumpup",1); //更新的方式
        mongoTemplate.updateFirst(query,update,"split"); //指定操作哪个集合


    }


}
