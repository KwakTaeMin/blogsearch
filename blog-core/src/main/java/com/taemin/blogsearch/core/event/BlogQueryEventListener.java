package com.taemin.blogsearch.core.event;

import com.taemin.blogsearch.core.domain.KeywordSearchCount;
import com.taemin.blogsearch.core.repository.KeywordSearchCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BlogQueryEventListener {

    private final KeywordSearchCountRepository keywordSearchCountRepository;

    private static final int MIN_SEARCH_COUNT = 1;

    @Async
    @EventListener
    public void receivedBlogQueryEvent(BlogQueryEvent blogQueryEvent) {
        Optional<KeywordSearchCount> optionalKeywordCount = keywordSearchCountRepository.findById(blogQueryEvent.getQuery());
        optionalKeywordCount.ifPresentOrElse(keywordSearchCount -> {
            KeywordSearchCount updateKeywordSearch = optionalKeywordCount.get();
            updateKeywordSearch.plusCount();
            keywordSearchCountRepository.save(updateKeywordSearch);
        }, () -> initSaveKey(blogQueryEvent.getQuery()));
    }

    private void initSaveKey(String query) {
        keywordSearchCountRepository.save(
                KeywordSearchCount.builder()
                        .keyword(query)
                        .count(MIN_SEARCH_COUNT)
                        .build()
        );
    }
}
