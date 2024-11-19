package com.tacticals.publisherastra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "datadump.location")
public class DatadumbConfig {

        private String author;
        private String book;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String location) {
            this.author = location;
        }

        public String getBook() {
            return book;
        }
        public void setBook(String location) {
            this.book = location;
        }
}
