package com.taemin.blogsearch.external.kakao.doamin;

import com.taemin.blogsearch.external.kakao.domain.KakaoSearchBlogParam;
import com.taemin.blogsearch.external.kakao.domain.KakaoSortType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KakaoSearchBlogParamTest {

    @Test
    public void KakaoSearchBlogParam_생성_테스트() {
        KakaoSearchBlogParam kakaoSearchBlogParam = new KakaoSearchBlogParam("질의", KakaoSortType.ACCURACY.name(), 1, 10);
        assertThat(kakaoSearchBlogParam.getQuery()).isEqualTo("질의");
        assertThat(kakaoSearchBlogParam.getSort()).isEqualTo(KakaoSortType.ACCURACY.name());
        assertThat(kakaoSearchBlogParam.getPage()).isEqualTo(1);
        assertThat(kakaoSearchBlogParam.getSize()).isEqualTo(10);
    }
}
