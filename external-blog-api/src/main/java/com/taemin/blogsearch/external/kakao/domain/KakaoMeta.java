package com.taemin.blogsearch.external.kakao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoMeta {

    @JsonProperty("is_end")
    private boolean isEnd;

    @JsonProperty("pageable_count")
    private int pageableCount;

    @JsonProperty("total_count")
    private int totalCount;

    public KakaoMeta(boolean isEnd, int pageableCount, int totalCount) {
        this.isEnd = isEnd;
        this.pageableCount = pageableCount;
        this.totalCount = totalCount;
    }
}
