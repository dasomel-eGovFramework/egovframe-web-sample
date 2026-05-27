package egovframework.example.sample.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
 * [게시판][SampleMapper.selectSample] DAO 단위 테스트
 *
 * @author 이백행
 * @since 2024-09-21
 *
 */

@ContextConfiguration(classes = { SampleMapperTestSelectSampleTest.class, EgovTestAbstractSpring.class })

@Configuration

@ImportResource({ "classpath*:egovframework/spring/context-idgen.xml", })

@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.example.sample.service.impl", }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { SampleMapper.class, }) })

@RequiredArgsConstructor
@Slf4j
class SampleMapperTestSelectSampleTest extends EgovTestAbstractSpring {

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
		sampleVO.setName("test 이백행 조회 카테고리명");
		sampleVO.setUseYn("Y");
		sampleVO.setDescription("test 이백행 조회 설명");
		sampleVO.setRegUser("test");

		sampleMapper.insertSample(sampleVO);

		// when
		final SampleVO queryVO = new SampleVO();
		queryVO.setId(sampleVO.getId());

		final SampleVO resultSampleVO = sampleMapper.selectSample(queryVO);

		// then
		if (log.isDebugEnabled()) {
			log.debug("sampleVO.getId={}", sampleVO.getId());
			log.debug("resultSampleVO={}", resultSampleVO);
		}

		assertNotNull(resultSampleVO, "조회 결과가 null이 아니어야 한다.");
		assertEquals(sampleVO.getId(), resultSampleVO.getId(), "글을 조회한다.");
		assertEquals(sampleVO.getName(), resultSampleVO.getName(), "이름이 일치해야 한다.");
	}

}
