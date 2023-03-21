package com.taemin.blogsearch.external.naver.doamin;

public enum NaverSortType {

    SIM("similar"),
    DATE("date desc");

    private String description;

    NaverSortType(String description) {
        this.description = description;
    }

    public static boolean isExist(String sort) {
        sort = sort.toLowerCase();
        if(SIM.name().toLowerCase().equals(sort))
            return true;
        if(DATE.name().toLowerCase().equals(sort))
            return true;
        return false;
    }
}
