package com.example.comento.domain;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@DynamicInsert // insert 시 null 인 필드 제외
@DynamicUpdate // update 시 null 인 필드 제외
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String writer;

//    @Column
//    private List comments;

    @Column
    private String title;


    @Column
    private String content;

    @Column(columnDefinition = "varchar(225) default 'community'")
    private String type;

    @Column(columnDefinition = "int default '0'")
    private int views;

    @Column(columnDefinition = "int default '0'")
    private int likes;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(columnDefinition = "timestamp")
    private Timestamp updateAt;

    public Post() {
    }

    public Post(Long id, String writer, String title, String content, String type, int views, int likes, Timestamp createdAt, Timestamp updateAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.type = type;
        this.views = views;
        this.likes = likes;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
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

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}
