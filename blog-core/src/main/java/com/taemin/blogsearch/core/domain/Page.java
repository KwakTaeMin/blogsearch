package com.taemin.blogsearch.core.domain;

import com.taemin.blogsearch.external.kakao.domain.KakaoMeta;
import com.taemin.blogsearch.external.naver.doamin.NaverBlog;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Page {

    private int currentPage;
    private int displayPerPage;
    private int totalCount;

    @Builder
    public Page(int currentPage, int displayPerPage, int totalCount) {
        this.currentPage = currentPage;
        this.displayPerPage = displayPerPage;
        this.totalCount = totalCount;
    }

    public static Page of(KakaoMeta kakaoMeta, BlogQuery blogQuery) {
        return Page.builder()
                .currentPage(blogQuery.getPage())
                .displayPerPage(blogQuery.getSize())
                .totalCount(kakaoMeta.getTotalCount())
                .build();
    }

    public static Page of(int totalCount, BlogQuery blogQuery) {
        return Page.builder()
                .currentPage(blogQuery.getPage())
                .displayPerPage(blogQuery.getSize())
                .totalCount(totalCount)
                .build();
    }
}
