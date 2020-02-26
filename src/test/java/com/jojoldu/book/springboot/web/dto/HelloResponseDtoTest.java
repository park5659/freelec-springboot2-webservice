package com.jojoldu.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        //assertThat은 assertj라는 테스트검증 라이브러리의 검증메소드임. 검증하고싶은 대상을 메소드 인자로 받음.(메소드체이닝지원으로 isEqualTo와 같이 이어서 사용함.)
        //isEqualTo는 assertj의 동등 비교 메소드임. assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을때만 성공임.
    }
}
