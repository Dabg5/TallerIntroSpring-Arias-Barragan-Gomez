package com.example.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebListener
public class SpringContextListener implements ServletContextListener {
    private AnnotationConfigApplicationContext context;

    @Override
    public void contextInitialized(ServletContextEvent event) {

        context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        event.getServletContext().setAttribute(
                "springContext",
                context
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        context.close();
    }
}
