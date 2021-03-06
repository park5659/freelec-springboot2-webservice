package com.jojoldu.book.springboot;
//import 단축키는 여기에선 Alt+Enter다.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //스프링부트의 자동설정, 스프링 빈 읽기와 생성을 모두 자동설정하는 애노테이션임.(항상최상위에 위치해야한다)
//@EnableJpaAuditing  //JPA Auditing 활성화.  마지막에는 이걸로 인해(@Entity 클래스가 필요해서) 테스트 오류가 발생하니 제거후 config패키지에 JpaConfig를 생성하여 @EnableJapAudition를 추가함.
public class Application {            //Application은 메인 클래스다.
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);   //SpringApplication.run으로 내장 WAS를 실행함.
    }
}
