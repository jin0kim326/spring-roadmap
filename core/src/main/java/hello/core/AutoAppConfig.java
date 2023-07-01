package hello.core;

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
 */
@Configuration
@ComponentScan (
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
