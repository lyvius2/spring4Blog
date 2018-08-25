## Spring4 Blog

JAVA, Spring Framework 기반으로 개발하고 있는 포트폴리오 겸 블로그입니다.  

### Languages

* [JAVA]
* [JavaScript]

### Structure

* Server : Spring MVC  
* Client : JSP + Vue.js
* DBMS : PostgreSQL (heroku Cloud), MongoDB (mlab)

### Tech (적용된 기술)

* [Spring MVC] - version 4.3.9
* [Spring Security] - version 4.1.1
* [Spring Data JPA] - 에러 및 접속 로그 데이터 수집 및 관리에 적용 (관계형 DB에 INSERT하고 관리함)
* [Spring Data MongoDB] - 프로필 및 댓글 기능에 적용
* [Spring Social] - Facebook, Google+, GitHub, LinkedIn 소셜 로그인 지원
* [Apache Lucene] - 검색 엔진. 블로그 포스트 검색에 적용
* [MyBatis] - SQL Mapper
* [Ehcache] - Memory Cache
* [Google Drive API] - 구글 드라이브를 파일 저장소로 활용
* [Spring AOP] - 접속 로그 수집에 적용
* Interceptor - 블로그 카테고리 메뉴 호출에 적용
* ControllerAdvice - ExceptionHandler. 오류 발생 시 내용을 DB에 저장
* Generic Type - from JDK 5
* Lambda Expressions - from JDK 8
* ~~SiteMesh~~ - ~~레이아웃 템플릿 엔진~~Tiles3로 재교체
* [Tiles3] - 레이아웃 템플릿 엔진
* [Lombok] - 개발 생산성을 높이는 도구
* [Vue.js] - The Progressive JavaScript Framework
* ~~AngularJS~~ - 원래 1.6버전으로 개발하다가 Vue.js로 교체
* ~~gulp~~ - [Node.js] 기반의 JavaScript 빌드 자동화 툴. 사용했었다가 잠시 보류
* [Bootstrap] - HTML, CSS, and JS framework

### Build Tool

* ~~Maven~~ → [Gradle]

### IDE

* ~~Spring Tool Suite~~ → [IntelliJ IDEA] 2018

### Library

* [jQuery] - version 2.2.4
* [CKEditor] - Plug-in을 Google Drive API와 연동하여 드래그로 이미지 업로드 등 구현
* [Moment.js] - Parse, validate, manipulate, and display dates and times in JavaScript.
* [lucy-xss] - Cross-site Scripting 처리
* [drewnoakes] - 이미지 메타 데이터 추출
* [arirang-analyzer-6] - Lucene 한글 형태소 분석기

### Installation

* application.properties - Facebook, Google+, GitHub, LinkedIn API 연결용 App ID와 App Secret 코드, 관리자 계정 E-MAIL, HOME URL 등록
* db.properties - PostgreSQL과 MongoDB 접속정보
* resources 디렉터리에 Google Drive API를 사용하기 위한 client_secret.json 파일 삽입 (from Google API Console)

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [JAVA]: <http://java.oracle.com>
   [JavaScript]: <https://developer.mozilla.org/ko/docs/Web/JavaScript>
   [heroku]: <https://www.heroku.com>
   [Node.js]: <http://nodejs.org>
   [Spring MVC]: <https://spring.io>
   [Spring Security]: <https://projects.spring.io/spring-security/>
   [Spring Data JPA]: <https://projects.spring.io/spring-data-jpa/>
   [Spring Data MongoDB]: <https://projects.spring.io/spring-data-mongodb/>
   [Spring Social]: <https://projects.spring.io/spring-social/>
   [Apache Lucene]: <https://lucene.apache.org/core/>
   [MyBatis]: <http://www.mybatis.org/mybatis-3/>
   [Ehcache]: <http://www.ehcache.org>
   [Google Drive API]: <https://developers.google.com/drive/>
   [Spring AOP]: <https://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html>
   [SiteMesh]: <http://wiki.sitemesh.org/wiki/display/sitemesh/Home>
   [Tiles3]: <https://tiles.apache.org>
   [Lombok]: <https://projectlombok.org>
   [Vue.js]: <https://vuejs.org>
   [AngularJS]: <https://angularjs.org>
   [Bootstrap]: <http://getbootstrap.com>
   [Gradle]: <https://gradle.org>
   [IntelliJ IDEA]: <https://www.jetbrains.com/idea/>
   [jQuery]: <https://jquery.com/>
   [Moment.js]: <https://momentjs.com>
   [CKEditor]: <http://ckeditor.com>
   [lucy-xss]: <https://github.com/naver/lucy-xss-filter>
   [drewnoakes]: <https://github.com/drewnoakes/metadata-extractor>
   [arirang-analyzer-6]: <https://github.com/korlucene>
