package com.taemin.blogsearch.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("KeywordSearchCount 도메인 테스트")
public class KeywordSearchCountTest {

    @Test
    public void KeywordSearchCount_생성_테스트() {
        KeywordSearchCount keywordSearchCount = new KeywordSearchCount("keyword", 1);
        assertThat(keywordSearchCount.getKeyword()).isEqualTo("keyword");
        assertThat(keywordSearchCount.getCount()).isEqualTo(1);
    }

    @Test
    public void KeywordSearchCount_plusCount_테스트() {
        KeywordSearchCount keywordSearchCount = new KeywordSearchCount("keyword", 1);
        assertThat(keywordSearchCount.getKeyword()).isEqualTo("keyword");
        assertThat(keywordSearchCount.getCount()).isEqualTo(1);
        keywordSearchCount.plusCount();
        assertThat(keywordSearchCount.getCount()).isEqualTo(2);
    }
}
