package com.taemin.blogsearch.external.naver.doamin;

import java.util.Collections;
import java.util.List;

public class NaverItems {

    private List<NaverItem> naverItems;

    public NaverItems(List<NaverItem> naverItems) {
        this.naverItems = naverItems;
    }

    public List<NaverItem> getNaverItems() {
        return Collections.unmodifiableList(this.naverItems);
    }
}
