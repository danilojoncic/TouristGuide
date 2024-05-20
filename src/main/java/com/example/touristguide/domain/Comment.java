package com.example.touristguide.domain;

import java.util.Date;

public class Comment {
    private int comment_id;
    private String author_name;
    private String text;
    private Date created_at;
    private int article_id;

    public Comment() {
    }

    public Comment(int comment_id, String author_name, String text, Date created_at, int article_id) {
        this.comment_id = comment_id;
        this.author_name = author_name;
        this.text = text;
        this.created_at = created_at;
        this.article_id = article_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }
}
