package com.mrnm.sys.blog.controller;

import com.mrnm.sys.blog.commons.ArticleException;
import com.mrnm.sys.blog.documents.Article;
import com.mrnm.sys.blog.repository.ArticleRepository;
import com.mrnm.sys.blog.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<Article> post(
            @RequestBody Article article
    ) throws ArticleException {
        var createdArticle = articleService.create(article);
        return ResponseEntity.ok(createdArticle);
    }

    @PutMapping
    public ResponseEntity<Article> put(
            @RequestBody Article article
    ) throws ArticleException {
        var updatedArticle = articleService.update(article);
        return ResponseEntity.ok(updatedArticle);
    }

}
