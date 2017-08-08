package com.kiegeland.polls;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "resource")
public interface ResourceRepository extends MongoRepository<Question, String> {

	Question findByUrl(String url);

}
