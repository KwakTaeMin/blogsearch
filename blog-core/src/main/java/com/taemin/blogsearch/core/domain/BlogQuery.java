package com.taemin.blogsearch.core.domain;

import com.taemin.blogsearch.core.exception.BlogQueryException;
import com.taemin.blogsearch.external.kakao.domain.KakaoSearchBlogParam;
import com.taemin.blogsearch.external.naver.doamin.NaverSearchBlogParam;
import lombok.Getter;

import java.util.Objects;

@Getter
public class BlogQuery {

    private final String query;
    private final SortType sort;
    private final int page;
    private final int size;

    private static final int MIN_PAGE_COUNT = 1;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 50;

    private static final String QUERY_ERROR_MESSAGE = "질의어는 필수 값 입니다.";
    private static final String SORT_ERROR_MESSAGE = "정렬 방식은 필수 값 혹은 정렬 방식이 맞지 않습니다.";
    private static final String PAGE_ERROR_MESSAGE = "페이지 최소 값은 1 이상 입니다.";
    private static final String SIZE_ERROR_MESSAGE = "페이지 당 보여줄 수 있는 개수는 최소 1 최대 50 입니다.";

    public BlogQuery(String query, SortType sort, int page, int size) {
        validate(query, sort, page, size);
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    private void validate(String query, SortType sort, int page, int size) {
        if (Objects.isNull(query) || query.isEmpty())
            throw new BlogQueryException(QUERY_ERROR_MESSAGE);
        if (Objects.isNull(sort) || !SortType.isExist(sort))
            throw new BlogQueryException(SORT_ERROR_MESSAGE);
        if (page < MIN_PAGE_COUNT)
            throw new BlogQueryException(PAGE_ERROR_MESSAGE);
        if (size < MIN_SIZE || size > MAX_SIZE)
            throw new BlogQueryException(SIZE_ERROR_MESSAGE);
    }

    public KakaoSearchBlogParam toKakaoSearchParam() {
        return new KakaoSearchBlogParam(query, sort.getKakaoType(), page, size);
    }

    public NaverSearchBlogParam toNaverSearchBlogParam() {
        return new NaverSearchBlogParam(query, sort.getNaverType(), page, size);
    }

}
