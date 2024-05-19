package com.example.touristguide.domain;

public class Article {
    private int article_id;
    private String title;
    private String text;
    //za created at uvijek cemo stavljati null pri insertu
    private int visit_count;
    private int autor_id;
    private int destination_id;

    public Article(int article_id, String title, String text, int visit_count, int autor_id, int destination_id) {
        this.article_id = article_id;
        this.title = title;
        this.text = text;
        this.visit_count = visit_count;
        this.autor_id = autor_id;
        this.destination_id = destination_id;
    }
}
