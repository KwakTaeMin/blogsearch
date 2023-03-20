package com.taemin.blogsearch.external.naver.doamin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NaverItem {

    private String title;
    private String link;
    private String description;
    @JsonProperty("bloggername")
    private String bloggerName;
    @JsonProperty("bloggerlink")
    private String bloggerLink;
    @JsonProperty("postdate")
    private String postDate;


}
