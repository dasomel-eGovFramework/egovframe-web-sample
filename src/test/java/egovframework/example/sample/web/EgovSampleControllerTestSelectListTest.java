package egovframework.example.sample.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import egovframework.test.EgovTestAbstractSpringMvc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [게시판][EgovSampleController.selectSampleList] Controller 단위 테스트
 *
 * @author 이백행
 * @since 2025-05-28
 */
@RequiredArgsConstructor
@Slf4j
class EgovSampleControllerTestSelectListTest extends EgovTestAbstractSpringMvc {

	@Test
	void test() throws Exception {
		// when
		mockMvc.perform(
				get("/egovSampleList.do")
		)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("resultList"))
		;

		// then
		if (log.isDebugEnabled()) {
			log.debug("test");
		}

		assertEquals("", "", "글 목록을 조회한다.");
	}

}
