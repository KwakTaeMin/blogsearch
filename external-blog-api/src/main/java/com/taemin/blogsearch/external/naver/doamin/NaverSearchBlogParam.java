package com.taemin.blogsearch.external.naver.doamin;

import com.taemin.blogsearch.external.naver.exception.NaverParamException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
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
    private static final String SORT_ERROR_MESSAGE = "종류에 존재 하지 않는 정렬 방식입니다.";
    private static final String PAGE_ERROR_MESSAGE = "페이지 최소 값은 1, 최대값 100입니다.";
    private static final String SIZE_ERROR_MESSAGE = "페이지 당 보여줄 수 있는 개수는 최소 1 최대 100 입니다.";

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
        if (Objects.isNull(NaverSortType.valueOf(sort)))
            throw new NaverParamException(SORT_ERROR_MESSAGE);
        if (start < MIN_START || start > MAX_START)
            throw new NaverParamException(PAGE_ERROR_MESSAGE);
        if (display < MIN_DISPLAY || display > MAX_DISPLAY)
            throw new NaverParamException(SIZE_ERROR_MESSAGE);
    }

}
