package com.taemin.blogsearch.core.domain;

import com.taemin.blogsearch.core.exception.BlogQueryException;
import com.taemin.blogsearch.external.kakao.domain.KakaoSearchBlogParam;
import com.taemin.blogsearch.external.naver.doamin.NaverSearchBlogParam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BlogQuery 도메인 테스트")
public class BlogQueryTest {

    private static final String QUERY_ERROR_MESSAGE = "질의어는 필수 값 입니다.";
    private static final String SORT_ERROR_MESSAGE = "정렬 방식은 필수 값 혹은 정렬 방식이 맞지 않습니다.";
    private static final String PAGE_ERROR_MESSAGE = "페이지 최소 값은 1 이상 입니다.";
    private static final String SIZE_ERROR_MESSAGE = "페이지 당 보여줄 수 있는 개수는 최소 1 최대 50 입니다.";
    
    @Test
    public void BlogQuery_생성_테스트() {
        BlogQuery blogQuery = new BlogQuery("카카오", SortType.ACCURACY, 1, 10);
        assertThat(blogQuery.getQuery()).isEqualTo("카카오");
        assertThat(blogQuery.getSort()).isEqualTo(SortType.ACCURACY);
        assertThat(blogQuery.getPage()).isEqualTo(1);
        assertThat(blogQuery.getSize()).isEqualTo(10);
    }

    @Test
    public void BlogQuery_toKakaoSearchParam_생성_테스트() {
        BlogQuery blogQuery = new BlogQuery("카카오", SortType.ACCURACY, 1, 10);
        KakaoSearchBlogParam kakaoSearchBlogParam = blogQuery.toKakaoSearchParam();
        assertThat(kakaoSearchBlogParam.getQuery()).isEqualTo("카카오");
        assertThat(kakaoSearchBlogParam.getSort()).isEqualTo(SortType.ACCURACY.getKakaoType());
        assertThat(kakaoSearchBlogParam.getPage()).isEqualTo(1);
        assertThat(kakaoSearchBlogParam.getSize()).isEqualTo(10);
    }

    @Test
    public void BlogQuery_toNaverSearchBlogParam_생성_테스트() {
        BlogQuery blogQuery = new BlogQuery("카카오", SortType.ACCURACY, 1, 10);
        NaverSearchBlogParam naverSearchBlogParam = blogQuery.toNaverSearchBlogParam();
        assertThat(naverSearchBlogParam.getQuery()).isEqualTo("카카오");
        assertThat(naverSearchBlogParam.getSort()).isEqualTo(SortType.ACCURACY.getNaverType());
        assertThat(naverSearchBlogParam.getStart()).isEqualTo(1);
        assertThat(naverSearchBlogParam.getDisplay()).isEqualTo(10);
    }

    @Test
    public void BlogQuery_생성_테스트_질의어_빈_값인_경우() {
        Throwable exception = assertThrows(BlogQueryException.class, () -> new BlogQuery("", SortType.ACCURACY, 1, 10));
        assertThat(exception.getMessage()).isEqualTo(QUERY_ERROR_MESSAGE);
    }

    @Test
    public void BlogQuery_생성_테스트_질의어_NULL_값인_경우() {
        Throwable exception = assertThrows(BlogQueryException.class, () -> new BlogQuery("", SortType.ACCURACY, 1, 10));
        assertThat(exception.getMessage()).isEqualTo(QUERY_ERROR_MESSAGE);
    }

    @Test
    public void BlogQuery_생성_테스트_Sort_타입이_NULL_경우() {
        Throwable exception = assertThrows(BlogQueryException.class, () -> new BlogQuery("카카오", null, 1, 10));
        assertThat(exception.getMessage()).isEqualTo(SORT_ERROR_MESSAGE);
    }

    @Test
    public void BlogQuery_생성_테스트_Page_1보다_작은_경우() {
        Throwable exception = assertThrows(BlogQueryException.class, () -> new BlogQuery("카카오", SortType.ACCURACY, 0, 10));
        assertThat(exception.getMessage()).isEqualTo(PAGE_ERROR_MESSAGE);
    }

    @Test
    public void BlogQuery_생성_테스트_Size_1보다_작은_경우() {
        Throwable exception = assertThrows(BlogQueryException.class, () -> new BlogQuery("카카오", SortType.ACCURACY, 1, 0));
        assertThat(exception.getMessage()).isEqualTo(SIZE_ERROR_MESSAGE);
    }

    @Test
    public void BlogQuery_생성_테스트_Size_50보다_큰_경우() {
        Throwable exception = assertThrows(BlogQueryException.class, () -> new BlogQuery("카카오", SortType.ACCURACY, 1, 51));
        assertThat(exception.getMessage()).isEqualTo(SIZE_ERROR_MESSAGE);
    }

}
