package com.hanah.book.springboot.config.auth;

import com.hanah.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests() //url별 권한 관리 설정
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**","/profile").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //유저 권한 가진 사람들만
                    .anyRequest().authenticated() // 나머지들은, 로그인한 사람들만
                .and()
                    .logout()
                        .logoutSuccessUrl("/") //로그아웃 성공시 여기로 이동
                .and()
                    .oauth2Login() //OAuth2 로그인 기능 설정
                        .userInfoEndpoint() //로그인 성공 이후 사용자 정보 가져올 때의 설정 담당
                            .userService(customOAuth2UserService); //UserService 인터페이스의 구현체
    }
}
