package com.taemin.blogsearch.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SortType 도메인 테스트")
public class SortTypeTest {

    @Test
    public void SortType_KakaoType_NaverType_테스트() {
        assertThat(SortType.ACCURACY.getKakaoType()).isEqualTo("accuracy");
        assertThat(SortType.ACCURACY.getNaverType()).isEqualTo("sim");
        assertThat(SortType.RECENCY.getKakaoType()).isEqualTo("recency");
        assertThat(SortType.RECENCY.getNaverType()).isEqualTo("date");
    }

    @Test
    public void SortType_of_테스트() {
        assertThat(SortType.of("accuracy")).isEqualTo(SortType.ACCURACY);
        assertThat(SortType.of("recency")).isEqualTo(SortType.RECENCY);
        assertThat(SortType.of("aaaaaaa")).isEqualTo(SortType.ACCURACY);
    }

}
