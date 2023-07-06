package hello.core.lifecycle;

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
 */
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 = " + url);
        connect();
        call("초기화 연결 메세지");
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
}
