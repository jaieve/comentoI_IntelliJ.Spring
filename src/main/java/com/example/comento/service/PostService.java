package com.example.comento.service;

import com.example.comento.controller.dto.PostDTO;
import com.example.comento.domain.Post;
import com.example.comento.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post writePost(PostDTO postDTO) {
        Post post = Post.builder()
                        .title(postDTO.getTitle())
                        .writer(postDTO.getWriter())
                        .content(postDTO.getContent())
                        .build();
        return postRepository.save(post);
    }

    public Post readPost(Long id){
        // 추가할 로직
        // 1. exception 추가
        // 2. 조회수 증가
        return postRepository.findById(id).orElseThrow();
    }

//    public Post readPost(Long id) {
//        Post dbPost = postRepository.findById(id).orElse(null);
//        Post post =  dbPost.get();
//        post.setViews(post.getViews()+1);
//        postRepository.save(post);
//        return postRepository.findById(id).orElse(null);
//    }

    public Post updatePost(Long id, PostDTO postDTO){
        String title = postDTO.getTitle();
        String content = postDTO.getContent();

        Post post = postRepository.findById(id).orElse(null);
        post.setContent(content);
        post.setTitle(title);

        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        postRepository.deleteById(post.getId());
    }
}
