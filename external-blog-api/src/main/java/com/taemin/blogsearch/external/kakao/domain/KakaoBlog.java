package com.taemin.blogsearch.external.kakao.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class KakaoBlog {
    private KakaoDocuments documents;
    private KakaoMeta meta;

    @JsonCreator
    public KakaoBlog(@JsonProperty("documents") List<KakaoDocument> documents, @JsonProperty("meta") KakaoMeta meta) {
        this.documents = new KakaoDocuments(documents);
        this.meta = meta;
    }
}
