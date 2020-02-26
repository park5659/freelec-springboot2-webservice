package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass  //JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate,modifiedDate)도 칼럼으로 인식하도록 함.
@EntityListeners(AuditingEntityListener.class)  //BaseTimeEntity 클래스에 Auditing 기능을 포함시킴.
public abstract class BaseTimeEntity {  //abstract은 상속을 강제하는 일종의 규제. 여기서는 BaseTimeEntity를 상속을 강제하는것임. 그래서 Posts에서 상속받도록 변경함.

    @CreatedDate  //Entity가 생성되어 저장될 때 시간이 자동 저장됨.
    private LocalDateTime createdDate;

    @LastModifiedDate  //조회한 Entity의 값을 변경할 때 시간이 자동 저장됨.
    private LocalDateTime modifiedDate;
    //이렇케 한 다음에 반드시 Posts클래스로가서 BaseTimeEntity를 상속받도록 변경해야한다.(public class Posts extends BaseTimeEntity {})
    //그리고나서 Application클래스로가서 JPA Auditing 어노테이션들을 모두 활성화하도록 어노테이션 하나를 추가함.(@EnableJpaAudition추가)

}
