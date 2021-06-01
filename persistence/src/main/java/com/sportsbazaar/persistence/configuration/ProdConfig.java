package com.sportsbazaar.persistence.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.sportsbazaar")
@Profile("prod")
@PropertySource("file:///${user.home}/Documents/.sportsbazaar/datasource-prod.properties")
public class ProdConfig {

}
