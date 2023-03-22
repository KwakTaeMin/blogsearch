package com.taemin.blogsearch.controller;

import com.taemin.blogsearch.core.domain.*;
import com.taemin.blogsearch.core.service.BlogService;
import com.taemin.blogsearch.external.kakao.domain.KakaoDocument;
import com.taemin.blogsearch.external.naver.doamin.NaverItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("BlogSearchController 테스트")
@ExtendWith(MockitoExtension.class)
public class BlogSearchControllerTest {

    @InjectMocks
    private BlogSearchController blogSearchController;

    @Mock
    private BlogService blogService;
    private MockMvc mockMvc;

    private PageableBlogs searchBlogResponse;
    private List<KeywordSearchCount> rankResponse;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(blogSearchController).build();
        initRankResponse();
        initSearchBlogResponse();
    }

    @Test
    public void searchBlog_테스트() throws Exception {
        doReturn(searchBlogResponse).when(blogService).getSearchBlog(any());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/blog/search").param("query", "카카오뱅크"));
        MvcResult mvcResult = resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("page", searchBlogResponse.getPage()).exists())
                .andExpect(jsonPath("page.currentPage", searchBlogResponse.getPage().getCurrentPage()).exists())
                .andExpect(jsonPath("page.displayPerPage", searchBlogResponse.getPage().getDisplayPerPage()).exists())
                .andExpect(jsonPath("page.totalCount", searchBlogResponse.getPage().getTotalCount()).exists())
                .andExpect(jsonPath("page.totalPage", searchBlogResponse.getPage().getTotalPage()).exists())
                .andExpect(jsonPath("blogs", searchBlogResponse.getBlogs()).exists())
                .andExpect(jsonPath("blogs[0].title", searchBlogResponse.getBlogs().get(0).getTitle()).exists())
                .andExpect(jsonPath("blogs[0].blogName", searchBlogResponse.getBlogs().get(0).getBlogName()).exists())
                .andExpect(jsonPath("blogs[0].content", searchBlogResponse.getBlogs().get(0).getContent()).exists())
                .andExpect(jsonPath("blogs[0].url", searchBlogResponse.getBlogs().get(0).getUrl()).exists())
                .andExpect(jsonPath("blogs[0].createDate", searchBlogResponse.getBlogs().get(0).getCreateDate()).exists())
                .andReturn();
    }

    @Test
    public void getSearchRank_테스트() throws Exception {
        doReturn(rankResponse).when(blogService).getSearchRank();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/blog/keyword/rank"));
        MvcResult mvcResult = resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].keyword", rankResponse.get(0).getKeyword()).exists())
                .andExpect(jsonPath("$[0].count", rankResponse.get(0).getCount()).exists())
                .andExpect(jsonPath("$[1].keyword", rankResponse.get(1).getKeyword()).exists())
                .andExpect(jsonPath("$[1].count", rankResponse.get(1).getCount()).exists())
                .andExpect(jsonPath("$[2].keyword", rankResponse.get(2).getKeyword()).exists())
                .andExpect(jsonPath("$[2].count", rankResponse.get(2).getCount()).exists())
                .andExpect(jsonPath("$[3].keyword", rankResponse.get(3).getKeyword()).exists())
                .andExpect(jsonPath("$[3].count", rankResponse.get(3).getCount()).exists())
                .andExpect(jsonPath("$[4].keyword", rankResponse.get(4).getKeyword()).exists())
                .andExpect(jsonPath("$[4].count", rankResponse.get(4).getCount()).exists())
                .andExpect(jsonPath("$[5].keyword", rankResponse.get(5).getKeyword()).exists())
                .andExpect(jsonPath("$[5].count", rankResponse.get(5).getCount()).exists())
                .andExpect(jsonPath("$[6].keyword", rankResponse.get(6).getKeyword()).exists())
                .andExpect(jsonPath("$[6].count", rankResponse.get(6).getCount()).exists())
                .andExpect(jsonPath("$[7].keyword", rankResponse.get(7).getKeyword()).exists())
                .andExpect(jsonPath("$[7].count", rankResponse.get(7).getCount()).exists())
                .andExpect(jsonPath("$[8].keyword", rankResponse.get(8).getKeyword()).exists())
                .andExpect(jsonPath("$[8].count", rankResponse.get(8).getCount()).exists())
                .andExpect(jsonPath("$[9].keyword", rankResponse.get(9).getKeyword()).exists())
                .andExpect(jsonPath("$[9].count", rankResponse.get(9).getCount()).exists())
                .andReturn();
    }

    private void initSearchBlogResponse() {
        Page page = new Page(1, 10, 10000);
        KakaoDocument kakaoDocument = KakaoDocument.builder()
                .blogName("name")
                .contents("contents")
                .title("title")
                .datetime(new Date())
                .thumbnail("thumbnail")
                .url("url")
                .build();
        Blog kakaoBlog = new Blog(kakaoDocument);

        NaverItem naverItem = NaverItem.builder()
                .bloggerName("name")
                .description("description")
                .link("link")
                .postDate("20220101")
                .title("title")
                .bloggerLink("bloggerLink")
                .build();
        Blog naverBlogs = new Blog(naverItem);
        Blogs blogs = new Blogs(Arrays.asList(kakaoBlog, naverBlogs));

        searchBlogResponse = new PageableBlogs(page, blogs);

    }

    private void initRankResponse() {
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

        rankResponse = keywordSearchRank.getKeywordSearchRank();
    }
}
