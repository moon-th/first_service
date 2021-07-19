package com.example.first_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {

    Environment evn;

    @Autowired
    public FirstServiceController(Environment evn){
        this.evn = evn;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First Service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header){

        log.info("header : {} " , header);
        return "Hello World in First Service." ;
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server Port = {}",request.getServerPort());

        return String.format("Hi there. This is a message from First Service %s"
                ,evn.getProperty("local.server.port"));
    }
}