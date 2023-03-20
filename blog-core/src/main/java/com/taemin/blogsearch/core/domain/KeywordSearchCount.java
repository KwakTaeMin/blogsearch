package com.taemin.blogsearch.core.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class KeywordSearchCount implements Comparable<KeywordSearchCount> {

    @Id
    @Column
    private String keyword;

    @Column
    private int count;

    @Builder
    public KeywordSearchCount(String keyword, int count) {
        this.keyword = keyword;
        this.count = count;
    }

    public void plusCount() {
        this.count++;
    }

    @Override
    public int compareTo(KeywordSearchCount other) {
        return other.count - this.count;
    }
}
