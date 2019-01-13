package com.leo.cloud.controller;


import com.leo.cloud.entity.Dept;
import com.leo.cloud.service.DeptClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class ConsumerController {

    @Autowired
    private DeptClientService service;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
        Dept dept =  this.service.get(id);
        return dept;
    }

    @RequestMapping("/consumer/dept/callable/{id}")
    public Callable<Dept> callable(@PathVariable("id") Long id) {
        logger.info("外部线程：" + Thread.currentThread().getName());
        return new Callable<Dept>() {

            @Override
            public Dept call() throws Exception {
                logger.info("内部线程：" + Thread.currentThread().getName());

                Dept dept = service.get(id);
                return dept;

            }
        };
    }


    @RequestMapping(value = "/consumer/dept/async_get/{id}")
    public Dept aget(@PathVariable("id") Long id){
        Dept dept = service.get(id);
        return dept;
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list()
    {
        return this.service.list();
    }

    @RequestMapping(value = "/consumer/dept/add")
    public Object add(Dept dept)
    {
        return this.service.add(dept);
    }

}
