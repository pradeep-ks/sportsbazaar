package com.sportsbazaar.persistence.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.sportsbazaar")
@Profile("dev")
@PropertySource("file:///${user.home}/.sportsbazaar/application-dev.properties")
public class DevConfig {

}
