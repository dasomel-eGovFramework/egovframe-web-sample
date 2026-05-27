package egovframework.example.sample.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import egovframework.example.sample.service.SampleVO;
import egovframework.test.EgovTestAbstractSpring;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [게시판][SampleMapper.updateSample] DAO 단위 테스트
 *
 * @author 이백행
 * @since 2024-09-21
 *
 */

@ContextConfiguration(classes = { SampleMapperTestUpdateSampleTest.class, EgovTestAbstractSpring.class })

@Configuration

@ImportResource({ "classpath*:egovframework/spring/context-idgen.xml", })

@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.example.sample.service.impl", }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { SampleMapper.class, }) })

@RequiredArgsConstructor
@Slf4j
class SampleMapperTestUpdateSampleTest extends EgovTestAbstractSpring {

	/**
	 * sample에 관한 데이터처리 매퍼 클래스
	 */
	@Autowired
	private SampleMapper sampleMapper;

	/**
	 *
	 */
	@Autowired
	private EgovIdGnrService egovIdGnrService;

	@Test
	void test() throws Exception {
		// given
		final SampleVO sampleVO = new SampleVO();

		sampleVO.setId(egovIdGnrService.getNextStringId());
		sampleVO.setName("test 이백행 등록명");
		sampleVO.setUseYn("Y");
		sampleVO.setDescription("test 이백행 등록 설명");
		sampleVO.setRegUser("test");

		sampleMapper.insertSample(sampleVO);

		final String id = sampleVO.getId();

		// when
		final SampleVO updateVO = new SampleVO();
		updateVO.setId(id);
		updateVO.setName("test 이백행 수정명");
		updateVO.setUseYn("N");
		updateVO.setDescription("test 이백행 수정 설명");
		updateVO.setRegUser("test");

		sampleMapper.updateSample(updateVO);

		// then
		final SampleVO queryVO = new SampleVO();
		queryVO.setId(id);

		final SampleVO resultSampleVO = sampleMapper.selectSample(queryVO);

		if (log.isDebugEnabled()) {
			log.debug("id={}", id);
			log.debug("resultSampleVO={}", resultSampleVO);
			log.debug("getName={}", resultSampleVO.getName());
		}

		assertEquals("test 이백행 수정명", resultSampleVO.getName(), "글을 수정한다.");
		assertEquals("N", resultSampleVO.getUseYn(), "사용여부를 수정한다.");
	}

}
