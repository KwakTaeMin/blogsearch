package com.taemin.blogsearch.core.domain;

import com.taemin.blogsearch.external.kakao.domain.KakaoDocument;
import com.taemin.blogsearch.external.naver.doamin.NaverItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PageableBlogs 도메인 테스트")
public class PageableBlogsTest {

    @Test
    public void PageableBlogs_생성_테스트() {
        Page page = new Page(1, 10, 514);
        KakaoDocument kakaoDocument = KakaoDocument.builder()
                .blogName("name")
                .contents("contents")
                .title("title")
                .datetime(new Date())
                .thumbnail("thumbnail")
                .url("url")
                .build();
        Blog kakaoBlog = new Blog(kakaoDocument);

        NaverItem naverItem = NaverItem.builder()
                .bloggerName("name")
                .description("description")
                .link("link")
                .postDate("20220101")
                .title("title")
                .bloggerLink("bloggerLink")
                .build();
        Blog naverBlogs = new Blog(naverItem);
        Blogs blogs = new Blogs(Arrays.asList(kakaoBlog, naverBlogs));

        PageableBlogs pageableBlogs = new PageableBlogs(page, blogs);
        assertThat(pageableBlogs).isNotNull();
        assertThat(pageableBlogs.getBlogs().size()).isEqualTo(2);
        assertThat(pageableBlogs.getPage()).isNotNull();
        assertThat(pageableBlogs.getPage().getTotalPage()).isEqualTo(52);

    }
}
