package com.example.comento.service;

import com.example.comento.controller.dto.PostDTO;
import com.example.comento.domain.Post;
import com.example.comento.repository.PostRepository;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post writePost(PostDTO postDTO) {
        String title = postDTO.getTitle();
        String content = postDTO.getContent();
        String writer = postDTO.getWriter();

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        return postRepository.save(post);
    }

    public Post readPost(Long id) {
        Optional<Post> dbPost = Optional.ofNullable(postRepository.findById(id).orElse(null));
        Post post =  dbPost.get();
        post.setViews(post.getViews()+1);
        postRepository.save(post);
        return postRepository.findById(id).orElse(null);
    }

    public Boolean updatePost(Long id, PostDTO update){
        Optional<Post> dbPost = Optional.ofNullable(postRepository.findById(id).orElse(null));

        //값이 있는 경우에 이를 사용하고 없는 경우에 아무 동작도 하지 않는다면, Optional.ifPresent()를 활용
        // ifPresent()는 Optional 객체 안에 값이 있는 경우 실행할 람다를 인자로 받음.
        // DB에 해당 사용자가 존재하는지 여부를 체크하기 위해 isPresent()를 이용하여 분기처리
        if(dbPost.isPresent()){
            Post post = dbPost.get();
            if(!update.getTitle().isEmpty()){ post.setTitle(update.getTitle()); }
            if(!update.getContent().isEmpty()){ post.setContent(update.getContent()); }
            if(!update.getWriter().isEmpty()){ post.setWriter(update.getWriter()); }

            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            post.setUpdateAt(timestamp);

            postRepository.save(post);
            return true;
        }
        return false;
    }

    public Boolean deletePost(Long id) {
        Optional<Post> dbPost = Optional.ofNullable(postRepository.findById(id).orElse(null));

        if(dbPost.isPresent()){
            postRepository.delete(dbPost.get());
            return true;
        }
        return false;
    }
}
