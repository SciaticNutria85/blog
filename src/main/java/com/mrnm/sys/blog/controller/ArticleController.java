package com.mrnm.sys.blog.controller;

import com.mrnm.sys.blog.commons.ArticleException;
import com.mrnm.sys.blog.documents.Article;
import com.mrnm.sys.blog.repository.ArticleRepository;
import com.mrnm.sys.blog.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleWritingService articleWritingService;

    private final ArticleReadingService articleReadingService;

    private final ArticleDeletionService articleDeletionService;

    private final ArticleQueryService articleQueryService;

    public ArticleController(ArticleWritingService articleWritingService, ArticleReadingService articleReadingService, ArticleDeletionService articleDeletionService, ArticleQueryService articleQueryService) {
        this.articleWritingService = articleWritingService;
        this.articleReadingService = articleReadingService;
        this.articleDeletionService = articleDeletionService;
        this.articleQueryService = articleQueryService;
    }

    @PostMapping
    public ResponseEntity<Article> post(
            @RequestBody Article article
    ) throws ArticleException {
        var createdArticle = articleWritingService.create(article);
        return ResponseEntity.ok(createdArticle);
    }

    @PutMapping
    public ResponseEntity<Article> put(
            @RequestBody Article article
    ) throws ArticleException {
        var updatedArticle = articleWritingService.update(article);
        return ResponseEntity.ok(updatedArticle);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<Article> get(
            @PathVariable String articleId
    ) throws ArticleException {
        var articleFound = articleReadingService.read(articleId);
        return ResponseEntity.ok(articleFound);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        var articles = articleReadingService.readAll();
        return ResponseEntity.ok(articles);
    }


    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> delete(
            @PathVariable String articleId
    ) throws ArticleException {
        articleDeletionService.delete(articleId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/query")
    public ResponseEntity<List<Article>> query(
                @RequestBody ArticleCriteria articleCriteria
            ) {
        return ResponseEntity.ok(articleQueryService.queryObject(articleCriteria));
    }
}
