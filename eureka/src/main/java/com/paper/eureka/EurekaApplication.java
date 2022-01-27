package com.paper.eureka;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaApplication.class).bannerMode(Banner.Mode.CONSOLE).run(args);


    }
}
