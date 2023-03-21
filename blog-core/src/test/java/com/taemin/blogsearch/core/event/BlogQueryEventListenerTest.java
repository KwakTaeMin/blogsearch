package com.taemin.blogsearch.core.event;

import com.taemin.blogsearch.core.domain.KeywordSearchCount;
import com.taemin.blogsearch.core.repository.KeywordSearchCountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@DisplayName("BlogQueryEventListener 테스트")
@ExtendWith(MockitoExtension.class)
public class BlogQueryEventListenerTest {

    @InjectMocks
    private BlogQueryEventListener blogQueryEventListener;

    @Mock
    private KeywordSearchCountRepository keywordSearchCountRepository;

    @Test
    public void receivedBlogQueryEvent_테스트() {
        BlogQueryEvent blogQueryEvent = new BlogQueryEvent(this, "카카오");
        KeywordSearchCount keywordSearchCount = new KeywordSearchCount("카카오", 1);
        doReturn(keywordSearchCount).when(keywordSearchCountRepository).save(any());
        blogQueryEventListener.receivedBlogQueryEvent(blogQueryEvent);
    }
}
