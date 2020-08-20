package com.bigfish.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bigfish.security.swagger")
@Data
public class SwaggerSecurityProperties {
    private String username;
    private String password;
}
