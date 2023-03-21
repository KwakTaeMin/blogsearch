package com.taemin.blogsearch.external.kakao.doamin;

import com.taemin.blogsearch.external.kakao.domain.KakaoSearchBlogParam;
import com.taemin.blogsearch.external.kakao.domain.KakaoSortType;
import com.taemin.blogsearch.external.kakao.exception.KakaoParamException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("KakaoSearchBlog Parameter 테스트")
public class KakaoSearchBlogParamTest {

    private static final String QUERY_ERROR_MESSAGE = "질의어는 필수 값 입니다.";
    private static final String SORT_ERROR_MESSAGE = "정렬 방식은 필수 값 혹은 정렬 방식이 맞지 않습니다.";
    private static final String PAGE_ERROR_MESSAGE = "페이지 최소 값은 1 이상 입니다.";
    private static final String SIZE_ERROR_MESSAGE = "페이지 당 보여줄 수 있는 개수는 최소 1 최대 50 입니다.";
    @Test
    public void KakaoSearchBlogParam_생성_테스트() {
        KakaoSearchBlogParam kakaoSearchBlogParam = new KakaoSearchBlogParam("질의", KakaoSortType.ACCURACY.name(), 1, 10);
        assertThat(kakaoSearchBlogParam.getQuery()).isEqualTo("질의");
        assertThat(kakaoSearchBlogParam.getSort()).isEqualTo(KakaoSortType.ACCURACY.name());
        assertThat(kakaoSearchBlogParam.getPage()).isEqualTo(1);
        assertThat(kakaoSearchBlogParam.getSize()).isEqualTo(10);
    }

    @Test
    public void KakaoSearchBlogParam_생성_테스트_질의어_빈_값인_경우() {
        Throwable exception = assertThrows(KakaoParamException.class, () -> {
            new KakaoSearchBlogParam("", KakaoSortType.ACCURACY.name(), 1, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(QUERY_ERROR_MESSAGE);
    }

    @Test
    public void KakaoSearchBlogParam_생성_테스트_질의어_NULL_값인_경우() {
        Throwable exception = assertThrows(KakaoParamException.class, () -> {
            new KakaoSearchBlogParam("", KakaoSortType.ACCURACY.name(), 1, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(QUERY_ERROR_MESSAGE);
    }

    @Test
    public void KakaoSearchBlogParam_생성_테스트_Sort_타입이_없는_경우() {
        Throwable exception = assertThrows(KakaoParamException.class, () -> {
            new KakaoSearchBlogParam("카카오", "", 1, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(SORT_ERROR_MESSAGE);
    }

    @Test
    public void KakaoSearchBlogParam_생성_테스트_Sort_타입이_NULL_경우() {
        Throwable exception = assertThrows(KakaoParamException.class, () -> {
            new KakaoSearchBlogParam("카카오", null, 1, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(SORT_ERROR_MESSAGE);
    }

    @Test
    public void KakaoSearchBlogParam_생성_테스트_Sort_타입이_다를_경우() {
        Throwable exception = assertThrows(KakaoParamException.class, () -> {
            new KakaoSearchBlogParam("카카오", "sort", 1, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(SORT_ERROR_MESSAGE);
    }

    @Test
    public void KakaoSearchBlogParam_생성_테스트_Page_1보다_작은_경우() {
        Throwable exception = assertThrows(KakaoParamException.class, () -> {
            new KakaoSearchBlogParam("카카오", KakaoSortType.ACCURACY.name(), 0, 10);
        });
        assertThat(exception.getMessage()).isEqualTo(PAGE_ERROR_MESSAGE);
    }

    @Test
    public void KakaoSearchBlogParam_생성_테스트_Size_1보다_작은_경우() {
        Throwable exception = assertThrows(KakaoParamException.class, () -> {
            new KakaoSearchBlogParam("카카오", KakaoSortType.ACCURACY.name(), 1, 0);
        });
        assertThat(exception.getMessage()).isEqualTo(SIZE_ERROR_MESSAGE);
    }

    @Test
    public void KakaoSearchBlogParam_생성_테스트_Size_50보다_큰_경우() {
        Throwable exception = assertThrows(KakaoParamException.class, () -> {
            new KakaoSearchBlogParam("카카오", KakaoSortType.ACCURACY.name(), 1, 51);
        });
        assertThat(exception.getMessage()).isEqualTo(SIZE_ERROR_MESSAGE);
    }
}
