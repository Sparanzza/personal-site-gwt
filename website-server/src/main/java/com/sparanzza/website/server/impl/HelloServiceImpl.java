package com.sparanzza.website.server.impl;

import com.sparanzza.website.server.HelloService;


public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "Hello Google";
    }
}
