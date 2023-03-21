# 프로젝트 구성
## 모듈
### 1. blog-api : API Controller
  - BlogController
    - /blog/search (blog 검색)
      - parameter 
        - query 질의어 (필수) 
        - sort 정렬 방식 (accuracy, recency)
        - page
        - size
      - response는 네이버와 카카오 둘다 존재하는 데이터로 생성
    - /blog/keyword/rank (blog 검색 키워드 랭킹)
      - 키워드 검색 순위대로 keyword 및 count 제공 
    - error handler 처리
    - sort type converter 처리
    

### 2. blog-core : 비즈니스 로직, jpa, local caching, event
  - 블로그 검색 시 이벤트 발행하여 keyword counting / event listener 비동기 처리
  - save 하는 행위를 따로하여 문제가 생겨도 블로그 조회하는 경우엔 방해되지 않도록 처리 
  - ehcache를 이용하여 트래픽 많은 경우 10초마다 캐싱되도록 처리
  - kakao api가 에러가 발생한 경우 naver api를 사용하여 처리
  - h2 db jpa 사용하여 keyword counting

### 3. external-blog-api
  - 카카오 api blog 조회 
  - 네이버 api blog 조회
  - feign client 이용


## gradle dependencies
- spring-boot-starter-cache : 로컬 캐싱을 위해 사용 
- spring-cloud-starter-openfeign : feignClient 외부 api 호출
- com.h2database:h2 : 인메모리 DB H2 사용
- org.ehcache:ehcache : ehcache 로컬 캐싱 사용 (cluster 감안하여 ehcache 선택)

## 모듈화
- blog-api / blog-core / external-blog-api 모듈화

## 테스트
- blog-api / blog-core / external-blog-api 테스트 코드 작성
- 테스트가 짜기 쉬운 코드로 작성

## Executable jar
- blog-api gradle 설정하여 gradle bootJar Task로 생성
