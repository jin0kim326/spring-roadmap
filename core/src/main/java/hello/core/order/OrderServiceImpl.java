package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);    // 1.회원검색
        int discountPrice = discountPolicy.discount(member, itemPrice); //2. 검색된 고객을 기준으로 할인금액 조회
        return new Order(memberId, itemName, itemPrice, discountPrice); //3.최종 주문객체 반환
    }
}
