package com.bigfish.security.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ActuatorSecurityConfig.class})
public @interface EnableActuatorSecurity {
}
