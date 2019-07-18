package com.tensquare.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autoer: Nick Chen
 * @Date: 2019/7/18.15 31
 * @Description:
 */
@RestController
public class HelloController
{
    
         @RequestMapping("/hello")
        public String sayHello(String name,String id){
        /**
        *@Description:
        *@param: [name, id]
        *@retun: 
        *@Autoor: Nick Chen 
        *@Data: 15:40 2019/7/18
        */
        
        return "hello,word,this is  "+name + id;
        }
}
