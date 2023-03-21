package com.taemin.blogsearch.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("KeywordSearchRank 도메인 테스트")
public class KeywordSearchRankTest {

    @Test
    public void KeywordSearchRank_생성_테스트() {
        KeywordSearchCount keywordSearchCountOne = new KeywordSearchCount("1 keyword", 1);
        KeywordSearchCount keywordSearchCountTwo = new KeywordSearchCount("2 keyword", 2);
        KeywordSearchCount keywordSearchCountThree = new KeywordSearchCount("3 keyword", 3);
        KeywordSearchRank keywordSearchRank = new KeywordSearchRank(Arrays.asList(keywordSearchCountOne, keywordSearchCountTwo, keywordSearchCountThree));
        assertThat(keywordSearchRank).isNotNull();
        assertThat(keywordSearchRank.size()).isEqualTo(3);
    }

    @Test
    public void KeywordSearchRank_Rank_Sorting_테스트() {
        KeywordSearchCount keywordSearchCountOne = new KeywordSearchCount("1 keyword", 1);
        KeywordSearchCount keywordSearchCountTwo = new KeywordSearchCount("2 keyword", 2);
        KeywordSearchCount keywordSearchCountThree = new KeywordSearchCount("3 keyword", 3);
        KeywordSearchRank keywordSearchRank = new KeywordSearchRank(Arrays.asList(keywordSearchCountOne, keywordSearchCountTwo, keywordSearchCountThree));
        assertThat(keywordSearchRank).isNotNull();
        assertThat(keywordSearchRank.size()).isEqualTo(3);

        assertThat(keywordSearchRank.getKeywordSearchRank().get(0).getKeyword()).isEqualTo("3 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(1).getKeyword()).isEqualTo("2 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(2).getKeyword()).isEqualTo("1 keyword");
    }

    @Test
    public void KeywordSearchRank_Rank_10등_까지만_보여주는_테스트() {
        KeywordSearchCount keywordSearchCountOne = new KeywordSearchCount("1 keyword", 1);
        KeywordSearchCount keywordSearchCountTwo = new KeywordSearchCount("2 keyword", 2);
        KeywordSearchCount keywordSearchCountThree = new KeywordSearchCount("4 keyword", 3);
        KeywordSearchCount keywordSearchCountFour = new KeywordSearchCount("5 keyword", 4);
        KeywordSearchCount keywordSearchCountFive = new KeywordSearchCount("6 keyword", 5);
        KeywordSearchCount keywordSearchCountSix = new KeywordSearchCount("7 keyword", 6);
        KeywordSearchCount keywordSearchCountSeven = new KeywordSearchCount("8 keyword", 7);
        KeywordSearchCount keywordSearchCountEight = new KeywordSearchCount("9 keyword", 8);
        KeywordSearchCount keywordSearchCountNine = new KeywordSearchCount("10 keyword", 9);
        KeywordSearchCount keywordSearchCountTen = new KeywordSearchCount("11 keyword", 10);
        KeywordSearchCount keywordSearchCountEleven = new KeywordSearchCount("12 keyword", 11);
        KeywordSearchRank keywordSearchRank = new KeywordSearchRank(Arrays.asList(
                keywordSearchCountOne,
                keywordSearchCountTwo,
                keywordSearchCountThree,
                keywordSearchCountFour,
                keywordSearchCountFive,
                keywordSearchCountSix,
                keywordSearchCountSeven,
                keywordSearchCountEight,
                keywordSearchCountNine,
                keywordSearchCountTen,
                keywordSearchCountEleven

        ));
        assertThat(keywordSearchRank).isNotNull();
        assertThat(keywordSearchRank.size()).isEqualTo(10);

        assertThat(keywordSearchRank.getKeywordSearchRank().get(0).getKeyword()).isEqualTo("12 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(1).getKeyword()).isEqualTo("11 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(2).getKeyword()).isEqualTo("10 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(3).getKeyword()).isEqualTo("9 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(4).getKeyword()).isEqualTo("8 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(5).getKeyword()).isEqualTo("7 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(6).getKeyword()).isEqualTo("6 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(7).getKeyword()).isEqualTo("5 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(8).getKeyword()).isEqualTo("4 keyword");
        assertThat(keywordSearchRank.getKeywordSearchRank().get(9).getKeyword()).isEqualTo("2 keyword");
    }
}
