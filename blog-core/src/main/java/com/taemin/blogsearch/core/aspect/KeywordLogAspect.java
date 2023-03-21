package com.taemin.blogsearch.core.aspect;

import com.taemin.blogsearch.core.domain.BlogQuery;
import com.taemin.blogsearch.core.event.BlogQueryEvent;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@Aspect
@RequiredArgsConstructor
public class KeywordLogAspect {

    private final ApplicationEventPublisher applicationEventPublisher;

    @After(value = "@annotation(com.taemin.blogsearch.core.anotation.KeywordLog)")
    public void logKeyword(JoinPoint joinPoint) {
        Optional<Object> optionalBlogQuery = Arrays.stream(joinPoint.getArgs()).findFirst();
        optionalBlogQuery.ifPresent(objectBlogQuery -> {
            BlogQuery blogQuery = (BlogQuery) objectBlogQuery;
            applicationEventPublisher.publishEvent(new BlogQueryEvent(this, blogQuery.getQuery()));
        });
    }
}
