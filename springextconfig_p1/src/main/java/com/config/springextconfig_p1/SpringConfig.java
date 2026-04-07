package com.config.springextconfig_p1;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RefreshScope
public class SpringConfig {
    private final ConfigPojo pojo;

    @Value("${framworkdetails.java_version}")
     String java_version;
    @Value("${framworkdetails.springbootversion:987}")
     String springbootversion;

    @GetMapping("/details")
    public String getConfigDetails(){
    return "build version " + pojo.getVersion()  + " build number :" + pojo.getBuidNum() + " package  :" + pojo.getPackagedetails();
    }

    @GetMapping("/framworkdetails")
    public String getframworkDetails(){
        return "java version " + java_version  + " string boot version :" +springbootversion ;
    }

}
