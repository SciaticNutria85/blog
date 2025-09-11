package com.mrnm.sys.blog.service;

import com.mrnm.sys.blog.commons.ArticleException;
import com.mrnm.sys.blog.documents.Article;
import com.mrnm.sys.blog.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleReadingService implements IRead<Article, String>{

    private final ArticleRepository articleRepository;

    public ArticleReadingService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article read(String id) throws ArticleException {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleException("Article Not Found"));
    }

    @Override
    public List<Article> readAll() {
        var articleIterator = articleRepository.findAll().iterator();
        List<Article> articles = new ArrayList<>();
        while (articleIterator.hasNext()) {
            var article = articleIterator.next();
            articles.add(article);
        }
        return articles;
    }

}
