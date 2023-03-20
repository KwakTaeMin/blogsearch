package com.taemin.blogsearch.external.naver.doamin;

public enum NaverSortType {

    SIM("similar"),
    DATE("date desc");

    private String description;

    NaverSortType(String description) {
        this.description = description;
    }
}
