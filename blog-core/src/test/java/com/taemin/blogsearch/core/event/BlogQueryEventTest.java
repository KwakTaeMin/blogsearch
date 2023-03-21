package com.taemin.blogsearch.core.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BlogQueryEvent 테스트")
public class BlogQueryEventTest {

    @Test
    public void BlogQueryEvent_생성_테스트() {
        BlogQueryEvent blogQueryEvent = new BlogQueryEvent(this, "카카오");
        assertThat(blogQueryEvent.getQuery()).isEqualTo("카카오");

    }
}
