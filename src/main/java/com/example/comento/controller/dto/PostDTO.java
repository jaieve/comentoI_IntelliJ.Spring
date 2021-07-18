package com.example.comento.controller.dto;

import java.sql.Timestamp;

public class PostDTO {
    private String title;
    private String writer;
    private String content;
//    private List comments;
    private String type;
    private int view;
    private int likes;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public PostDTO() {
    }

    public PostDTO(String title, String writer, String content, String type, int view, int likes, Timestamp createdAt, Timestamp updatedAt) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.type = type;
        this.view = view;
        this.likes = likes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
