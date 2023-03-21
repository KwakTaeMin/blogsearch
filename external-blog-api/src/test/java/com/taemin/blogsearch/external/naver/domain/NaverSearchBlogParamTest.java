package com.taemin.blogsearch.external.naver.domain;

import com.taemin.blogsearch.external.naver.doamin.NaverSearchBlogParam;
import com.taemin.blogsearch.external.naver.doamin.NaverSortType;
import com.taemin.blogsearch.external.naver.exception.NaverParamException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("NaverSearchBlog Parameter 테스트")
public class NaverSearchBlogParamTest {

    private static final String QUERY_ERROR_MESSAGE = "질의어는 필수 값 입니다.";
    private static final String SORT_ERROR_MESSAGE = "정렬 방식은 필수 값 혹은 정렬 방식이 맞지 않습니다.";
    private static final String START_ERROR_MESSAGE = "페이지 최소 값은 1, 최대값 100입니다.";
    private static final String DISPLAY_ERROR_MESSAGE = "페이지 당 보여줄 수 있는 개수는 최소 1 최대 100 입니다.";

    @Test
    public void NaverSearchBlogParam_생성_테스트() {
        NaverSearchBlogParam kakaoSearchBlogParam = new NaverSearchBlogParam("질의", NaverSortType.SIM.name(), 1, 10);
        assertThat(kakaoSearchBlogParam.getQuery()).isEqualTo("질의");
        assertThat(kakaoSearchBlogParam.getSort()).isEqualTo(NaverSortType.SIM.name());
        assertThat(kakaoSearchBlogParam.getStart()).isEqualTo(1);
        assertThat(kakaoSearchBlogParam.getDisplay()).isEqualTo(10);
    }

    @Test
    public void NaverSearchBlogParam_생성_테스트_질의어_빈_값인_경우() {
        Throwable exception = assertThrows(NaverParamException.class, () -> {
            new NaverSearchBlogParam("", NaverSortType.SIM.name(), 1, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(QUERY_ERROR_MESSAGE);
    }

    @Test
    public void NaverSearchBlogParam_생성_테스트_질의어_NULL_값인_경우() {
        Throwable exception = assertThrows(NaverParamException.class, () -> {
            new NaverSearchBlogParam("", NaverSortType.SIM.name(), 1, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(QUERY_ERROR_MESSAGE);
    }

    @Test
    public void NaverSearchBlogParam_생성_테스트_Sort_타입이_없는_경우() {
        Throwable exception = assertThrows(NaverParamException.class, () -> {
            new NaverSearchBlogParam("카카오", "", 1, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(SORT_ERROR_MESSAGE);
    }

    @Test
    public void NaverSearchBlogParam_생성_테스트_Sort_타입이_NULL_경우() {
        Throwable exception = assertThrows(NaverParamException.class, () -> {
            new NaverSearchBlogParam("카카오", null, 1, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(SORT_ERROR_MESSAGE);
    }

    @Test
    public void NaverSearchBlogParam_생성_테스트_Sort_타입이_다를_경우() {
        Throwable exception = assertThrows(NaverParamException.class, () -> {
            new NaverSearchBlogParam("카카오", "sort", 1, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(SORT_ERROR_MESSAGE);
    }

    @Test
    public void NaverSearchBlogParam_생성_테스트_Page_1보다_작은_경우() {
        Throwable exception = assertThrows(NaverParamException.class, () -> {
            new NaverSearchBlogParam("카카오", NaverSortType.SIM.name(), 0, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(START_ERROR_MESSAGE);
    }

    @Test
    public void NaverSearchBlogParam_생성_테스트_Size_1보다_작은_경우() {
        Throwable exception = assertThrows(NaverParamException.class, () -> {
            new NaverSearchBlogParam("카카오", NaverSortType.SIM.name(), 1, 0);
        });
        assertThat(exception.getMessage()).isEqualTo(DISPLAY_ERROR_MESSAGE);
    }

    @Test
    public void NaverSearchBlogParam_생성_테스트_Size_100보다_큰_경우() {
        Throwable exception = assertThrows(NaverParamException.class, () -> {
            new NaverSearchBlogParam("카카오", NaverSortType.SIM.name(), 1, 101);
        });
        assertThat(exception.getMessage()).isEqualTo(DISPLAY_ERROR_MESSAGE);
    }
}
