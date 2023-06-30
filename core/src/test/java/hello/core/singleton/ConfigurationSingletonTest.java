package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    /**
     * memberRepository 인스턴스는 모두 같은 인스턴스가 공유되어 사용된다
     */
    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepositry = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepositry = " + memberRepositry);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepositry);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepositry);
    }

    /**
     * 출력결과 : bean = hello.core.AppConfig$$SpringCGLIB$$0@19fe4644
     * 순수한 클래스라면 class hello.core.Appconfig만 출력되어야하는데 xxxCGLIB가 붙으면서 복잡하게 출력됨
     *
     * => 즉 내가 등록한 Appconfig말고 스프링이 AppConfig@CGLIB라는 임의의 다른클래스를 등록하고,
     *    싱글톤을 보장되도록 해줌
     *
     */
    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean); // 출력결과 : bean = hello.core.AppConfig$$SpringCGLIB$$0@19fe4644
    }
}
