package com.tacticals.publisherastra.repository;

import com.tacticals.publisherastra.model.Book;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CassandraRepository<Book, String> {

    //@Projection(name = "product-name-and-price", types = { Order.class })
    //https://github.com/DataStax-Examples/spring-data-starter/blob/master/src/main/java/com/datastax/examples/order/ProductNameAndPrice.java

}
