package com.tacticals.publisherastra.config;

import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.nio.file.Path;

@Configuration
@EnableConfigurationProperties(DataStaxAstraProperties.class)
//@EnableCassandraRepositories(basePackages = "com.tacticals.publisherastra.repository")
public class DataStaxAstraConn {

    /**
     * This is necessary to have the Spring Boot app use the Astra secure bundle
     * to connect to the database
     */
    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder
                .withCloudSecureConnectBundle(bundle)
                .withKeyspace("main")
                .withAuthCredentials("HOZQXZFAkaOHMImxPUrDsXoa",
                        "ybIxp7B7PzOHK4oPX0QR-tKuAirucY3QQCl5QfgiDg_MTccsoWw6UHnH9h_26YR0xcI,W9u.DTciuq+74.ubmTlZmu7yxYwG0pLxXg.Tu_fUkEDAelJgu7C_K8tU,e_m");

    }

}
