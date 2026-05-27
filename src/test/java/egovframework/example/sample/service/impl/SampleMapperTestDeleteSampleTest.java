package egovframework.example.sample.service.impl;

import static org.junit.jupiter.api.Assertions.assertNull;

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
 * [게시판][SampleMapper.deleteSample] DAO 단위 테스트
 *
 * @author 이백행
 * @since 2024-09-21
 *
 */

@ContextConfiguration(classes = { SampleMapperTestDeleteSampleTest.class, EgovTestAbstractSpring.class })

@Configuration

@ImportResource({ "classpath*:egovframework/spring/context-idgen.xml", })

@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.example.sample.service.impl", }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { SampleMapper.class, }) })

@RequiredArgsConstructor
@Slf4j
class SampleMapperTestDeleteSampleTest extends EgovTestAbstractSpring {

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
		sampleVO.setName("test 이백행 삭제 카테고리명");
		sampleVO.setUseYn("Y");
		sampleVO.setDescription("test 이백행 삭제 설명");
		sampleVO.setRegUser("test");

		sampleMapper.insertSample(sampleVO);

		// when
		final SampleVO deleteVO = new SampleVO();
		deleteVO.setId(sampleVO.getId());

		sampleMapper.deleteSample(deleteVO);

		// then
		final SampleVO queryVO = new SampleVO();
		queryVO.setId(sampleVO.getId());

		final SampleVO resultSampleVO = sampleMapper.selectSample(queryVO);

		if (log.isDebugEnabled()) {
			log.debug("sampleVO.getId={}", sampleVO.getId());
			log.debug("resultSampleVO={}", resultSampleVO);
		}

		assertNull(resultSampleVO, "글을 삭제한다.");
	}

}
