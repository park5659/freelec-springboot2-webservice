package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  //spring Security설정들을 활성화 시켜줌.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()  //h2-console화면을 사용하기 위해 해당옵션을 disable()함.
                .and()
                    .authorizeRequests()  //URL별 권한관리를 설정하는 옵션의 시작점임. 이선언이 되어야만 antMatchers옵션을 사용할수 있음.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  //antMatchers은 권한관리대상을 지정하는 옵션임.
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())  //URL,HTTP 메소드별로 관리가 가능함. permitAll()옵션으로 전체열람권한을 줌. USER권한을 가진 사람만 가능하도록함.
                    .anyRequest().authenticated()  //설정된값들 이외 나머지 URL들을 나타냄. authenticated()을 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하게함. 즉, 로그인한사용자만.
                .and()
                    .logout()
                        .logoutSuccessUrl("/")  //로그아웃 기능에 대한 여러 설정의 진입점임. 즉, 로그아웃 성공시 / 주소로 이동함.
                .and()
                    .oauth2Login()  //OAuth2 로그인 기능에 대한 여러 설정의 진입점임.
                        .userInfoEndpoint()  //OAuth2 로그인 성공이후 사용자 정보를 가져올 때의 설정들을 담당함.
                            .userService(customOAuth2UserService);  //userService는 소셜로그인 성공시 후속조치를 진행할 UserService 인터페이스의 구현체를 등록함. 정보를 가져온상태에서 추가로 진행하고자하는 기능을 명시할수있음.

    }
}
