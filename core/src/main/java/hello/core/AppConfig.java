package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        /* 생성자 주입 */
        return new MemberServiceImpl(memberRepository());       //역할
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());//역할
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();        // 구현
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDifscountPolicy();         //구현
        return new RateDiscountPolicy();
    }
}

/**
 * 빈 이름 : 빈이름은 기본으로 메서드 명을 사용함. @Bean(name="xxxxx")로 직접 부여가능
 *  -> 빈 이름은 항상 다른 이름을 부여!!
 *
 * 1.BeanFactory
 *  -> 스프링 컨테이너의 최상위 인터페이스, 스프링빈을 관리/조회
 *
 * 2.ApplicationContext
 *  -> BeanFactory 기능을 모두 상속받아 제공
 *  -> 애플리케이션을 개발할 때는 빈을 관리/조회는 물론 수많은 "부가기능"이 필요
 *  MessageSource - 다국어
 *  EnvironmentCapable - 환경변수 (로컬/개발/운영)
 *  ApplicationEventPublisher - 이벤트 발행/구독 모델을 지원
 *  ResourceLoader - 파일/클래스패스/외부 등에서 리소스를 편리하게 조회
 *
 * 💡1. ApplicationContext는 BeanFactory기능을 상속받으며, 빈 관리기능 + 편리한 부가 기능 제공
 *   2. BeanFactory를 직접사용할 일은 없음
 *
 */
