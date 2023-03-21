package com.taemin.blogsearch.core.service;

import com.taemin.blogsearch.core.domain.*;
import com.taemin.blogsearch.core.repository.KeywordSearchCountRepository;
import com.taemin.blogsearch.external.kakao.domain.KakaoBlog;
import com.taemin.blogsearch.external.kakao.domain.KakaoDocument;
import com.taemin.blogsearch.external.kakao.domain.KakaoDocuments;
import com.taemin.blogsearch.external.kakao.domain.KakaoMeta;
import com.taemin.blogsearch.external.kakao.feignclient.KakaoFeignClient;
import com.taemin.blogsearch.external.naver.doamin.NaverBlog;
import com.taemin.blogsearch.external.naver.doamin.NaverItem;
import com.taemin.blogsearch.external.naver.doamin.NaverItems;
import com.taemin.blogsearch.external.naver.feignclient.NaverFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@DisplayName("BlogService 테스트")
@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {

    @InjectMocks
    private BlogService blogService;
    @Mock
    private KakaoFeignClient kakaoFeignClient;
    @Mock
    private NaverFeignClient naverFeignClient;
    @Mock
    private KeywordSearchCountRepository keywordSearchCountRepository;

    private KakaoMeta doReturnKakaoMeta;
    private KakaoDocuments doReturnKakaoDocuments;
    private NaverItems doReturnNaverItems;
    private List<KeywordSearchCount> doReturnKeywordSearchCounts;

    @BeforeEach
    public void beforeEach() {
        initKakaoBlog();
        initNaverBlog();
        initKeywordSearchCount();
    }

    @Test
    public void getSearchBlog_카카오_api_테스트() {
        BlogQuery blogQuery = new BlogQuery("카카오", SortType.ACCURACY, 1, 10);
        KakaoBlog kakaoBlog = new KakaoBlog(doReturnKakaoDocuments.getKakaoDocuments(), doReturnKakaoMeta);
        doReturn(kakaoBlog).when(kakaoFeignClient).searchBlog(any());

        PageableBlogs pageableBlogs = blogService.getSearchBlog(blogQuery);

        assertThat(pageableBlogs).isNotNull();
        assertThat(pageableBlogs.getBlogs().size()).isEqualTo(2);
        assertThat(pageableBlogs.getPage()).isNotNull();
    }

    @Test
    public void getSearchBlog_네이버_api_테스트() {
        BlogQuery blogQuery = new BlogQuery("카카오", SortType.ACCURACY, 1, 10);
        NaverBlog naverBlog = new NaverBlog(new Date(), 100, 1, 10, doReturnNaverItems.getNaverItems());
        doReturn(naverBlog).when(naverFeignClient).searchBlog(any());

        PageableBlogs pageableBlogs = blogService.getSearchBlog(blogQuery);

        assertThat(pageableBlogs).isNotNull();
        assertThat(pageableBlogs.getBlogs().size()).isEqualTo(2);
        assertThat(pageableBlogs.getPage()).isNotNull();
    }

    @Test
    public void getSearchRank_테스트() {
        doReturn(doReturnKeywordSearchCounts).when(keywordSearchCountRepository).findAll();
        List<KeywordSearchCount> keywordSearchCounts = blogService.getSearchRank();

        assertThat(keywordSearchCounts).isNotNull();
        assertThat(keywordSearchCounts.size()).isEqualTo(10);

        assertThat(keywordSearchCounts.get(0).getKeyword()).isEqualTo("12 keyword");
        assertThat(keywordSearchCounts.get(1).getKeyword()).isEqualTo("11 keyword");
        assertThat(keywordSearchCounts.get(2).getKeyword()).isEqualTo("10 keyword");
        assertThat(keywordSearchCounts.get(3).getKeyword()).isEqualTo("9 keyword");
        assertThat(keywordSearchCounts.get(4).getKeyword()).isEqualTo("8 keyword");
        assertThat(keywordSearchCounts.get(5).getKeyword()).isEqualTo("7 keyword");
        assertThat(keywordSearchCounts.get(6).getKeyword()).isEqualTo("6 keyword");
        assertThat(keywordSearchCounts.get(7).getKeyword()).isEqualTo("5 keyword");
        assertThat(keywordSearchCounts.get(8).getKeyword()).isEqualTo("4 keyword");
        assertThat(keywordSearchCounts.get(9).getKeyword()).isEqualTo("2 keyword");
    }

    private void initKakaoBlog() {
        KakaoDocument kakaoDocumentOne = KakaoDocument.builder()
                .blogName("name")
                .contents("contents")
                .title("title")
                .datetime(new Date())
                .thumbnail("thumbnail")
                .url("url")
                .build();

        KakaoDocument kakaoDocumentTwo = KakaoDocument.builder()
                .blogName("name")
                .contents("contents")
                .title("title")
                .datetime(new Date())
                .thumbnail("thumbnail")
                .url("url")
                .build();
        doReturnKakaoMeta = new KakaoMeta(false, 100, 10000);
        doReturnKakaoDocuments = new KakaoDocuments(Arrays.asList(kakaoDocumentOne, kakaoDocumentTwo));
    }

    private void initNaverBlog() {
        NaverItem naverItemOne = NaverItem.builder()
                .bloggerName("name")
                .description("description")
                .link("link")
                .postDate("20220101")
                .title("title")
                .bloggerLink("bloggerLink")
                .build();

        NaverItem naverItemTwo = NaverItem.builder()
                .bloggerName("name")
                .description("description")
                .link("link")
                .postDate("20220101")
                .title("title")
                .bloggerLink("bloggerLink")
                .build();
        doReturnNaverItems = new NaverItems(Arrays.asList(naverItemOne, naverItemTwo));
    }

    private void initKeywordSearchCount() {
        KeywordSearchCount keywordSearchCountOne = new KeywordSearchCount("1 keyword", 1);
        KeywordSearchCount keywordSearchCountTwo = new KeywordSearchCount("2 keyword", 2);
        KeywordSearchCount keywordSearchCountThree = new KeywordSearchCount("4 keyword", 3);
        KeywordSearchCount keywordSearchCountFour = new KeywordSearchCount("5 keyword", 4);
        KeywordSearchCount keywordSearchCountFive = new KeywordSearchCount("6 keyword", 5);
        KeywordSearchCount keywordSearchCountSix = new KeywordSearchCount("7 keyword", 6);
        KeywordSearchCount keywordSearchCountSeven = new KeywordSearchCount("8 keyword", 7);
        KeywordSearchCount keywordSearchCountEight = new KeywordSearchCount("9 keyword", 8);
        KeywordSearchCount keywordSearchCountNine = new KeywordSearchCount("10 keyword", 9);
        KeywordSearchCount keywordSearchCountTen = new KeywordSearchCount("11 keyword", 10);
        KeywordSearchCount keywordSearchCountEleven = new KeywordSearchCount("12 keyword", 11);
        KeywordSearchRank keywordSearchRank = new KeywordSearchRank(Arrays.asList(
                keywordSearchCountOne,
                keywordSearchCountTwo,
                keywordSearchCountThree,
                keywordSearchCountFour,
                keywordSearchCountFive,
                keywordSearchCountSix,
                keywordSearchCountSeven,
                keywordSearchCountEight,
                keywordSearchCountNine,
                keywordSearchCountTen,
                keywordSearchCountEleven

        ));
        doReturnKeywordSearchCounts = keywordSearchRank.getKeywordSearchRank();
    }

}
