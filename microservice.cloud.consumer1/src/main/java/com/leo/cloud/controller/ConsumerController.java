package com.leo.cloud.controller;

import com.leo.cloud.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class ConsumerController {

    private static final String REST_URL_PPREFIX = "http://MICROSERVICECLOUD-DEPT";

    @Autowired
//    @LoadBalanced
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PPREFIX+"/dept/add",dept, Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        try {
            Dept dept = restTemplate.getForObject(REST_URL_PPREFIX + "/dept/get/" + id, Dept.class);
            return dept;
        }catch(Exception err){
            System.out.println(err);
            return null;
        }
    }

    @RequestMapping("/consumer/dept/callable/{id}")
    public Callable<Dept> callable(@PathVariable("id") Long id) {
//        logger.info("外部线程：" + Thread.currentThread().getName());
        return new Callable<Dept>() {

            @Override
            public Dept call() throws Exception {
                Dept dept = restTemplate.getForObject(REST_URL_PPREFIX + "/dept/async_get/" + id, Dept.class);
                return dept;
//                logger.info("内部线程：" + Thread.currentThread().getName());
            }
        };
    }


    @RequestMapping(value = "/consumer/dept/async_get/{id}")
    public Dept aget(@PathVariable("id") Long id){
        try {
            Dept dept = restTemplate.getForObject(REST_URL_PPREFIX + "/dept/async_get/" + id, Dept.class);
            return dept;
        }catch(Exception err){
            System.out.println(err);
            return null;
        }
    }

    @RequestMapping(path = "/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PPREFIX+"/dept/list", List.class);
    }

    @RequestMapping(path = "/consumer/dept/discovery")
    public Object discovery(){
        return restTemplate.getForObject(REST_URL_PPREFIX+"/dept/discovery", Object.class);
    }
}
