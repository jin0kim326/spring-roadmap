package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 🔥🔥 컴포넌트 스캔과 의존관계 자동 주입 🔥🔥
 * AppConfig처럼 설정정보를 일일이 등록하는것은 귀찮고, 실수할 가능성이 있음
 * -> 1.컴포넌트 스캔 : 설정정보 없이 자동으로 스프링 빈을 등록
 * -> 2. 의존관계 자동주입 : @Autowired
 *
 * 빈이름 기본전력 클래스명을 그대로가져오되 첫글자만 소문자
 *
 * @Autowired를 지정하면 스프링 컨테이너가 자동으로 해당 빈을 찾아서 주입 -> "타입"이 같은걸로
 *
 * ------------------------------------------------------------------
 *
 * @ComponentScan
 * basePackages: {"hello.core} : hello.core 에서부터 하위로 스캔(검색)
 * basePackagesClass: 지정한 클래스의 패키지를 탐색 시작위치로 지정
 * 지정하지않으면 -> 해당 어노테이션이 붙어있는 클래스의 위치부터
 *
 * 💡 권장방법 : 패키지 위치 지정X, 설정 정보 클래스의 위치를 프로젝트 최상단에 위치시킴 (스프링부트도 이와같은 방식을 기본제공)
 *
 * ------------------------------------------------------------------------------------------
 *
 * 컴포넌트 스캔에서 중복등록&충돌
 *
 * 같은빈 이름을 등록하는 경우
 * 1. 자동 빈 등록 vs 자동 빈 등록 (빈이름이 같은경우) - ConflictingBeanDefinitionException 발생확률적음..
 * 2. 수동 빈 등록 vs 자동 빈 등록
 *
 * ------------------------------------------------------------------------------------------
 *  < 자동/수동의 올바른 실무 운영 기준 >
 * 🔥 편리한 자동 기능을 기본으로 사용 🔥
 * -> 자동 빈 등록을 사용해도 OCP,DIP를 지킬 수 있다.
 *
 * ?? 그러면 수동 빈 등록은 언제 사용할까?
 * 애플리케이션은 크게 업무로직/기술로직으로 나눌 수 있음
 * - 업무로직 빈 : 컨트롤러/서비스/리포지토리 등 비즈니스 요구사항을 개발할때 사용하는 것
 * - 기술지원 로직 : 기술적인 문제, 공통관심사(aop)를 처리 ex. DB연결, 공통로그
 *
 * => 업무로직은 자동 기능을 적극활용 (문제가 발생해도 어떤 곳에서 문제가 발생했는데 명확하게 파악하기 쉬움)
 * => 기술로직은 수가 매우 적고 애플리케이션에 광범위하게 영향을 미침, 기술지원 로직은 적용이 잘 되고 있는지 아닌지 조차 파악하기 어려운 경우많음.
 *    가급적 수동 빈 등록을 사용해서 명확하게 들어내는 것이 좋다.
 *
 */
@Configuration
@ComponentScan (
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    /**
     * 자동빈이 등록되어있는데, 컨피그에 또 등록하는 경우
     * 수동빈으로 오버라이딩 된다.
     * 그러나 스프링부트로 실행하면 오류가 터짐 (애매한 버그라, 스프링부트에서는 디폴트로 오류 처리)
     */
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
