package com.tacticals.publisherastra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
//@EnableCassandraRepositories(basePackages = "com.tacticals.publisherastra.repository")
public class PublisherAstraApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublisherAstraApplication.class, args);
	}

}
