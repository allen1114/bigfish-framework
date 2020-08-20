package com.bigfish.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(SwaggerSecurityProperties.class)
@ConditionalOnProperty(prefix = "bigfish.security.swagger", name = "username")
@Order(-2)
public class SwaggerSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String NOOP_PASSWORD_PREFIX = "{noop}";
    final SwaggerSecurityProperties swaggerSecurityProperties;

    public SwaggerSecurityConfig(SwaggerSecurityProperties swaggerSecurityProperties) {
        this.swaggerSecurityProperties = swaggerSecurityProperties;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(swaggerSecurityProperties.getUsername())
                .password(NOOP_PASSWORD_PREFIX + swaggerSecurityProperties.getPassword()).roles("swagger");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("swagger-ui.html", "/webjars/**").and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

}
