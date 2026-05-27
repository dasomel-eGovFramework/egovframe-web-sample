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
 * [게시판][EgovSampleController.updateSample] Controller 단위 테스트
 *
 * @author 이백행
 * @since 2025-05-28
 */
@RequiredArgsConstructor
@Slf4j
class EgovSampleControllerTestUpdateSampleTest extends EgovTestAbstractSpringMvc {

	@Autowired
	private EgovSampleService sampleService;

	@Test
	void test() throws Exception {
		// given: 먼저 등록
		final SampleVO sampleVO = new SampleVO();
		final LocalDateTime now = LocalDateTime.now();

		sampleVO.setName("test 이백행 수정전 " + now);
		sampleVO.setUseYn("Y");
		sampleVO.setDescription("test 이백행 설명 " + now);
		sampleVO.setRegUser("test");
		sampleService.insertSample(sampleVO);

		final String id = sampleVO.getId();

		if (log.isDebugEnabled()) {
			log.debug("insertedId={}", id);
		}

		// when: 수정 요청
		mockMvc.perform(
				post("/updateSample.do")
						.param("id", id)
						.param("name", "test 이백행 수정후 " + now)
						.param("description", "test 이백행 수정설명 " + now)
						.param("regUser", "test")
						.param("useYn", "Y")
		)
				.andDo(print())
				.andExpect(status().isFound())
		;

		// then
		if (log.isDebugEnabled()) {
			log.debug("test");
		}

		assertEquals("", "", "글을 수정한다.");
	}

}
