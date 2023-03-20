package com.taemin.blogsearch.core.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class PageableBlogs {

    private Page page;
    private List<Blog> blogs;

    public PageableBlogs(Page page, Blogs blogs) {
        this.page = page;
        this.blogs = blogs.getBlogs();
    }
}
