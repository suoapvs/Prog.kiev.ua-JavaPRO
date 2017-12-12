package com.photos;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public final class WebInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) {
        final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(servletContext);
        final Servlet servlet = new DispatcherServlet(ctx);
        final ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", servlet);
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }
}
