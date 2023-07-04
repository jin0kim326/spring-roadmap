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
 */
@Configuration
@ComponentScan (
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
