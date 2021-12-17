package com.example.demo.controller;

import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/get1")
    public Object getTrees1() {
        return demoService.getTrees1();
    }

    @GetMapping("/get2")
    public Object getTrees2() {
        return demoService.getTrees2();
    }

    @GetMapping("/get3")
    public Object getTrees3() {
        return demoService.getTrees3();
    }

    @GetMapping("/getDir")
    public Object getDir() {
        return demoService.getDir();
    }

}
