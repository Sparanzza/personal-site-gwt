package com.sparanzza.website.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationBootstrapper extends GuiceServletContextListener {

    Injector injector;
    ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent ev) {
        super.contextInitialized(ev);
        System.out.println("initialize servlet");
        servletContext = ev.getServletContext();

    }

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                bind(HelloController.class);
                serve("/api/*").with(GuiceContainer.class);
            }
        });
        return injector;
    }
}
