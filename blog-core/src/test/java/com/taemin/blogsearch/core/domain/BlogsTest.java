package com.taemin.blogsearch.core.domain;

import com.taemin.blogsearch.core.converter.DateConverter;
import com.taemin.blogsearch.external.kakao.domain.KakaoDocument;
import com.taemin.blogsearch.external.kakao.domain.KakaoDocuments;
import com.taemin.blogsearch.external.naver.doamin.NaverItem;
import com.taemin.blogsearch.external.naver.doamin.NaverItems;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Blogs 도메인 테스트")
public class BlogsTest {

    @Test
    public void Blogs_생성_테스트() {
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

        assertThat(blogs).isNotNull();
        assertThat(blogs.size()).isEqualTo(2);
    }

    @Test
    public void Blogs_unmodifiableList_테스트() {
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
        assertThrows(UnsupportedOperationException.class, () -> blogs.getBlogs().add(kakaoBlog));
    }

    @Test
    public void Blogs_KakaoDocuments_of_테스트() {
        KakaoDocument kakaoDocumentOne = KakaoDocument.builder()
                .blogName("name")
                .contents("contents")
                .title("title")
                .datetime(new Date())
                .thumbnail("thumbnail")
                .url("url")
                .build();

        KakaoDocument kakaoDocumentTwo = KakaoDocument.builder()
                .blogName("name")
                .contents("contents")
                .title("title")
                .datetime(new Date())
                .thumbnail("thumbnail")
                .url("url")
                .build();

        KakaoDocuments kakaoDocuments = new KakaoDocuments(Arrays.asList(kakaoDocumentOne, kakaoDocumentTwo));
        Blogs kakaoBlogs = Blogs.of(kakaoDocuments);
        assertThat(kakaoBlogs).isNotNull();
        assertThat(kakaoBlogs.size()).isEqualTo(2);
        kakaoBlogs.getBlogs().forEach(blog -> {
            assertThat(blog).isNotNull();
            assertThat(blog.getBlogName()).isEqualTo(kakaoDocumentOne.getBlogName());
            assertThat(blog.getContent()).isEqualTo(kakaoDocumentOne.getContents());
            assertThat(blog.getTitle()).isEqualTo(kakaoDocumentOne.getTitle());
            assertThat(blog.getUrl()).isEqualTo(kakaoDocumentOne.getUrl());
            assertThat(blog.getCreateDate()).isEqualTo(kakaoDocumentOne.getDatetime());
        });
    }

    @Test
    public void Blogs_NaverItems_of_테스트() {
        NaverItem naverItemOne = NaverItem.builder()
                .bloggerName("name")
                .description("description")
                .link("link")
                .postDate("20220101")
                .title("title")
                .bloggerLink("bloggerLink")
                .build();

        NaverItem naverItemTwo = NaverItem.builder()
                .bloggerName("name")
                .description("description")
                .link("link")
                .postDate("20220101")
                .title("title")
                .bloggerLink("bloggerLink")
                .build();

        NaverItems naverItems = new NaverItems(Arrays.asList(naverItemOne, naverItemTwo));
        Blogs kakaoBlogs = Blogs.of(naverItems);
        assertThat(kakaoBlogs).isNotNull();
        assertThat(kakaoBlogs.size()).isEqualTo(2);
        kakaoBlogs.getBlogs().forEach(blog -> {
            assertThat(blog).isNotNull();
            assertThat(blog.getBlogName()).isEqualTo(naverItemOne.getBloggerName());
            assertThat(blog.getContent()).isEqualTo(naverItemOne.getDescription());
            assertThat(blog.getTitle()).isEqualTo(naverItemOne.getTitle());
            assertThat(blog.getUrl()).isEqualTo(naverItemOne.getLink());
            assertThat(blog.getCreateDate()).isEqualTo(DateConverter.toDateNaverPostDate(naverItemOne.getPostDate()));
        });
    }
}
