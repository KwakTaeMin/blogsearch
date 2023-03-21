package com.taemin.blogsearch.core.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class PageableBlogs {

    private final Page page;
    private final List<Blog> blogs;

    public PageableBlogs(Page page, Blogs blogs) {
        this.page = page;
        this.blogs = blogs.getBlogs();
    }

    public List<Blog> getBlogs() {
        return Collections.unmodifiableList(this.blogs);
    }
}
