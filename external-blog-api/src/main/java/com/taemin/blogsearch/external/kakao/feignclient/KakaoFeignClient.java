package com.taemin.blogsearch.external.kakao.feignclient;


import com.taemin.blogsearch.external.kakao.domain.KakaoBlog;
import com.taemin.blogsearch.external.kakao.domain.KakaoSearchBlogParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "kakao", url = "https://dapi.kakao.com")
public interface KakaoFeignClient {

    @GetMapping(value = "v2/search/blog", headers = "Authorization=KakaoAK 43e8ba94a78178b5259d651bb3c861fe")
    KakaoBlog searchBlog(@SpringQueryMap KakaoSearchBlogParam kakaoSearchBlog);
}
