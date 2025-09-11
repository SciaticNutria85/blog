package com.mrnm.sys.blog.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "articles")
public class Article {

    @Id
    private String id;

    private String title;

    private String body;

    private String category;

    private Integer likes;

    private List<String> tags;

    private Date date;

    public Article() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Article setArticleValues(Article article) {
        this.title = article.getTitle();
        this.body = article.getBody();;
        this.category = article.getCategory();
        this.likes = article.getLikes();
        this.date = new Date();
        this.tags = article.getTags();
        return this;
    }
}
