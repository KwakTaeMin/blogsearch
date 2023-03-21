package com.taemin.blogsearch.core.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KeywordSearchRank {

    private final List<KeywordSearchCount> keywordSearchRank;
    private static final int MAX_RANK = 10;
    public KeywordSearchRank(List<KeywordSearchCount> keywordSearchCounts) {
        this.keywordSearchRank = keywordSearchCounts.stream()
                .sorted()
                .limit(MAX_RANK)
                .collect(Collectors.toList());
    }

    public List<KeywordSearchCount> getKeywordSearchRank() {
        return Collections.unmodifiableList(this.keywordSearchRank);
    }

    public int size() {
        return this.keywordSearchRank.size();
    }
}
