package com.taemin.blogsearch.core.repository;

import com.taemin.blogsearch.core.domain.KeywordSearchCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordSearchCountRepository extends JpaRepository<KeywordSearchCount, String> {
}
