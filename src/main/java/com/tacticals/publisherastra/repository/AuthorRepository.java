package com.tacticals.publisherastra.repository;

import com.tacticals.publisherastra.model.Author;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface AuthorRepository extends CassandraRepository<Author, String> {

}
