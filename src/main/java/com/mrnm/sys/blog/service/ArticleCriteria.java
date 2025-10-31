package com.mrnm.sys.blog.service;

import java.util.Date;
import java.util.List;

public class ArticleCriteria {
    private Date date;
    private List<String> tags;

    public ArticleCriteria() {}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
