package com.example.comento;

import com.example.comento.controller.dto.PostDTO;
import com.example.comento.domain.Post;
import com.example.comento.service.PostService;
import com.example.comento.util.JacksonUtil;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
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
				.addFilters(new CharacterEncodingFilter("UTF-8",true))
				.alwaysDo(print())
				.build();
	}

	String title = "타이틀 테스트";
	String content = "테스트 콘텐츠";
	String writer = "홍서연";

	@Test
	void contextLoads(){
		System.out.println("Hello! This is test code");
	}

//	@Test
	void testWritingPost() throws Exception {
		//given
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

		mockMvc.perform(get("/api/post{id}", 8L))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.title").value(title))
				.andExpect(jsonPath("$.content").value(content))
				.andExpect(jsonPath("$.writer").value(writer));
	}
	@Test
	void testUpdatingPost() throws Exception{
		//given
		Post post = postService.readPost(5L);
		PostDTO postDTO = PostDTO.builder().title("0725 title update").content("0725 content update").writer(post.getWriter()).build();
		String data = JacksonUtil.toJson(postDTO);
		//when
		//when
		mockMvc.perform(put("/api/post/{id}",5L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(data))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.title").value(postDTO.getTitle()))
				.andExpect(jsonPath("$.content").value(postDTO.getContent()))
				.andDo(print());
		//then

	}
	@Test
	void testDeletingPost() throws Exception{
		//given

		//when
		mockMvc.perform(delete("/api/post/{id}", 9L));

		//then

	}
//	@Autowired
//	private WebApplicationContext ctx;

//	@BeforeEach
//	void setup(){
//		this.mockMvc = MockMvcBuilders
//				.webAppContextSetup(ctx)
//				.addFilters(new CharacterEncodingFilter("UTF-8", true))
//				.alwaysDo(print())
//				.build();
//	}

//	@Test
//	void hello리턴() throws Exception {
//		String hello = "Hello Spring";
//
//		mockMvc.perform(get("/hello"))
//				.andExpect(status().isOk())
//				.andExpect(content().string(hello));
//
//	}
}
