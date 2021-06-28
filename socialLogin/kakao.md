## 카카오 로그인 연동

### 1. 카카오 서비스 등록
<br>

**1. 카카오에 신규 서비스 생성**

1. [카카오 디벨로퍼](https://developers.kakao.com/console/app)에 접속한 후 애플리케이션 추가하기
2. 앱 이름, 회사 이름 입력 후 저장
3. 추가한 애플리케이션 선택 > 동의항목 > 개인정보 동의 여부 설정 후 저장

    ![image](https://user-images.githubusercontent.com/67725652/123586705-15ba0e00-d820-11eb-9075-aa92232e42cb.png)

4. 카카오 로그인 > 활성화 후 저장
5. Redirect URI 정보 입력

**2. Client Secret 발급**

추가한 애플리케이션 선택 > 보안 > Client Secret 코드 생성

**3.application-oauth.properties에 키값 등록**

 ```properties
  #kakao registration
  spring.security.oauth2.client.registration.kakao.client-id=REST API 키
  spring.security.oauth2.client.registration.kakao.client-secret=클라이언트 secret
  spring.security.oauth2.client.registration.kakao.redirect-uri={baseUrl}/{action}/oauth2/code/{registrationId}
  spring.security.oauth2.client.registration.kakao.authorization-grant-type=authorization_code
  spring.security.oauth2.client.registration.kakao.scope=profile_nickname,profile_image,account_email #동의항목으로 추가한 것들
  spring.security.oauth2.client.registration.kakao.client-name=kakao
  spring.security.oauth2.client.registration.kakao.client-authentication-method=POST
  
  #kakao provider
  spring.security.oauth2.client.provider.kakao.authorization-uri= https://kauth.kakao.com/oauth/authorize
  spring.security.oauth2.client.provider.kakao.token-uri=https://kauth.kakao.com/oauth/token
  spring.security.oauth2.client.provider.kakao.user-info-uri=https://kapi.kakao.com/v2/user/me
  spring.security.oauth2.client.provider.kakao.user-name-attribute=id

```

**4. .gitignore에 `application-oauth.properties` 등록**

<br>
<br>

### 2. 카카오 로그인 연동
<br>

**1. 사용자 정보 담당할 도메인 생성**

이미 생성되어 있다면 생략

**2. 스프링 시큐리티 설정**

1. build.gradle에 implementation('org.springframework.boot:spring-boot-starter-oauth2-client') 추가
2. [시큐리티 관련 클래스 생성](./config/auth/)

3. 로그인 테스트
    - 스프링 시큐리티 기본 로그인 URL : "/oauth2/authorization/kakao"
    - 스프링 시큐리티 기본 로그아웃 URL : "/logout"
    
    *별도의 컨트롤러 생성할 필요 없음*
4. 세션 저장소로 DB 사용
    1. build.gradle에 implementation('org.springframework.session:spring-session-jdbc') 추가
    2. application.properties에 spring.session.store-type=jdbc 
