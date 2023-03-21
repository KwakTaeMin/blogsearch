package com.taemin.blogsearch.external.naver.doamin;

import com.taemin.blogsearch.external.naver.exception.NaverParamException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
public class NaverSearchBlogParam {

    private String query;
    private int display;
    private int start;
    private String sort;

    private static final int MIN_START = 1;
    private static final int MAX_START = 100;
    private static final int MIN_DISPLAY = 1;
    private static final int MAX_DISPLAY = 100;

    private static final String QUERY_ERROR_MESSAGE = "질의어는 필수 값 입니다.";
    private static final String SORT_ERROR_MESSAGE = "정렬 방식은 필수 값 혹은 정렬 방식이 맞지 않습니다.";
    private static final String START_ERROR_MESSAGE = "페이지 최소 값은 1, 최대값 100입니다.";
    private static final String DISPLAY_ERROR_MESSAGE = "페이지 당 보여줄 수 있는 개수는 최소 1 최대 100 입니다.";

    public NaverSearchBlogParam(String query, String sort, int start, int display) {
        validate(query, sort, start, display);
        this.query = query;
        this.display = display;
        this.start = start;
        this.sort = sort;
    }

    private void validate(String query, String sort, int start, int display) {
        if (Objects.isNull(query) || query.isEmpty())
            throw new NaverParamException(QUERY_ERROR_MESSAGE);
        if (Objects.isNull(sort) || sort.isEmpty() || !NaverSortType.isExist(sort))
            throw new NaverParamException(SORT_ERROR_MESSAGE);
        if (start < MIN_START || start > MAX_START)
            throw new NaverParamException(START_ERROR_MESSAGE);
        if (display < MIN_DISPLAY || display > MAX_DISPLAY)
            throw new NaverParamException(DISPLAY_ERROR_MESSAGE);
    }

}
