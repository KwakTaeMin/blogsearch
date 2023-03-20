package com.taemin.blogsearch.external.kakao.domain;

public enum KakaoSortType {

    ACCURACY("accuracy"),
    RECENCY("recency");

    public String description;

    KakaoSortType(String description) {
        this.description = description;
    }
}
