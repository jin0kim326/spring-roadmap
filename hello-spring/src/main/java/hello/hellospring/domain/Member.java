package hello.hellospring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "spring_basic")
@Getter
@Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 알아서 pk값을 생성해주는 전략을 IDENTITY라고함
    private Long id;

    private String name;
}
