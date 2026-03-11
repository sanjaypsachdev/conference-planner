package com.sanjaypsachdev.conferenceplanner.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@NoArgsConstructor
@Setter
public class AppProperties {

    private String name;
    private boolean seeding;
}
