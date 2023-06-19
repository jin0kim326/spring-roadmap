package hello.hellospring.config;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Spring Bean으로 등록하는 방법
 * 생성자 주입, 필드 주입, setter주입
 *
 * ** 상황에 따라 구현을 변경해야한다면? -> 설정을 통해 스프링 빈으로 등록
 * 운영중인 코드의 변경없이 설정 코드 한줄만 바꿔서 적용가능함
 */
@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        /**
         * 개방-폐쇄 원칙 (OCP)
         * - 확장에는 열려있고, 수정/변경에는 열려있다
         *
         * -> 스프링의 DI을 사용하면 기존코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경 할 수 있다.
         */
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
          return new JdbcTemplateMemberRepository(dataSource);
    }
}
