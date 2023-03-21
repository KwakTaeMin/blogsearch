package com.taemin.blogsearch.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum SortType {
    ACCURACY("accuracy", "sim"),
    RECENCY("recency", "date");

    private String kakaoType;
    private String naverType;

    SortType(String kakaoType, String naverType) {
        this.kakaoType = kakaoType;
        this.naverType = naverType;
    }

    public static SortType of(String sortType) {
        if(sortType.equals(RECENCY.kakaoType))
            return SortType.RECENCY;
        return SortType.ACCURACY;
    }
}
