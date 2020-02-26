package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController   //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어주는 애노테이션임. (@ResponseBody를 각메소드에 선언한것을 한번에 사용할수 있게 해줌.)
public class HelloController {

    @GetMapping("/hello")    //@RequestMapping과 같다. HTTP Method인 Get의 요청을 받을수 있는 API를 만들어줌.
    public String hello() {

        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
        //@RequestParam은 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션임.
        //여기서는 외부에서 name(@RequestParam ("name“)) 이란 이름으로 넘긴 파라미 터를 메소드 파라미터 name(String name)에 저장함.
    }
}
