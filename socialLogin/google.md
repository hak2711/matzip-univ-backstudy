## 구글 로그인 연동

### 1. 구글 서비스 등록
<br>

**1. 구글 서비스에 신규 서비스 생성**

[구글 클라우드 플랫폼](https://console.cloud.google.com/)에 접속한 후 새 프로젝트 생성

**2. 사용자 인증 정보 생성 - OAuth 클라이언트 ID 만들기**

```markdown
1. API 및 서비스 > 사용자 인증 정보 > 사용자 인증 정보 만들기 > OAuth 클라이언트 ID 생성 > 동의 화면 구성
- 애플리케이션 이름 : 구글 로그인 시 사용자에게 노출될 애플리케이션 이름
- 지원 이메일 : 사용자 동의 화면에서 노출될 이메일 주소. 보통 서비스의 help 이메일 주소 사용.
- Google API의 범위 : 등록할 구글 서비스에서 사용할 범위 목록.

2. OAuth 클라이언트 ID 생성 > 웹 애플리케이션 > 승인된 리디렉션 URI에 `{도메인}/login/oauth2/code/google` 추가 > 생성
```

**3.[application-oauth](./application-oauth.properties)와 [.gitignore](./.gitignore) 등록**
<br>
<br>

### 2. 구글 로그인 연동
<br>

1. 사용자 정보 담당할 도메인 생성
2. 스프링 시큐리티 설정
3. 로그인 테스트
