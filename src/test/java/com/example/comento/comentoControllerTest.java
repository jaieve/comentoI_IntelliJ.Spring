package com.example.comento;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class comentoControllerTest {

	@Autowired
	private MockMvc mockMvc;

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

	@Test
	void hello리턴() throws Exception {
		String hello = "Hello Spring";

		mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string(hello));

	}
}
