package com.taemin.blogsearch.core.service;

import com.taemin.blogsearch.core.anotation.KeywordLog;
import com.taemin.blogsearch.core.domain.*;
import com.taemin.blogsearch.core.repository.KeywordSearchCountRepository;
import com.taemin.blogsearch.external.kakao.domain.KakaoBlog;
import com.taemin.blogsearch.external.kakao.feignclient.KakaoFeignClient;
import com.taemin.blogsearch.external.naver.doamin.NaverBlog;
import com.taemin.blogsearch.external.naver.feignclient.NaverFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final KakaoFeignClient kakaoFeignClient;
    private final NaverFeignClient naverFeignClient;

    private final KeywordSearchCountRepository keywordSearchCountRepository;

    @KeywordLog
    public PageableBlogs getSearchBlog(BlogQuery blogQuery) {
        try {
            return getKakaoBlogData(blogQuery);
        } catch (Exception exception) {
            return getNaverBlogData(blogQuery);
        }
    }

    @Cacheable(cacheNames = "getSearchRank")
    public List<KeywordSearchCount> getSearchRank() {
        KeywordSearchRank keywordSearchRank = new KeywordSearchRank(keywordSearchCountRepository.findAll());
        return keywordSearchRank.getKeywordSearchRank();
    }

    private PageableBlogs getKakaoBlogData(BlogQuery blogQuery) {
        KakaoBlog kakaoBlog = kakaoFeignClient.searchBlog(blogQuery.toKakaoSearchParam());
        Blogs blogs = Blogs.of(kakaoBlog.getDocuments());
        Page page = Page.of(kakaoBlog.getMeta().getTotalCount(), blogQuery);
        return new PageableBlogs(page, blogs);
    }

    private PageableBlogs getNaverBlogData(BlogQuery blogQuery) {
        NaverBlog naverBlog = naverFeignClient.searchBlog(blogQuery.toNaverSearchBlogParam());
        Blogs blogs = Blogs.of(naverBlog.getItems());
        Page page = Page.of(naverBlog.getTotal(), blogQuery);
        return new PageableBlogs(page, blogs);
    }
}
