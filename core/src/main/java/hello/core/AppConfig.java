package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        /* 생성자 주입 */
        return new MemberServiceImpl(memberRepository());       //역할
    }
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());//역할
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();        // 구현
    }

    private FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();         //구현
    }
}
