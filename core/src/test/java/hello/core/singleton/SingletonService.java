package hello.core.singleton;

/**
 * 🔥싱글톤 패턴
 * 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴 (객체 인스턴스를 2개 이상 생성하지 못하도록 막아야함!)
 *
 * 1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
 * 2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회가능 (항상 같은 인스턴스를 반환)
 * 3. 딱 1개의 객체인스턴스만 존재해야 하므로 생성자를 private으로 막는다 (외부에서 생성 불가)
 *
 * 🔥 싱글톤 단점
 * -> 내가 필요한건 logic()인데, 패턴구현을 위한 코드 자체가 많이 들어감
 * 의존관계상 클라이언트가 구체클래스를 의존 -> DIP위반
 * OCP 위반 가능성 높음
 * 테스트 어려움
 *  결론 : 유연성이 떨어짐
 *
 *  스프링(싱글톤 컨테이너)는 이 싱글톤의 단점을 모두 해결
 *
 *  참고 : 스프링의 기본 빈 등록방식은 싱글톤이지만, 요청마다 새로운 객체를 생성해서 반환하는 기능도 제공 (빈 스코프) - 99% 싱글톤을 사용
 *
 *  🔥 싱글톤 방식의 주의점
 *   싱글톤 같은 객체 인스턴스를 하나만 생성해서 공유하는 방식은 상태를 유지하게 설계하면 안됨 -> 무상태(stateless)로 설계
 *   - 특정 클라이언트에 의존적인 필드가 있으면 안됨
 *   - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안됨
 *   - 가급적 읽기만 가능
 */
public class SingletonService {
    private static final SingletonService instance = new SingletonService();    //static영역에 singletonService가 하나만 딱 올라감

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
