package com.example.comento.controller;

import com.example.comento.controller.dto.PostDTO;
import com.example.comento.domain.Post;
import com.example.comento.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RootController {

    @Autowired
    private PostService postService;

    @GetMapping("/hello")
    public String hello() { return "Hello Spring";}

    @GetMapping("/api/post")
    public List<Post> readPosts() {
        return  postService.readPosts();
    }

    @GetMapping("/api/post/{id}")
    public Post readPost(@PathVariable Long id){
        return postService.readPost(id);
    }

//    @GetMapping("/api/post/{id}")
//    public Post readPost(@PathVariable Long id) throws Exception {
//        Optional<Post> post = Optional.ofNullable(postService.readPost(id));
//
//        if(!post.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponse("일치하는 게시글이 없습니다. id 확인!"));
//        }
//
//        return ResponseEntity.ok().body(new CommonResponse<Post>(post.get()));
//    }

    @PostMapping ("/api/post")
    public Post createPost(@RequestBody PostDTO postDTO) {
        return postService.writePost(postDTO);
    }

//    @PostMapping ("/api/post")
//    public ResponseEntity<? extends BasicResponse> createPost(@RequestBody PostDTO postDTO) {
//        Post post = postService.writePost(postDTO);
//        if (post == null) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("게시글 등록 실패"));
//        }
//        return ResponseEntity.noContent().build();
//    }


    @PutMapping("/api/post/{id}")
    public Post updatePost(@PathVariable Long id,@RequestBody PostDTO postDTO)
    {
        return postService.updatePost(id,postDTO);
    }

//    // PutMapping은 @RequestMapping(method = RequestMethod.PUT) 의 단축어이다.
//    @PutMapping("/api/post/{id}")
//    public ResponseEntity<? extends BasicResponse> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
//        if (!postService.updatePost(id, postDTO)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponse("찾는 게시글이 없습니다. id를 확인하세요."));
//        }
//        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping("/api/post/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return id+" 번 게시글 삭제";
    }

//    @DeleteMapping("/api/post/{id}")
//    public ResponseEntity<? extends BasicResponse> deletePost( @PathVariable Long id) {
//        if(!postService.deletePost(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponse("찾는 게시글이 없습니다. id를 확인하세요."));
//        }
//        return ResponseEntity.noContent().build();
//    }

}



