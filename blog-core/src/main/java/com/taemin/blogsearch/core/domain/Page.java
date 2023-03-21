package com.taemin.blogsearch.core.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Page {

    private final int currentPage;
    private final int displayPerPage;
    private final int totalCount;

    @Builder
    public Page(int currentPage, int displayPerPage, int totalCount) {
        this.currentPage = currentPage;
        this.displayPerPage = displayPerPage;
        this.totalCount = totalCount;
    }

    public static Page of(int totalCount, BlogQuery blogQuery) {
        return Page.builder()
                .currentPage(blogQuery.getPage())
                .displayPerPage(blogQuery.getSize())
                .totalCount(totalCount)
                .build();
    }
}
