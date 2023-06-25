package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }

    /**
     * 🔥 IoC (제어의 역전)
     * -> 프로그램에 대한 제어 흐름에 대한 권한은 모두 AppConfig가 가지고 있음. (OrderServiceImpl 조차 AppConfig가 생성)
     * -> 이렇듯 프로그램의 제어 흐름을 직접제어하는것이 아니라 외부에서 관리하는것을 제어의 역전이라함
     *
     * ✅ 프레임워크 vs 라이브러리
     * - 프레임워크 : 내가 작성한 코드를 제어하고, 대신 실행 (ex. JUnit)
     * - 라이브러리 : 내가 작성한 코드가 직접 제어의 흐름을 담당
     *
     * 🔥 의존관계 주입
     * -> OrderServiceImpl은 DiscountPolicy 인터페이스에 의존 (실제 어떤 구현 객체가 사용될지 모름)
     * -> 의존관계는 "정적인 클래스 의존관계와 실행시점에 결정되는 동적인 객체(인스턴스) 의존 관계" 를 분리해서 생각해야함!!
     *
     * 🕹️정적인 클래스 의존관계 : import코드만 보고 의존관계를 쉽게 구분가능
     * ex) OrderServiceImpl은 MemberRepository, DiscountPolicy에 의존 / but 이 정적클래스 의존관계만으로는 실제 어떤 객체가 서비스에 주입될지 알 수 없음
     *
     * 🕹️동적 객체 의존관계
     * 1. 애플리케이션 "실행시점(런타임)에 외부에서 객체 구현 객체를 생성하고, 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는것을 "의존관계 주입"이라고함
     * 2. 객체 인스턴스를 생성하고, 그 참조값을 전달해서 연결된다.
     * 3. 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다.
     * 4. 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경 가능
     *
     * 💡 IoC컨테이너, DI컨테이너
     * -> AppConfig처럼 객체를 생성하고 관리하면서 의존관계를 연결해주는것을 IoC컨테이너 또는 DI컨테이너
     * -> 의존관계 추입에 초점을 맞추어 최근에는 주로 DI 컨테이너라 한다.
    */
}
