package com.example.comento.controller.dto;

import com.sun.istack.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
// setter가 없는 이유
public class PostDTO {
    @NotNull
    private String title;
    @NotNull
    private String writer;
    @NotNull
    private String content;
//    private List comments;
    private String type;
    private Long view;
    private Long likes;
    // 게시글을 읽을 때 작성시각이나 수정시각을 확인하고싶을 수 있기 때문에 getter가 필요하다
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
