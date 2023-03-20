package com.taemin.blogsearch.core.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    private static final String NAVER_POST_DATE_FORMAT = "yyyyMMdd";
    private static final SimpleDateFormat naverPostDateFormat = new SimpleDateFormat(NAVER_POST_DATE_FORMAT);

    public static Date toDateNaverPostDate(String postDate){
        try {
            return naverPostDateFormat.parse(postDate);
        } catch (ParseException parseException) {
            throw new IllegalArgumentException();
        }
    }
}
