package com.example.touristguide.dto;

public class CreateActivityDTO {
    private String tag;

    public CreateActivityDTO(String tag) {
        this.tag = tag;
    }

    public CreateActivityDTO() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
