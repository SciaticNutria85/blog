package com.mrnm.sys.blog.repository;

import com.mrnm.sys.blog.documents.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MongoRepositoryTest {

    @Autowired
    private MongoRepository mongoRepository;

    private Article article;

    @BeforeEach
    void setUp() {
        mongoRepository.deleteAll(); // limpiar antes de cada test
        article = new Article();
        article.setId("1");
        article.setTitle("Primer artículo");
        //article.setContent("Contenido del artículo de prueba");
        article.setBody("Contenido del artículo de prueba");
    }

    @Test
    void shouldSaveAndFindArticleById() {
        // Guardar artículo
        mongoRepository.save(article);

        // Recuperar
        Optional<Article> found = mongoRepository.findById("1");

        // Verificaciones
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Primer artículo");
        assertThat(found.get().getBody()).isEqualTo("Contenido del artículo de prueba");
    }

    @Test
    void shouldDeleteArticle() {
        mongoRepository.save(article);
        mongoRepository.deleteById("1");

        Optional<Article> found = mongoRepository.findById("1");

        assertThat(found).isEmpty();
    }

    @Test
    void shouldReturnAllArticles() {
        Article another = new Article();
        another.setId("2");
        another.setTitle("Segundo artículo");
        another.setBody("Contenido extra");

        mongoRepository.save(article);
        mongoRepository.save(another);

        Iterable<Article> all = mongoRepository.findAll();

        assertThat(all).hasSize(2);
    }
}

