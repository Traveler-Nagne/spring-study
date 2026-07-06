package hello.servlet.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {

    private Long id;
    private String username;
    private int age;


    private Member(String username, int age) {
        this.username = username;
        this.age = age;
    }


    public static Member create(String username, int age) {
        return new Member(username, age);
    }
}
