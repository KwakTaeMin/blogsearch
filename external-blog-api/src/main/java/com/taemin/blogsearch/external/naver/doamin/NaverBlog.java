package com.taemin.blogsearch.external.naver.doamin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class NaverBlog {

    private Date lastBuildDate;
    private int total;
    private int start;
    private int display;
    private NaverItems items;

    @JsonCreator
    public NaverBlog(@JsonProperty("lastBuildDate") Date lastBuildDate,
                     @JsonProperty("total") int total,
                     @JsonProperty("start") int start,
                     @JsonProperty("display") int display,
                     @JsonProperty("items") List<NaverItem> items) {
        this.lastBuildDate = lastBuildDate;
        this.total = total;
        this.start = start;
        this.display = display;
        this.items = new NaverItems(items);
    }
}
