package com.tensquare.base.controller;

import com.sun.org.apache.bcel.internal.generic.LADD;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {
    @Autowired
    private LabelService labelService;


    /**
     * 查询所有标签
     *
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findLabelAll(){
        return  new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }


    /**
     * 根据ID查询
     * @param id
     *
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findLabelById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }


    /**
     * 查询 推荐标签列表
      * @return
     */
    @RequestMapping(value = "/toplist",method = RequestMethod.GET)
    public Result findLableByrecommend(){
        Label label = new Label();
        label.setRecommend("Y");
        return new Result(true, StatusCode.OK,"查询成功",labelService.findSearch(label));
    };

    /**
     * 根据有效标签列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result findLabelByState(){
        Label label = new Label();
        label.setState("1");
        return new Result(true,StatusCode.OK,"查询成功",labelService.findSearch(label));
    }

    /**
     * 添加Label
     * @param label
     */
    @RequestMapping(method =RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 更改label
     * @param label
     * @param id
     */
    @RequestMapping(value ="/{id}" ,  method = RequestMethod.PUT)
    public Result update(@RequestBody Label label,@PathVariable String id){
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    /**
     * 删除label
     * @param id
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }



    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        List<Label> list=labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"查询成功",list);
    };

    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageQuery(@RequestBody Label label,@PathVariable int page ,@PathVariable int size){//int初始值为0
        Page<Label> pageData=labelService.pageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    };


}
