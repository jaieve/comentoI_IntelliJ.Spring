package com.example.comento.controller;

import com.example.comento.controller.dto.PostDTO;
import com.example.comento.domain.Post;
import com.example.comento.service.PostService;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static org.assertj.core.api.Assertions.assertThat;


class RootControllerTest {

    PostService postService = new PostService();

    @Test
    void 인사() {
    }

    @Test
    void 글_읽기() {

    }

    @Test
    void createPost() {
        // given
        String title = "new title";
        String content = "new content";
        String writer = "programmer";
        PostDTO postDTO = PostDTO.builder()
                                .title(title)
                                .content(content)
                                .writer(writer)
                                .build();

        // when
        Post post = postService.writePost(postDTO);
        // then
//        Post findPost = postService.readPost(post.getId());
//        assertThat(post.getTitle()).isEqualTo(findPost.getTitle());
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}