package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * proxyMode = ScopedProxyMode.TARGET_CLASS 를 사용하여 Provider 없이 오류해결가능
 * -> 스프링이 올라갈때에 CGLIB라이브러리를 사용하여 가짜 프록시객체를 주입해준다.
 * -> 실제 요청이 오면 그때 내부에서 진짜 빈을 요청하는 "위임 로직"이 들어가있음
 *
 * 마치 싱글톤을 사용하는것처럼 사용가능
 * 핵심! Provider를 사용하던, 프록시를 사용하던 핵심 아이디어는 "객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점"
 *
 * 💡 싱글톤 외 scope는 꼭 필요한곳에서만 사용! 무분별하게 사용시 유지보수 어려워짐
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 프록시(가짜)로 만든다.
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this );
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this );
    }
}
