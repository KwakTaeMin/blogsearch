package com.taemin.blogsearch.external.naver.doamin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
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

    @Builder
    public NaverItem(String title, String link, String description, String bloggerName, String bloggerLink, String postDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.bloggerName = bloggerName;
        this.bloggerLink = bloggerLink;
        this.postDate = postDate;
    }
}
