package com.example.comento.controller;

import com.example.comento.controller.dto.PostDTO;
import com.example.comento.domain.Post;
import com.example.comento.response.BasicResponse;
import com.example.comento.response.CommonResponse;
import com.example.comento.response.ErrorResponse;
import com.example.comento.service.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Basic;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class RootController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String hello() { return "Hello Spring";}


    @GetMapping("/api/post/{id}")
    public ResponseEntity<? extends BasicResponse> getPost(@PathVariable Long id) throws Exception {
        Optional<Post> post = Optional.ofNullable(postService.readPost(id));

        if(!post.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 게시글이 없습니다. id 확인!"));
        }

        return ResponseEntity.ok().body(new CommonResponse<Post>(post.get()));
    }

    @PostMapping ("/api/post")
    public ResponseEntity<? extends BasicResponse> createPost(@RequestBody PostDTO getPost) {
        Post post = postService.writePost(getPost);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("게시글 등록 실패"));
        }
        return ResponseEntity.noContent().build();
    }


    // PutMapping은 @RequestMapping(method = RequestMethod.PUT) 의 단축어이다.
    @PutMapping("/api/post/{id}")
    public ResponseEntity<? extends BasicResponse> updatePost(@PathVariable Long id, @RequestBody PostDTO updatePost) {
        if(!postService.updatePost(id, updatePost)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("찾는 게시글이 없습니다. id를 확인하세요."));
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<? extends BasicResponse> deletePost( @PathVariable Long id) {
        if(!postService.deletePost(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("찾는 게시글이 없습니다. id를 확인하세요."));
        }
        return ResponseEntity.noContent().build();
    }

}



