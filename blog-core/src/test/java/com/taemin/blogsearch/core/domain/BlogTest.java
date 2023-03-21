package com.taemin.blogsearch.core.domain;

import com.taemin.blogsearch.core.converter.DateConverter;
import com.taemin.blogsearch.external.kakao.domain.KakaoDocument;
import com.taemin.blogsearch.external.naver.doamin.NaverItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Blog 도메인 테스트")
public class BlogTest {

    @Test
    public void Blog_KakaoDocument_생성_테스트() {
        KakaoDocument kakaoDocument = KakaoDocument.builder()
                .blogName("name")
                .contents("contents")
                .title("title")
                .datetime(new Date())
                .thumbnail("thumbnail")
                .url("url")
                .build();
        Blog blog = new Blog(kakaoDocument);

        assertThat(blog.getBlogName()).isEqualTo(kakaoDocument.getBlogName());
        assertThat(blog.getContent()).isEqualTo(kakaoDocument.getContents());
        assertThat(blog.getTitle()).isEqualTo(kakaoDocument.getTitle());
        assertThat(blog.getCreateDate()).isEqualTo(kakaoDocument.getDatetime());
        assertThat(blog.getUrl()).isEqualTo(kakaoDocument.getUrl());
    }

    @Test
    public void Blog_NaverItem_생성_테스트() {
        NaverItem naverItem = NaverItem.builder()
                .bloggerName("name")
                .description("description")
                .link("link")
                .postDate("20220101")
                .title("title")
                .bloggerLink("bloggerLink")
                .build();
        Blog blog = new Blog(naverItem);

        assertThat(blog.getBlogName()).isEqualTo(naverItem.getBloggerName());
        assertThat(blog.getContent()).isEqualTo(naverItem.getDescription());
        assertThat(blog.getTitle()).isEqualTo(naverItem.getTitle());
        assertThat(blog.getCreateDate()).isEqualTo(DateConverter.toDateNaverPostDate(naverItem.getPostDate()));
        assertThat(blog.getUrl()).isEqualTo(naverItem.getLink());
    }

}
