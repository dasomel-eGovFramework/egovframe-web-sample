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
package egovframework.example.cmmn.web;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.beans.PropertyEditor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.web.bind.WebDataBinder;

import lombok.extern.slf4j.Slf4j;

/**
 * EgovBindingInitializer 단위 테스트
 *
 * @author 이백행
 * @since 2024-09-21
 */
@Slf4j
class EgovBindingInitializerTest {

	private EgovBindingInitializer bindingInitializer;

	private WebDataBinder binder;

	@BeforeEach
	void setUp() {
		bindingInitializer = new EgovBindingInitializer();
		binder = new WebDataBinder(null);
		bindingInitializer.initBinder(binder);
	}

	@Test
	@DisplayName("날짜 문자열 yyyy-MM-dd 형식을 Date로 변환한다")
	void testDateEditorValidFormat() throws ParseException {
		// given
		final String dateStr = "2024-01-15";
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		final Date expected = sdf.parse(dateStr);

		// when
		final PropertyEditor editor = binder.findCustomEditor(Date.class, null);
		assertNotNull(editor);
		editor.setAsText(dateStr);
		final Date result = (Date) editor.getValue();

		// then
		assertEquals(expected, result);

		if (log.isDebugEnabled()) {
			log.debug("dateStr={}", dateStr);
			log.debug("result={}", result);
		}
	}

	@Test
	@DisplayName("잘못된 날짜 형식(yyyy/MM/dd)은 변환에 실패한다")
	void testDateEditorInvalidFormat() {
		// given
		final String invalidDateStr = "2024/01/15";
		final PropertyEditor editor = binder.findCustomEditor(Date.class, null);
		assertNotNull(editor);

		// when & then
		assertThrows(Exception.class, () -> editor.setAsText(invalidDateStr));

		if (log.isDebugEnabled()) {
			log.debug("invalidDateStr={}", invalidDateStr);
		}
	}

	@Test
	@DisplayName("StringTrimmerEditor가 등록되어 문자열 앞뒤 공백을 제거한다")
	void testStringTrimmerEditor() {
		// given
		final String paddedStr = "  hello world  ";

		// when
		final PropertyEditorRegistry registry = binder;
		final PropertyEditor editor = registry.findCustomEditor(String.class, null);

		// then
		assertNotNull(editor);

		editor.setAsText(paddedStr);
		final String result = (String) editor.getValue();
		assertEquals("hello world", result);

		if (log.isDebugEnabled()) {
			log.debug("paddedStr='{}'", paddedStr);
			log.debug("result='{}'", result);
		}
	}

	@Test
	@DisplayName("Date PropertyEditor가 등록되어 있다")
	void testDateEditorIsRegistered() {
		// given & when
		final PropertyEditorRegistry registry = binder;
		final PropertyEditor editor = registry.findCustomEditor(Date.class, null);

		// then
		assertNotNull(editor);

		if (log.isDebugEnabled()) {
			log.debug("editor={}", editor);
		}
	}

}
