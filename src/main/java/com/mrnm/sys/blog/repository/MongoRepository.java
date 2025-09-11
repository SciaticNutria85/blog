package com.mrnm.sys.blog.repository;

import com.mrnm.sys.blog.documents.Article;
import org.springframework.data.repository.CrudRepository;

public interface MongoRepository extends CrudRepository<Article, String> {
}
