package com.taemin.blogsearch.external.kakao.domain;

public enum KakaoSortType {

    ACCURACY("accuracy"),
    RECENCY("recency");

    public String description;

    KakaoSortType(String description) {
        this.description = description;
    }

    public static boolean isExist(String sort) {
        sort = sort.toLowerCase();
        if(ACCURACY.name().toLowerCase().equals(sort))
            return true;
        if(RECENCY.name().toLowerCase().equals(sort))
            return true;
        return false;
    }
}
