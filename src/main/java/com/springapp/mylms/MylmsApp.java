package com.springapp.mylms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by bezdj on 02/02/2017.
 */

@SpringBootApplication
@EnableWebMvc
public class MylmsApp extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(MylmsApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MylmsApp.class);
    }
}