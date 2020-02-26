package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;  //static는 자바클래스에 대한 인스턴스의 생성없이 메소드를 사용할수있음.
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)  //SpringRunner라는 스프링 실행자를 사용함.(즉,스프링부트테스트와 JUnit사이에 연결자 역할을 함.)
@WebMvcTest(controllers = HelloController.class,  //여러스프링테스트 애노테이션중 Web에 집중할수 있는 애노테이션임.(단,@Service,@Component,@Repository등은 사용할수 없음.)여기선 @Controller만 사용하기때문에 사용함.
        excludeFilters = {                        //CustomOAuth2UserService를 읽을수 없어 오류가 나니 스캔대상에서 SecurityConfig를 제거하기위한 코딩이다.
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }

)

public class HelloControllerTest {

    @Autowired  //스프링이 관리하는 빈을 주입 받음.(@Inject와 같음.)
    private MockMvc mvc;  //웹 API를 테스트할때 사용. 스프링 MVC 테스트의 시작점임.

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))  //MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함. 체이닝이 지원되어 아래와 같이 여러검증기능을 이어서 선언할수 있다.
                .andExpect(status().isOk())  //mvc.perform의 결과를 검증. HTTP Header의 Status를 검증. 흔히 200,404,500등의 상태검증. 여기에선 Ok 즉 200인지 검증.
                .andExpect(content().string(hello));  //mvc.perform의 결과를 검증. 응답본문의 내용을 검증. Controller에서 "hello"를 리턴하는지를 검증.
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        //Param은 API 테스트할때 사용될 요청 파라미터를 설정함. 단, 값은 String만 허용됨. 그래서 숫자/날짜등을 등록할때는 문자열로 변경해야만 가능함. 그래서 amount는 String.valueOf(amount)로 처리함.
        //jsonPath는 JSON 응답값을 필드별로 검증할수 있는 메소드임. $를 기준으로 필드명을 명시함. 여기선 $.name과 $.amount로 검증함.

    }
}
