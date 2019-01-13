package com.leo.cloud.controller;

import com.leo.cloud.entity.Dept;
import com.leo.cloud.service.IDeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Future;

@RestController
public class DeptController {

    @Autowired
    private IDeptService service;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return service.addDept(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
//    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id){

        Dept dept = service.findById(id);
        if(dept==null){
            throw  new RuntimeException("该ID不存在");
        }
        return dept;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id).setDname("该ID没有对应信息，null  -> @HystrixCommand")
                .setDb_source("not in database");
    }

    //异步的执行
//    @HystrixCommand(fallbackMethod = "asyncGetError")
    @RequestMapping(value = "/dept/async_get/{id}", method = RequestMethod.GET)
    public Future<Dept> getUserName(final Long id) {
        return new AsyncResult<Dept>() {
            @Override
            public Dept invoke() {
                Dept dept = service.findById(id);
                if(dept==null){
                    throw  new RuntimeException("该ID不存在");
                }
                return dept;
            }
        };
    }

    public Dept asyncGetError(Long id) {
        return new Dept().setDeptno(id).setDname("async get 该ID没有对应信息，null  -> @HystrixCommand")
                .setDb_source("not in database");
    }





    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept > list(){
        return service.findAll();
    }

    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery()
    {
        List<String> list = client.getServices();
        System.out.println("**********" + list);

        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }
}
