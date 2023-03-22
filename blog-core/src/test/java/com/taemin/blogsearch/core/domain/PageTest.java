package com.taemin.blogsearch.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Page 도메인 테스트")
public class PageTest {

    @Test
    public void Page_생성_테스트() {
        Page page = new Page(1, 10, 9999);
        assertThat(page.getCurrentPage()).isEqualTo(1);
        assertThat(page.getDisplayPerPage()).isEqualTo(10);
        assertThat(page.getTotalCount()).isEqualTo(9999);
        assertThat(page.getTotalPage()).isEqualTo(1000);
    }

    @Test
    public void Page_of_생성_테스트() {
        BlogQuery blogQuery = new BlogQuery("카카오", SortType.ACCURACY, 1, 10);
        Page page = Page.of(100, blogQuery);
        assertThat(page.getCurrentPage()).isEqualTo(1);
        assertThat(page.getDisplayPerPage()).isEqualTo(10);
        assertThat(page.getTotalCount()).isEqualTo(100);
        assertThat(page.getTotalPage()).isEqualTo(10);
    }

}
