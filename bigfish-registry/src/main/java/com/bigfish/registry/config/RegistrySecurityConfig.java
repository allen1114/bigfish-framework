package com.bigfish.registry.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(RegistrySecurityProperties.class)
@ConditionalOnProperty(prefix = "bigfish.security.registry", name = "username")
public class RegistrySecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String NOOP_PASSWORD_PREFIX = "{noop}";
    RegistrySecurityProperties securityProperties;

    public RegistrySecurityConfig(RegistrySecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(securityProperties.getUsername())
                .password(NOOP_PASSWORD_PREFIX + securityProperties.getPassword()).roles("actuator");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/eureka/**").csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }
}
