package com.bigfish.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(ActuatorSecurityProperties.class)
@ConditionalOnProperty(prefix = "bigfish.security.actuator", name = "username")
@Order(-1)
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String NOOP_PASSWORD_PREFIX = "{noop}";
    final ActuatorSecurityProperties securityProperties;

    public ActuatorSecurityConfig(ActuatorSecurityProperties securityProperties) {
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
        http.antMatcher("/actuator/**")
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

}
