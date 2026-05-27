package egovframework.example.sample.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
 * [게시판][SampleMapper.selectSampleList / selectSampleListTotCnt] DAO 단위 테스트
 *
 * @author 이백행
 * @since 2024-09-21
 *
 */

@ContextConfiguration(classes = { SampleMapperTestSelectSampleListTest.class, EgovTestAbstractSpring.class })

@Configuration

@ImportResource({ "classpath*:egovframework/spring/context-idgen.xml", })

@ComponentScan(useDefaultFilters = false, basePackages = {
		"egovframework.example.sample.service.impl", }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { SampleMapper.class, }) })

@RequiredArgsConstructor
@Slf4j
class SampleMapperTestSelectSampleListTest extends EgovTestAbstractSpring {

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
		final SampleVO sampleVO1 = new SampleVO();
		sampleVO1.setId(egovIdGnrService.getNextStringId());
		sampleVO1.setName("test 이백행 목록조회1");
		sampleVO1.setUseYn("Y");
		sampleVO1.setDescription("test 이백행 목록조회 설명1");
		sampleVO1.setRegUser("test");
		sampleMapper.insertSample(sampleVO1);

		final SampleVO sampleVO2 = new SampleVO();
		sampleVO2.setId(egovIdGnrService.getNextStringId());
		sampleVO2.setName("test 이백행 목록조회2");
		sampleVO2.setUseYn("Y");
		sampleVO2.setDescription("test 이백행 목록조회 설명2");
		sampleVO2.setRegUser("test");
		sampleMapper.insertSample(sampleVO2);

		// when
		final SampleVO searchVO = new SampleVO();
		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(10);

		final List<?> resultList = sampleMapper.selectSampleList(searchVO);
		final int totCnt = sampleMapper.selectSampleListTotCnt(searchVO);

		// then
		if (log.isDebugEnabled()) {
			log.debug("resultList.size={}", resultList.size());
			log.debug("totCnt={}", totCnt);
		}

		assertNotNull(resultList, "목록 결과가 null이 아니어야 한다.");
		assertTrue(resultList.size() >= 2, "등록한 건수 이상이어야 한다.");
		assertTrue(totCnt >= 2, "전체 건수가 등록한 건수 이상이어야 한다.");
	}

}
