package com.bigfish.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bigfish.security.actuator")
@Data
public class ActuatorSecurityProperties {
    private String username;
    private String password;
}
