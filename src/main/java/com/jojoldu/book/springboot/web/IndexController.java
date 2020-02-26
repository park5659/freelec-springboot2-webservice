package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

//mustache에 URL을 매핑할 Controller임.
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")                    //Model은 서버템플릿엔진에서 사용할수 있는 객체를 저장할수있음.
    public String index(Model model, @LoginUser SessionUser user) {  //여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에전달함.
        model.addAttribute("Posts", postsService.findAllDesc());


        if (user != null) {        //model에 세션값이 있으면 userName에 등록 없으면 로그인 버튼이 보이게 됨.
            model.addAttribute("userName", user.getName());
        }

        return "index";   //mustache 스타터땜에 문자열반환시 앞의경로와 뒤의 파일 확장자는 자동지정되므로 "index"로만 표현함.
                          //"index"로 전환되어 View Resolver가 처리. View Resolver는 URL 요청의 결과를 전달할 타입과 값을 지정하는 관리자 격으로 봄.
    }

    @GetMapping("/posts/save")
    public String postsSave() {

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")  //수정화면을 연결할 메소드임.
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
