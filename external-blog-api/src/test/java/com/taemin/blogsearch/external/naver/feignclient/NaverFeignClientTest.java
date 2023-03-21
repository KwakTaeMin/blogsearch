package com.taemin.blogsearch.external.naver.feignclient;

import com.taemin.blogsearch.external.naver.doamin.NaverBlog;
import com.taemin.blogsearch.external.naver.doamin.NaverSearchBlogParam;
import com.taemin.blogsearch.external.naver.doamin.NaverSortType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class NaverFeignClientTest {

    @Autowired
    private NaverFeignClient naverFeignClient;

    @Test
    public void 네이버_블로그_정확도_순_조회_테스트() {
        NaverSearchBlogParam naverSearchBlogParam = new NaverSearchBlogParam("애플", NaverSortType.SIM.name(), 1, 10);
        NaverBlog naverBlog = naverFeignClient.searchBlog(naverSearchBlogParam);
        assertThat(naverBlog).isNotNull();
        assertThat(naverBlog.getStart()).isEqualTo(1);
        assertThat(naverBlog.getTotal()).isNotNull();
        assertThat(naverBlog.getDisplay()).isGreaterThan(1);
        assertThat(naverBlog.getLastBuildDate()).isNotNull();
        assertThat(naverBlog.getItems()).isNotNull();
        naverBlog.getItems().getNaverItems().forEach(naverItem -> {
            assertThat(naverItem.getBloggerLink()).isNotEmpty();
            assertThat(naverItem.getTitle()).isNotEmpty();
            assertThat(naverItem.getLink()).isNotEmpty();
            assertThat(naverItem.getDescription()).isNotEmpty();
            assertThat(naverItem.getPostDate()).isNotEmpty();
            assertThat(naverItem.getBloggerName()).isNotEmpty();
        });
    }

    @Test
    public void 네이버_블로그_최근_순_조회_테스트() {
        NaverSearchBlogParam naverSearchBlogParam = new NaverSearchBlogParam("애플", NaverSortType.DATE.name(), 1, 10);
        NaverBlog naverBlog = naverFeignClient.searchBlog(naverSearchBlogParam);
        assertThat(naverBlog).isNotNull();
        assertThat(naverBlog.getStart()).isEqualTo(1);
        assertThat(naverBlog.getTotal()).isNotNull();
        assertThat(naverBlog.getDisplay()).isGreaterThan(1);
        assertThat(naverBlog.getLastBuildDate()).isNotNull();
        assertThat(naverBlog.getItems()).isNotNull();
        naverBlog.getItems().getNaverItems().forEach(naverItem -> {
            assertThat(naverItem.getBloggerLink()).isNotEmpty();
            assertThat(naverItem.getTitle()).isNotEmpty();
            assertThat(naverItem.getLink()).isNotEmpty();
            assertThat(naverItem.getDescription()).isNotEmpty();
            assertThat(naverItem.getPostDate()).isNotEmpty();
            assertThat(naverItem.getBloggerName()).isNotEmpty();
        });
    }

    @Test
    public void 네이버_블로그_Null_파라미터_조회_테스트() {
        assertThrows(NullPointerException.class, () -> naverFeignClient.searchBlog(null));
    }


}
