package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * 생성자를 통해 객체가 생성될때 connect를 호출하도록 되어있음
 * 객체가 생성될때 url이 없기때문에 오류가 날 것이다.
 *
 * 스프링 빈의 이벤트 라이프사이클 (싱글톤)
 * 1. 스프링 컨테이너 생성
 * 2. 스프링 빈 생성
 * 3. 의존관계 주입
 * 4. 초기화 콜백
 * 5. 사용
 * 6. 소멸전 콜백
 * 7. 스프링 종료
 *
 * <1> 초기화,소멸 인터페이스의 단점
 * -> 스프링 전용 인터페이스 (스프링에 의존)
 * -> 메서드명 변경 불가
 * -> 외부 라이브러리 적용 불가 (코드수정이 안되기때문에)
 *
 * <2> 빈 등록 초기화,소멸 메서드
 * -> 메서드 이름 작성 가능
 * -> 스프링 빈 의존X
 * -> 외부 라이브러리에도 초기화,종료 메서드를 적용가능
 * +@Bean의 destoryMethod 속성의 아주 특별한 기능 - 라이브러리는 대부분 close,shutdown 이라는 이름의 종료메서드 사용
 *  @Bean의 destroyeMethod는 기본값이 "(inferred)"로 등록되어 있다. inferred=추론
 *
 * <3> @PostConstruct, @PreDestory 특징
 * - 최신 스프링 가장 권장방법 (편리함)
 * - 스프링 종속 기술이 아니라, 자바에서도 사용가능
 * - 컴포넌트 스캔과 잘 어울림
 * - 유일한 단점 : 외부라이브러리에 적용 불가
 *
 * 💡 3번을 기본사용, 외부라이브러리사용시 2번사용
 */
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect:  " + url);
    }

    public void call(String message) {
        System.out.println("call : "+ url + "/ message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("disconnect: " + url);
    }

    @PostConstruct
    public void init() {
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() {
        disconnect();
    }
}
