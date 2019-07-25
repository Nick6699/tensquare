package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据ID查询
     *
     * @param id
     */
    public Label findById(String id) {
        Label label = new Label();
        return labelDao.findById(id).get();
    }

    /**
     * 添加标签
     * @param label
     */
    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * 更新操作
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    };

    /**
     * 根据ID删除
     * @param id
     */
        public void deleteById(String id) {
            labelDao.deleteById(id);
        };


    public List<Label> findSearch(Label label) {

       return labelDao.findAll(new Specification<Label>() {
           /**
            *
            * @param root 根对象，也就是把条件封装到哪个对象，where 类名=label.getid
            * @param query  封装的关键字  group by order by,如果直接返回,表示不需要作何条件
            * @param cb  封装条件对象
            * @return
            */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                ArrayList<Predicate> list = new ArrayList<>();
                if (label.getLabelname()!=null &&! "".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//相当于 where labelname like '%小明%'
                    list.add(predicate);
                }
                if (label.getState()!=null &&! "".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class),  label.getState() );//相当于 where labelname like '%小明%'
                    list.add(predicate);
                }
                if (label.getRecommend()!=null &&! "".equals(label.getRecommend())){
                    Predicate predicate = cb.equal(root.get("recommend").as(String.class),  label.getRecommend() );//相当于 where labelname like '%小明%'
                    list.add(predicate);
                }
                //新建一个集合存放所有条件
                Predicate[] parr = new Predicate[list.size()];
                //把list直接转成数组
                list.toArray(parr);
                return cb.and(parr); //相当条件连接的 AND
            }
        });
    }


    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是把条件封装到哪个对象，where 类名=label.getid
             * @param query  封装的关键字  group by order by,如果直接返回,表示不需要作何条件
             * @param cb  封装条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                ArrayList<Predicate> list = new ArrayList<>();
                if (label.getLabelname()!=null &&! "".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//相当于 where labelname like '%小明%'
                    list.add(predicate);
                }
                if (label.getState()!=null &&! "".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class),  label.getState() );//相当于 where labelname like '%小明%'
                    list.add(predicate);
                }
                //新建一个集合存放所有条件
                Predicate[] parr = new Predicate[list.size()];
                //把list直接转成数组
                list.toArray(parr);
                return cb.and(parr); //相当条件连接的 AND
            }
        },pageable);
    }

}

