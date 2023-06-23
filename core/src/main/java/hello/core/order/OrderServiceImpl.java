package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 고정할인 정책 -> 정률할인 정책
     * 할인 정책을 변경하기 위해 클라이언트 코드를 고쳐야함.
     *
     * 역할/구현을 충실하게 분리 ✅
     * 다형성활용, 인터페이스 구현 객체를 분리 ✅
     * OCP,DIP 같은 객체지향 설계 원칙을 충실히 준수했다 -> 그런것같지만 ❌
     *   💡 주문서비스 클라이언트는 DiscountPolicy 인터페이스뿐 아니라 "구체(구현)클래스"에도 의존하고있음
     *   🔥 DIP 위반 - 인터페이스 뿐만아니라 구체클래스(FixDiscountPolicy)를 의존
     *   🔥OCP 위반 - 정책을 바꾸는 순간 orderService 코드도 함께 변경된다

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
     */

    /**
     * 인터페이스에만 의존하도록 변경
     * 구현갹체가 없기때문에 NullPoint Exception 발생...
     *
     * 해결방안 -> 누군가가 클라이언트(OrderServiceImpl)에 DiscountPolicy의 구현객체를 대신 생성,주입 해주어야함
     */
    private DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);    // 1.회원검색
        int discountPrice = discountPolicy.discount(member, itemPrice); //2. 검색된 고객을 기준으로 할인금액 조회
        return new Order(memberId, itemName, itemPrice, discountPrice); //3.최종 주문객체 반환
    }
}
