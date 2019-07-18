package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;


    /**
     * 查询所有标签
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findLabelAll(){
        return  new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    /**
    *@Description:
    *@param: 
    *@retun:  * @param null
    *@Autoor: Nick Chen 
    *@Data: 15:38 2019/7/18
    */
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findLabelById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }

    /**
     * 新增
     * @param label
     * @return
     */
    @RequestMapping(method =RequestMethod.POST)
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"增加成功");
    }
    @RequestMapping(value ="/{id}" ,  method = RequestMethod.POST)
    public Result update(@RequestBody Label label,@PathVariable String id){
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"更新成功");
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        labelService.deletedById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
