package com.example.security.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true)
public class GlobalMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    private final RoleHierarchy roleHierarchy;
    private final ApplicationContext applicationContext;

    public GlobalMethodSecurityConfig(RoleHierarchy roleHierarchy, ApplicationContext applicationContext) {
        this.roleHierarchy = roleHierarchy;
        this.applicationContext = applicationContext;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }
}
