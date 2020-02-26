package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter  //Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다. 대신 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼수 있는 메소드를 추가해야함.(생성자나 빌더를 통해 최종값을 채운 후 DB에 삽입함.)
@NoArgsConstructor  //lombok 어노테이션으로 기본생성자 자동 추가임. public Posts() {}와 같은 효과임.
@Entity  //테이블과 링크될 클래스임을 나타냄. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블이름 매칭함. 예)SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity {

    @Id  //해당 테이블의 PK 필드를 나타냄.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //PK 생성규칙을 나타냄. GenerationType.IDENTITY 옵션을 추가해야만 auto_Increment가 됨.
    private Long id;  //Entity의 PK는 Long 타입의 Auto_increment를 사용하는것을 추천함.(여러키를 조합한 복합키로 잡을 경우 난감한 상황이 발생할수있다.)

    @Column(length = 500, nullable = false)  //Column은 테이블컬럼을 나타내며 굳이선언하지 않아도 해당 클래스필드는 모두 컬럼이 됨.
    private String title;                    //사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용함.
                                             //예)varchar(255)가 기본인데 사이즈를 500으로 늘리고 싶을떄나 타입을 text로 변경할때 등등 사용함.
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder  //해당 클래스의 빌더 패턴 클래스를 생성함. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함함.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
