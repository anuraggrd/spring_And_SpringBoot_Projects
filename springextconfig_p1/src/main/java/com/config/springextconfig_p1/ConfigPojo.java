package com.config.springextconfig_p1;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "build")
@Data
public class ConfigPojo {
   private String version;
    private String buidNum;
    private String packagedetails;
}
