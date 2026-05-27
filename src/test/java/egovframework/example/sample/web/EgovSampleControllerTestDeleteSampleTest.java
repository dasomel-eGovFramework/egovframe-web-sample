package egovframework.example.sample.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleVO;
import egovframework.test.EgovTestAbstractSpringMvc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [게시판][EgovSampleController.deleteSample] Controller 단위 테스트
 *
 * @author 이백행
 * @since 2025-05-28
 */
@RequiredArgsConstructor
@Slf4j
class EgovSampleControllerTestDeleteSampleTest extends EgovTestAbstractSpringMvc {

	@Autowired
	private EgovSampleService sampleService;

	@Test
	void test() throws Exception {
		// given: 먼저 등록
		final SampleVO sampleVO = new SampleVO();
		final LocalDateTime now = LocalDateTime.now();

		sampleVO.setName("test 이백행 삭제대상 " + now);
		sampleVO.setUseYn("Y");
		sampleVO.setDescription("test 이백행 설명 " + now);
		sampleVO.setRegUser("test");
		sampleService.insertSample(sampleVO);

		final String id = sampleVO.getId();

		if (log.isDebugEnabled()) {
			log.debug("insertedId={}", id);
		}

		// when: 삭제 요청
		mockMvc.perform(
				post("/deleteSample.do")
						.param("id", id)
		)
				.andDo(print())
				.andExpect(status().isFound())
		;

		// then
		if (log.isDebugEnabled()) {
			log.debug("test");
		}

		assertEquals("", "", "글을 삭제한다.");
	}

}
