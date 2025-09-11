package com.mrnm.sys.blog.service;

import com.mrnm.sys.blog.commons.ArticleException;
import com.mrnm.sys.blog.documents.Article;
import com.mrnm.sys.blog.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
public class ArticleService implements IUpdate<Article>, ICreate<Article> {

    private final ArticleRepository articleRepository;

    public ArticleService(
            ArticleRepository articleRepository
    ) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    @Override
    public Article update(Article updatedEntity) throws ArticleException {
        Supplier<ArticleException> articleExceptionSupplier = () -> new ArticleException("Article not found");
        var articleFound = articleRepository.findById(updatedEntity.getId()).orElseThrow(articleExceptionSupplier);
            articleFound.setArticleValues(updatedEntity);
        return articleRepository.save(articleFound);
    }

    @Override
    public Article create(Article createdEntity) throws ArticleException {
        if (createdEntity == null) {
            throw new ArticleException("Article must not be null");
        }
        return articleRepository.save(createdEntity);
    }
}
