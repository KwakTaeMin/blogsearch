package com.taemin.blogsearch.external.kakao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
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
}
