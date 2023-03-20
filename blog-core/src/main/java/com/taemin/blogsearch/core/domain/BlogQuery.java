package com.taemin.blogsearch.core.domain;

import com.taemin.blogsearch.external.kakao.domain.KakaoSearchBlogParam;
import com.taemin.blogsearch.external.naver.doamin.NaverSearchBlogParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BlogQuery {

    private String query;
    private SortType sort;
    private int page;
    private int size;

    @Builder
    public BlogQuery(String query, SortType sort, int page, int size) {
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    public KakaoSearchBlogParam toKakaoSearchParam() {
        return new KakaoSearchBlogParam(query, sort.getKakaoType(), page, size);
    }

    public NaverSearchBlogParam toNaverSearchBlogParam() {
        return new NaverSearchBlogParam(query, sort.getNaverType(), page, size);
    }

}
