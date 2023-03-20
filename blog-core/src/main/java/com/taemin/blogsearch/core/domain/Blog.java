package com.taemin.blogsearch.core.domain;

import com.taemin.blogsearch.core.converter.DateConverter;
import com.taemin.blogsearch.external.kakao.domain.KakaoDocument;
import com.taemin.blogsearch.external.naver.doamin.NaverItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class Blog {

    private String title;
    private String content;
    private Date createDate;
    private String url;
    private String blogName;

    public Blog(KakaoDocument kakaoDocument) {
        this.title = kakaoDocument.getTitle();
        this.content = kakaoDocument.getContents();
        this.createDate = kakaoDocument.getDatetime();
        this.url = kakaoDocument.getUrl();
        this.blogName = kakaoDocument.getBlogName();
    }

    public Blog(NaverItem naverItem) {
        this.title = naverItem.getTitle();
        this.content = naverItem.getDescription();
        this.createDate = DateConverter.toDateNaverPostDate(naverItem.getPostDate());
        this.url = naverItem.getBloggerLink();
        this.blogName = naverItem.getBloggerName();
    }

}
