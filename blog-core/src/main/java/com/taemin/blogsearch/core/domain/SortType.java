package com.taemin.blogsearch.core.domain;

import lombok.Getter;

@Getter
public enum SortType {
    ACCURACY("accuracy", "sim"),
    RECENCY("recency", "date");

    private final String kakaoType;
    private final String naverType;

    SortType(String kakaoType, String naverType) {
        this.kakaoType = kakaoType;
        this.naverType = naverType;
    }

    public static SortType of(String sortType) {
        if(sortType.equals(RECENCY.kakaoType))
            return SortType.RECENCY;
        return SortType.ACCURACY;
    }

    public static boolean isExist(SortType sortType) {
        if(ACCURACY.equals(sortType))
            return true;
        if(RECENCY.equals(sortType))
            return true;
        return false;
    }
}
