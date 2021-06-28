## 네이버 로그인 연동

### 1. 네이버 API 등록
<br>

**1. 네이버 오픈 API에 서비스 등록**

1. [네이버 오픈 API](https://developers.naver.com/apps/#/register?api=nvlogin)에 접속
2. 애플리케이션 이름, 제공 정보 선택 후 Callback URL에 `{도메인}/login/oauth2/code/naver` 등록


**2. application-oauth.properties에 키값 등록**

```properties
  #registration
  spring.security.oauth2.client.registration.naver.client-id=클라이언트ID
  spring.security.oauth2.client.registration.naver.client-secret=클라이언트 비밀
  spring.security.oauth2.client.registration.naver.redirect-uri={baseUrl}/{action}/oauth2/code/{registrationId}
  spring.security.oauth2.client.registration.naver.authorization-grant-type=authorization_code
  spring.security.oauth2.client.registration.naver.scope = name,email,profile_image #필수 제공 정보
  spring.security.oauth2.client.registration.naver.client-name=Naver

  #provider
  spring.security.oauth2.client.provider.naver.authorization-uri=https://nid.naver.com/oauth2.0/authorize
  spring.security.oauth2.client.provider.naver.token-uri=https://nid.naver.com/oauth2.0/token
  spring.security.oauth2.client.provider.naver.user-info-uri=https://openapi.naver.com/v1/nid/me
  spring.security.oauth2.client.provider.naver.user-name-attribute=response #회원 조회시 반환되는 JSON의 최상위 필드 중에서, id값을 가지고 있는 response를 user_name으로 지정
```

<br>

### 2. 네이버 로그인 연동
<br>

**1. 사용자 정보 담당할 도메인 생성**

  이미 생성되어 있다면 생략

**2. 스프링 시큐리티 설정**

1. build.gradle에 implementation('org.springframework.boot:spring-boot-starter-oauth2-client') 추가
2. [시큐리티 관련 클래스 생성](./config/auth/)

3. 로그인 테스트
    - 스프링 시큐리티 기본 로그인 URL : "/oauth2/authorization/naver"
    - 스프링 시큐리티 기본 로그아웃 URL : "/logout"
    
    *별도의 컨트롤러 생성할 필요 없음*
4. 세션 저장소로 DB 사용
    1. build.gradle에 implementation('org.springframework.session:spring-session-jdbc') 추가
    2. application.properties에 spring.session.store-type=jdbc 
