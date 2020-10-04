package com.sparanzza.website.server;

import com.google.inject.ImplementedBy;
import com.sparanzza.website.server.impl.HelloServiceImpl;

@ImplementedBy(HelloServiceImpl.class)
public interface HelloService {
    String sayHello();
}
