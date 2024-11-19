package com.tacticals.publisherastra.config;

import com.datastax.oss.driver.api.core.session.Session;
import org.apache.catalina.Cluster;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;

//@Configuration
//@Import(CassandraDataAutoConfiguration.class)
//@EnableConfigurationProperties(CassandraProperties.class)
public class CassandraCustomConfiguration {
//
//    @Bean
//    Cluster cluster(CassandraProperties properties, ObjectProvider<ClusterBuilderCustomizer> builderCustomizers) {
//        CassandraAutoConfiguration cassandraAutoConfiguration = new CassandraAutoConfiguration(properties, builderCustomizers);
//        Cluster cluster = cassandraAutoConfiguration.cassandraCluster();
//        createKeyspace(cluster, getSimpleKeyspaceCreation());
//        return cluster;
//    }
//
//    private CreateKeyspaceSpecification getSimpleKeyspaceCreation() {
//        return CreateKeyspaceSpecification.createKeyspace("foo").ifNotExists(); // simplified sample
//    }
//
//    private void createKeyspace(Cluster cluster, CreateKeyspaceSpecification keyspaceCreation) {
//        try (Session session = cluster.connect()) {
//            CqlTemplate template = new CqlTemplate(session);
//            template.execute(CreateKeyspaceCqlGenerator.toCql(keyspaceCreation));
//        }
//    }
}
