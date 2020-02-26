package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  //이 어노테이션이 생성될수 있는 위치를 지정함. PARAMETER로 지정했으니 파라미터로 선언된 객체만 사용할수 있음.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {   //@interface 파일을 어노테이션 클래스로 지정함. LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면 됨.
}
