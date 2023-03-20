package com.taemin.blogsearch.external.kakao.domain;

import java.util.Collections;
import java.util.List;

public class KakaoDocuments {
    private List<KakaoDocument> kakaoDocuments;

    public KakaoDocuments(List<KakaoDocument> kakaoDocuments) {
        this.kakaoDocuments = kakaoDocuments;
    }

    public List<KakaoDocument> getKakaoDocuments() {
        return Collections.unmodifiableList(this.kakaoDocuments);
    }
}
