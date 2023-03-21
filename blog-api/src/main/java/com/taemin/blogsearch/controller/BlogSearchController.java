package com.taemin.blogsearch.controller;

import com.taemin.blogsearch.core.domain.BlogQuery;
import com.taemin.blogsearch.core.domain.KeywordSearchCount;
import com.taemin.blogsearch.core.domain.PageableBlogs;
import com.taemin.blogsearch.core.domain.SortType;
import com.taemin.blogsearch.core.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogSearchController {

    private final BlogService blogService;
    private static final String DEFAULT_PAGE_VALUE = "1";
    private static final String DEFAULT_SIZE_VALUE = "10";
    private static final String DEFAULT_SORT_TYPE_VALUE = "ACCURACY";


    @GetMapping("/search")
    public ResponseEntity<PageableBlogs> searchBlog(@RequestParam String query,
                                                    @RequestParam(required = false, defaultValue = DEFAULT_SORT_TYPE_VALUE) SortType sort,
                                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_VALUE) int page,
                                                    @RequestParam(required = false, defaultValue = DEFAULT_SIZE_VALUE) int size) {
        BlogQuery blogQuery = new BlogQuery(query, sort, page, size);
        return ResponseEntity.ok(blogService.getSearchBlog(blogQuery));
    }

    @GetMapping("/keyword/rank")
    public ResponseEntity<List<KeywordSearchCount>> getSearchRank() {
        return ResponseEntity.ok(blogService.getSearchRank());
    }
}
