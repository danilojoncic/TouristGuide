package com.example.touristguide.dto;

public class CreateCommentDto {
    private String author_name;
    private String text;

    public CreateCommentDto(String author_name, String text) {
        this.author_name = author_name;
        this.text = text;
    }

    public CreateCommentDto() {
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
}
