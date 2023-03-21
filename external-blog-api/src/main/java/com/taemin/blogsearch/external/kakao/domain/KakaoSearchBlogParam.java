package com.taemin.blogsearch.external.kakao.domain;

import com.taemin.blogsearch.external.kakao.exception.KakaoParamException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class KakaoSearchBlogParam {

    private String query;
    private String sort;
    private int page;
    private int size;

    private static final int MIN_PAGE_COUNT = 1;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 50;

    private static final String QUERY_ERROR_MESSAGE = "질의어는 필수 값 입니다.";
    private static final String SORT_ERROR_MESSAGE = "정렬 방식은 필수 값 혹은 정렬 방식이 맞지 않습니다.";
    private static final String PAGE_ERROR_MESSAGE = "페이지 최소 값은 1 이상 입니다.";
    private static final String SIZE_ERROR_MESSAGE = "페이지 당 보여줄 수 있는 개수는 최소 1 최대 50 입니다.";

    public KakaoSearchBlogParam(String query, String sort, int page, int size) {
        validate(query, sort, page, size);
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    private void validate(String query, String sort, int page, int size) {
        if (Objects.isNull(query) || query.isEmpty())
            throw new KakaoParamException(QUERY_ERROR_MESSAGE);
        if (Objects.isNull(sort) || sort.isEmpty() || !KakaoSortType.isExist(sort))
            throw new KakaoParamException(SORT_ERROR_MESSAGE);
        if (page < MIN_PAGE_COUNT)
            throw new KakaoParamException(PAGE_ERROR_MESSAGE);
        if (size < MIN_SIZE || size > MAX_SIZE)
            throw new KakaoParamException(SIZE_ERROR_MESSAGE);
    }

}
