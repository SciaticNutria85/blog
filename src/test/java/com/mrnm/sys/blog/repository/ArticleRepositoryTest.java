package com.mrnm.sys.blog.repository;

import com.mrnm.sys.blog.documents.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    private Article article;

    @BeforeEach
    void setUp() {
        articleRepository.deleteAll(); // limpiar antes de cada test
        article = new Article();
        article.setId("1");
        article.setTitle("Primer artículo");
        //article.setContent("Contenido del artículo de prueba");
        article.setBody("Contenido del artículo de prueba");
    }

    @Test
    void shouldSaveAndFindArticleById() {
        // Guardar artículo
        articleRepository.save(article);

        // Recuperar
        Optional<Article> found = articleRepository.findById("1");

        // Verificaciones
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Primer artículo");
        assertThat(found.get().getBody()).isEqualTo("Contenido del artículo de prueba");
    }

    @Test
    void shouldDeleteArticle() {
        articleRepository.save(article);
        articleRepository.deleteById("1");

        Optional<Article> found = articleRepository.findById("1");

        assertThat(found).isEmpty();
    }

    @Test
    void shouldReturnAllArticles() {
        Article another = new Article();
        another.setId("2");
        another.setTitle("Segundo artículo");
        another.setBody("Contenido extra");

        articleRepository.save(article);
        articleRepository.save(another);

        Iterable<Article> all = articleRepository.findAll();

        assertThat(all).hasSize(2);
    }
}

