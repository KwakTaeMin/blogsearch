package com.taemin.blogsearch.external.kakao.feignclient;


import com.taemin.blogsearch.external.kakao.domain.KakaoBlog;
import com.taemin.blogsearch.external.kakao.domain.KakaoDocument;
import com.taemin.blogsearch.external.kakao.domain.KakaoSearchBlogParam;
import com.taemin.blogsearch.external.kakao.domain.KakaoSortType;
import com.taemin.blogsearch.external.kakao.exception.KakaoParamException;
import com.taemin.blogsearch.external.kakao.feignclient.KakaoFeignClient;
import com.taemin.blogsearch.external.naver.doamin.NaverSearchBlogParam;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class KakaoFeignClientTest {

    @Autowired
    private KakaoFeignClient kakaoFeignClient;


    @Test
    public void 카카오_블로그_조회_테스트_정확도_순() {
        KakaoSearchBlogParam kakaoSearchBlogParam = new KakaoSearchBlogParam("애플", KakaoSortType.ACCURACY.name(), 1, 10);
        KakaoBlog kakaoBlog = kakaoFeignClient.searchBlog(kakaoSearchBlogParam);
        List<KakaoDocument> kakaoDocumentList = kakaoBlog.getDocuments().getKakaoDocuments();
        assertThat(kakaoBlog).isNotNull();
        assertThat(kakaoBlog.getDocuments()).isNotNull();
        assertThat(kakaoBlog.getDocuments().getKakaoDocuments().size()).isEqualTo(10);
        kakaoDocumentList.forEach(kakaoDocument -> {
            assertThat(kakaoDocument.getBlogName()).isNotEmpty();
            assertThat(kakaoDocument.getUrl()).isNotEmpty();
            assertThat(kakaoDocument.getContents()).isNotEmpty();
            assertThat(kakaoDocument.getTitle()).isNotEmpty();
            assertThat(kakaoDocument.getDatetime()).isNotNull();
            assertThat(kakaoDocument.getThumbnail()).isNotNull();
        });
        assertThat(kakaoBlog.getMeta()).isNotNull();
        assertThat(kakaoBlog.getMeta().getPageableCount()).isNotNull();
        assertThat(kakaoBlog.getMeta().getTotalCount()).isNotNull();
        assertThat(kakaoBlog.getMeta().isEnd()).isFalse();
    }

    @Test
    public void 카카오_블로그_조회_테스트_최근_순() {
        KakaoSearchBlogParam kakaoSearchBlogParam = new KakaoSearchBlogParam("애플", KakaoSortType.RECENCY.name(), 1, 10);
        KakaoBlog kakaoBlog = kakaoFeignClient.searchBlog(kakaoSearchBlogParam);
        List<KakaoDocument> kakaoDocumentList = kakaoBlog.getDocuments().getKakaoDocuments();
        assertThat(kakaoBlog).isNotNull();
        assertThat(kakaoBlog.getDocuments()).isNotNull();
        assertThat(kakaoBlog.getDocuments().getKakaoDocuments().size()).isEqualTo(10);
        kakaoDocumentList.forEach(kakaoDocument -> {
            assertThat(kakaoDocument.getBlogName()).isNotEmpty();
            assertThat(kakaoDocument.getUrl()).isNotEmpty();
            assertThat(kakaoDocument.getContents()).isNotEmpty();
            assertThat(kakaoDocument.getTitle()).isNotEmpty();
            assertThat(kakaoDocument.getDatetime()).isNotNull();
            assertThat(kakaoDocument.getThumbnail()).isNotNull();
        });
        assertThat(kakaoBlog.getMeta()).isNotNull();
        assertThat(kakaoBlog.getMeta().getPageableCount()).isNotNull();
        assertThat(kakaoBlog.getMeta().getTotalCount()).isNotNull();
        assertThat(kakaoBlog.getMeta().isEnd()).isFalse();
    }

    @Test
    public void 카카오_블로그_Null_파라미터_조회_테스트() {
        assertThrows(NullPointerException.class, () -> {
            kakaoFeignClient.searchBlog(null);
        });
    }

}
