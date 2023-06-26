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
 * -> 빈 이름은 항상 다른 이름을 부여!!
 */
