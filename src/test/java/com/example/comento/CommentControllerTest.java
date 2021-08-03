package com.example.comento;

import com.example.comento.controller.dto.PostDTO;
import com.example.comento.domain.Post;
import com.example.comento.service.PostService;
import com.example.comento.util.JacksonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
class CommentControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext ctx;

	@Autowired
	private PostService postService;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.alwaysDo(print())
				.build();
	}

	@Test
	void contextLoads() {
		System.out.println("Hello! This is test code");
	}

	//	@Test
	void testWritingPost() throws Exception {
		//given
		String title = "타이틀 테스트";
		String content = "테스트 콘텐츠";
		String writer = "홍서연";
		PostDTO postDTO = PostDTO.builder().title(title).content(content).writer(writer).build();
		String data = JacksonUtil.toJson(postDTO);
		//when
		mockMvc.perform(post("/api/post")
						.contentType(MediaType.APPLICATION_JSON)
						.content(data))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.title").value(title))
				.andExpect(jsonPath("$.content").value(content))
				.andExpect(jsonPath("$.writer").value(writer))
				.andDo(print());

	}

	@Test
	void testReadingPost() throws Exception {
		//given
		testWritingPost();

		String title = "타이틀 테스트";
		String content = "테스트 콘텐츠";
		String writer = "홍서연";

		mockMvc.perform(get("/api/post/{id}", 6L))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.title").value(title))
				.andExpect(jsonPath("$.content").value(content))
				.andExpect(jsonPath("$.writer").value(writer));
	}

	@Test
	void testUpdatingPost() throws Exception {
		//given
		Post post = postService.readPost(6L);
		PostDTO postDTO = PostDTO.builder().title("0729 title update").content("0729 content update").writer(post.getWriter()).build();
		String data = JacksonUtil.toJson(postDTO);
		//when
		mockMvc.perform(put("/api/post/{id}", 6L)
						.contentType(MediaType.APPLICATION_JSON)
						.content(data))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.title").value(postDTO.getTitle()))
				.andExpect(jsonPath("$.content").value(postDTO.getContent()))
				.andDo(print());
		//then

	}

	@Test
	void testReadPosts() throws Exception {
		//given
		//when
		mockMvc.perform(get("/api/post")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andDo(print());
		//then
	}

	@Test
	void testDeletingPost() throws Exception {
		//given

		//when
		mockMvc.perform(delete("/api/post/{id}", 6L));

		//then

	}
}