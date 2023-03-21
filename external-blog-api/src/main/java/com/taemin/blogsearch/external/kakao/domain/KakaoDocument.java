package com.taemin.blogsearch.external.kakao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
public class KakaoDocument {

    @JsonProperty("blogname")
    private String blogName;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("datetime")
    private Date datetime;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @Builder
    public KakaoDocument(String blogName, String contents, Date datetime, String thumbnail, String title, String url) {
        this.blogName = blogName;
        this.contents = contents;
        this.datetime = datetime;
        this.thumbnail = thumbnail;
        this.title = title;
        this.url = url;
    }
}
