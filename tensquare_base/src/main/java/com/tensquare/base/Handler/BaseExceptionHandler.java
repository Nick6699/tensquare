package com.tensquare.base.Handler;

import ch.qos.logback.core.status.Status;
import entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Autoer: Nick Chen
 * @Date: 2019/7/23.10 05
 * @Description: 异常类处理
 */
@ControllerAdvice
public class BaseExceptionHandler{
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, Status.ERROR,"查询失败",e.getMessage());
    }
}
