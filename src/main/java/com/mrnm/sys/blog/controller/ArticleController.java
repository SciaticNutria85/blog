package com.mrnm.sys.blog.controller;

import com.mrnm.sys.blog.commons.ArticleException;
import com.mrnm.sys.blog.documents.Article;
import com.mrnm.sys.blog.repository.ArticleRepository;
import com.mrnm.sys.blog.service.ArticleReadingService;
import com.mrnm.sys.blog.service.ArticleWritingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleWritingService articleWritingService;

    private final ArticleReadingService articleReadingService;

    public ArticleController(ArticleWritingService articleWritingService, ArticleReadingService articleReadingService) {
        this.articleWritingService = articleWritingService;
        this.articleReadingService = articleReadingService;
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

}
