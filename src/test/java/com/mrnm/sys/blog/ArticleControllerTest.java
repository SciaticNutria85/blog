package com.mrnm.sys.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest // Focused on the web layer
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // 1. Return list of articles (with optional filters)
    @Test
    void shouldReturnListOfArticles() throws Exception {
        mockMvc.perform(get("/articles")
                        .param("tag", "spring")
                        .param("publishedAfter", LocalDate.now().minusDays(10).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // 2. Return a single article by ID
    @Test
    void shouldReturnSingleArticleById() throws Exception {
        mockMvc.perform(get("/articles/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists());
    }

    // 3. Create a new article
    @Test
    void shouldCreateNewArticle() throws Exception {
        Map<String, Object> newArticle = Map.of(
                "title", "My First Article",
                "content", "Hello world with TDD",
                "tags", List.of("spring", "tdd")
        );

        mockMvc.perform(post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newArticle)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("My First Article"));
    }

    // 4. Delete an article by ID
    @Test
    void shouldDeleteArticleById() throws Exception {
        mockMvc.perform(delete("/articles/{id}", 1))
                .andExpect(status().isNoContent());
    }

    // 5. Update an article by ID
    @Test
    void shouldUpdateArticleById() throws Exception {
        Map<String, Object> updatedArticle = Map.of(
                "title", "Updated Article",
                "content", "Updated content here"
        );

        mockMvc.perform(put("/articles/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedArticle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Article"))
                .andExpect(jsonPath("$.content").value("Updated content here"));
    }
}

