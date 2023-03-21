package com.taemin.blogsearch.core.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DateConverter 컨버터 테스트")
public class DateConverterTest {

    @Test
    public void DateConverter_toDateNaverPostDate_컨버터_테스트() {
        Date postDate = DateConverter.toDateNaverPostDate("20230322");
        assertThat(postDate).isNotNull();
        assertThat(postDate.toString()).isEqualTo("Wed Mar 22 00:00:00 KST 2023");
    }
}
