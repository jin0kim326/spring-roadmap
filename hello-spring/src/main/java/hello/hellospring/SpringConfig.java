package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Bean으로 등록하는 방법
 * 생성자 주입, 필드 주입, setter주입
 *
 * ** 상황에 따라 구현을 변경해야한다면? -> 설정을 통해 스프링 빈으로 등록
 * 운영중인 코드의 변경없이 설정 코드 한줄만 바꿔서 적용가능함
 */
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
