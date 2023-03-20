package com.taemin.blogsearch.external.naver.feignclient;


import com.taemin.blogsearch.external.naver.doamin.NaverBlog;
import com.taemin.blogsearch.external.naver.doamin.NaverSearchBlogParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "naver", url = "https://openapi.naver.com")
public interface NaverFeignClient {

    @GetMapping(value = "v1/search/blog.json", headers = {"X-Naver-Client-Id=otPiEU8byTRl3v6d6t0T", "X-Naver-Client-Secret=ShQNMpr0pN"})
    NaverBlog searchBlog(@SpringQueryMap NaverSearchBlogParam naverSearchBlogParam);
}
