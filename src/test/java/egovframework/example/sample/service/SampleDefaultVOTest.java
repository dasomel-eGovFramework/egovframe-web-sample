/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * [게시판] SampleDefaultVO 단위 테스트
 *
 * @author 이백행
 * @since 2024-09-21
 */
class SampleDefaultVOTest {

    private SampleDefaultVO vo;

    @BeforeEach
    void setUp() {
        vo = new SampleDefaultVO();
    }

    @Test
    @DisplayName("검색조건 기본값은 빈 문자열이다")
    void searchConditionDefaultIsEmpty() {
        assertEquals("", vo.getSearchCondition());
    }

    @Test
    @DisplayName("검색 키워드 기본값은 빈 문자열이다")
    void searchKeywordDefaultIsEmpty() {
        assertEquals("", vo.getSearchKeyword());
    }

    @Test
    @DisplayName("현재 페이지 기본값은 1이다")
    void pageIndexDefaultIsOne() {
        assertEquals(1, vo.getPageIndex());
    }

    @Test
    @DisplayName("페이지 단위 기본값은 10이다")
    void pageUnitDefaultIsTen() {
        assertEquals(10, vo.getPageUnit());
    }

    @Test
    @DisplayName("페이지 사이즈 기본값은 10이다")
    void pageSizeDefaultIsTen() {
        assertEquals(10, vo.getPageSize());
    }

    @Test
    @DisplayName("페이지당 레코드 수 기본값은 10이다")
    void recordCountPerPageDefaultIsTen() {
        assertEquals(10, vo.getRecordCountPerPage());
    }

    @Test
    @DisplayName("firstIndex 기본값은 1이다")
    void firstIndexDefaultIsOne() {
        assertEquals(1, vo.getFirstIndex());
    }

    @Test
    @DisplayName("lastIndex 기본값은 1이다")
    void lastIndexDefaultIsOne() {
        assertEquals(1, vo.getLastIndex());
    }

    @Test
    @DisplayName("검색조건 setter/getter가 정상 동작한다")
    void searchConditionSetterGetter() {
        vo.setSearchCondition("1");
        assertEquals("1", vo.getSearchCondition());
    }

    @Test
    @DisplayName("검색 키워드 setter/getter가 정상 동작한다")
    void searchKeywordSetterGetter() {
        vo.setSearchKeyword("테스트");
        assertEquals("테스트", vo.getSearchKeyword());
    }

    @Test
    @DisplayName("페이지 인덱스 setter/getter가 정상 동작한다")
    void pageIndexSetterGetter() {
        vo.setPageIndex(3);
        assertEquals(3, vo.getPageIndex());
    }

    @Test
    @DisplayName("toString이 null이 아니다")
    void toStringIsNotNull() {
        assertNotNull(vo.toString());
    }

    @Test
    @DisplayName("toString이 필드명을 포함한다")
    void toStringContainsFieldNames() {
        String result = vo.toString();
        assertNotNull(result);
        assertEquals(true, result.contains("pageIndex"));
    }

}
