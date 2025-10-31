package com.mrnm.sys.blog.service;

import com.mrnm.sys.blog.documents.Article;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ArticleQueryService implements IQuery<Article, ArticleCriteria> {

    private final MongoTemplate mongoTemplate;

    public ArticleQueryService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Article> queryObject(ArticleCriteria criteria) {
        boolean hasTags = criteria.getTags() != null && !criteria.getTags().isEmpty();
        boolean hasDate = criteria.getDate() != null;

        if (!hasTags && !hasDate) {
            return List.of();
        }

        Query query = new Query();

        if (hasTags)
            query.addCriteria(Criteria.where("tags").in(criteria.getTags()));

        if (hasDate) {
            LocalDate date = criteria.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            query.addCriteria(Criteria.where("date").gte(start).lte(end));
        }

        return mongoTemplate.find(query, Article.class);
    }

}
