package com.mrnm.sys.blog.service;

import com.mrnm.sys.blog.commons.ArticleException;
import com.mrnm.sys.blog.documents.Article;
import com.mrnm.sys.blog.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleDeletionService implements IDelete<String> {

    private final ArticleRepository articleRepository;

    public ArticleDeletionService(
            ArticleRepository articleRepository
    ) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void delete(String deletedEntityId) throws ArticleException {
        var articleFound = articleRepository.findById(deletedEntityId).orElseThrow(() -> new ArticleException("Article not found"));
        articleRepository.delete(articleFound);
    }
}
