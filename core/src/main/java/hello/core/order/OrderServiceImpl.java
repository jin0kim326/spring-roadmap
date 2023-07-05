package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 스프링의 라이프 사이클 크게 두가지!
 * 1. 스프링 빈 등록
 * 2. 의존관계 주입 (설정)
 *
 * 생성자 주입방법 4가지
 * 1. 생성자
 * 2. 수정자(Setter)
 * 3. 필드 주입
 * 4. 일반 메서드
 *
 * <1. 생성자 주입>
 * -> 생성자를 통해서 의존관계를 주입
 *  - 생성자 호출시점에 딱 1번만 호출
 *  - 불변,필수 의존관계에 사용
 *  🔥 생성자는 자바코드이기 때문에 스프링 빈을 등록하면서 주입을 하게됨 (라이프사이클에서 예외적인 상황)
 *
 *  <2. 수정자(Setter) 주입>
 * -> setter를 통해 의존관계 주입
 * -> 선택, 변경 가능성이 있는 의존관계 주입 (required=false)
 *
 * <3. 필드 주입 >
 * -이름그대로 필드에 바로 주입 / 코드가 간결함
 * - 사용하지말자 ! (단위테스트 불가, 즉 DI컨테이너가 없으면 아무것도 못함)
 *
 * <4. 일반 메서드 주입>
 * -> 한번에 여러 필드를 주입 받을 수 있음 (잘안씀)
 *
 * 💡💡 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작함 💡💡
 * -> 스프링 빈이 아닌 Member클래스 안에서 @Autowired같은것들은 동작하지 않는다!!
 */
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);    // 1.회원검색
        int discountPrice = discountPolicy.discount(member, itemPrice); //2. 검색된 고객을 기준으로 할인금액 조회
        return new Order(memberId, itemName, itemPrice, discountPrice); //3.최종 주문객체 반환
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

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
 *
 * 🔥 AppConfig 등장 🔥
 * -> 애플리케이션 전체 동작 방식을 구성
 *
 * private DiscountPolicy discountPolicy;
 */
