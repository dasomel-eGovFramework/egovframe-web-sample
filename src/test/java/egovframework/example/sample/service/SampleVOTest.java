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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * [게시판] SampleVO 단위 테스트
 *
 * @author 이백행
 * @since 2024-09-21
 */
class SampleVOTest {

    private SampleVO vo;

    @BeforeEach
    void setUp() {
        vo = new SampleVO();
    }

    @Test
    @DisplayName("id 초기값은 null이다")
    void idDefaultIsNull() {
        assertNull(vo.getId());
    }

    @Test
    @DisplayName("name 초기값은 null이다")
    void nameDefaultIsNull() {
        assertNull(vo.getName());
    }

    @Test
    @DisplayName("description 초기값은 null이다")
    void descriptionDefaultIsNull() {
        assertNull(vo.getDescription());
    }

    @Test
    @DisplayName("useYn 초기값은 null이다")
    void useYnDefaultIsNull() {
        assertNull(vo.getUseYn());
    }

    @Test
    @DisplayName("regUser 초기값은 null이다")
    void regUserDefaultIsNull() {
        assertNull(vo.getRegUser());
    }

    @Test
    @DisplayName("id setter/getter가 정상 동작한다")
    void idSetterGetter() {
        vo.setId("SAMPLE-001");
        assertEquals("SAMPLE-001", vo.getId());
    }

    @Test
    @DisplayName("name setter/getter가 정상 동작한다")
    void nameSetterGetter() {
        vo.setName("테스트 게시글");
        assertEquals("테스트 게시글", vo.getName());
    }

    @Test
    @DisplayName("description setter/getter가 정상 동작한다")
    void descriptionSetterGetter() {
        vo.setDescription("설명 내용");
        assertEquals("설명 내용", vo.getDescription());
    }

    @Test
    @DisplayName("useYn setter/getter가 정상 동작한다")
    void useYnSetterGetter() {
        vo.setUseYn("Y");
        assertEquals("Y", vo.getUseYn());
    }

    @Test
    @DisplayName("regUser setter/getter가 정상 동작한다")
    void regUserSetterGetter() {
        vo.setRegUser("admin");
        assertEquals("admin", vo.getRegUser());
    }

    @Test
    @DisplayName("SampleVO는 SampleDefaultVO를 상속한다")
    void sampleVOExtendsSampleDefaultVO() {
        assertInstanceOf(SampleDefaultVO.class, vo);
    }

    @Test
    @DisplayName("SampleDefaultVO에서 상속받은 페이지 기본값이 정상이다")
    void inheritedPageIndexDefaultIsOne() {
        assertEquals(1, vo.getPageIndex());
    }

    @Test
    @DisplayName("toString이 null이 아니다")
    void toStringIsNotNull() {
        vo.setId("SAMPLE-001");
        vo.setName("테스트");
        assertNotNull(vo.toString());
    }

    @Test
    @DisplayName("여러 필드를 동시에 설정하면 각각 정상 반환된다")
    void multipleFieldsSetAndGet() {
        vo.setId("S-100");
        vo.setName("게시글 제목");
        vo.setDescription("게시글 내용");
        vo.setUseYn("Y");
        vo.setRegUser("user01");

        assertEquals("S-100", vo.getId());
        assertEquals("게시글 제목", vo.getName());
        assertEquals("게시글 내용", vo.getDescription());
        assertEquals("Y", vo.getUseYn());
        assertEquals("user01", vo.getRegUser());
    }

}
