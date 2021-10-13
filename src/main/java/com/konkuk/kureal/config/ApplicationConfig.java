package com.konkuk.kureal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.konkuk.kureal.dao", "com.konkuk.kureal.service", "com.konkuk.kureal.controller"})
@Import({ DBConfig.class })
public class ApplicationConfig {

}
