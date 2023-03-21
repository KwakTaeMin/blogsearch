package com.taemin.blogsearch.core.domain;

import com.taemin.blogsearch.external.kakao.domain.KakaoDocuments;
import com.taemin.blogsearch.external.naver.doamin.NaverItems;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Blogs {

    private List<Blog> blogs;

    public Blogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public static Blogs of(KakaoDocuments kakaoDocuments) {
        return new Blogs(kakaoDocuments.getKakaoDocuments()
                .stream()
                .map(Blog::new)
                .collect(Collectors.toList()));
    }

    public static Blogs of(NaverItems naverItems) {
        return new Blogs(naverItems.getNaverItems()
                .stream()
                .map(Blog::new)
                .collect(Collectors.toList()));
    }

    public List<Blog> getBlogs() {
        return Collections.unmodifiableList(this.blogs);
    }

    public int size() {
        return blogs.size();
    }
}
