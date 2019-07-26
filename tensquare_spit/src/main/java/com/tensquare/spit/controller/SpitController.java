package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Autoer: Nick Chen
 * @Date: 2019/7/26.12 05
 * @Description:
 */
@RestController
@RequestMapping("/spit")
@CrossOrigin
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    /**
     * 根据id查询
     * @param spitid
     * @return
     */
    @RequestMapping(value = "/{spitid}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitid){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(spitid));
    }

    /**
     * 新增成功
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"新增成功");
    }

    /**
     * 修改
     * @param spit
     * @return
     */
    @RequestMapping(value = "/{spitid}",method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit){
        spitService.update(spit);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    /**
     * 删除
     * @param spitid
     * @return
     */
    @RequestMapping(value = "/{spitid}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitid){
        spitService.deleteById(spitid);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParentByID(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> pageData = spitService.findByParentId(parentid, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageData.getTotalElements(),pageData.getContent()));
    }
    @RequestMapping(value = "/thumbup/{spitid}",method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitid){
        //判断当前用户是否已经点赞,
        String userid="111";
        if(redisTemplate.opsForValue().get("thumbup_"+userid)!=null){
            return new Result(false,StatusCode.REPERROR,"不能重复点赞");
        }
        spitService.thumbup(spitid);
        redisTemplate.opsForValue().set("thumbup_"+userid,1);
        return new Result(true,StatusCode.OK,"点赞成功");
    }

}
