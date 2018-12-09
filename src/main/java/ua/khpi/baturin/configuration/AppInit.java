package ua.khpi.baturin.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import ua.khpi.baturin.configuration.application.WebConfig;
import ua.khpi.baturin.configuration.security.WebSecurityConfig;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { WebConfig.class, WebSecurityConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}