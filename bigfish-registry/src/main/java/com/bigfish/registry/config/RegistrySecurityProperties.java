package com.bigfish.registry.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bigfish.security.registry")
@Data
public class RegistrySecurityProperties {
    private String username;
    private String password;
}
