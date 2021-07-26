package com.it.springcloud.controller;

import com.it.springcloud.pojo.Dept;
import com.it.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//提供Restful服务
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);
        if (dept == null) {
            throw new RuntimeException("id=>" + id + ",不存在该用户，或者信息无法找到");
//            throw new RuntimeException("Fail");
        }
        return dept;
    }

    //get()备选方法
    public Dept hystrixGet(@PathVariable("id") Long id) {
        return new Dept()
                .setDeptno(id)
                .setDname("id=>" + id + "没有对应信息， null--@Hystrix")
                .setDb_source("no this database in MySQL");
    }

    @GetMapping("dept/list")
    public List<Dept> queryAll() {
        return deptService.queryAll();
    }

    @GetMapping("/dept/discovery")
    public Object discovery() {
        //获取微服务的清单
        List<String> services = client.getServices();
        System.out.println("discovery=>service:" + services);
        // 得到一个具体的微服务信息，得到具体的微服务 id， applicationName
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t" + //主机名
                    instance.getPort() + "\t" + //端口号
                    instance.getUri() + "\t" + //url
                    instance.getServiceId() //服务ID
            );
        }
        return this.client;
    }
}
