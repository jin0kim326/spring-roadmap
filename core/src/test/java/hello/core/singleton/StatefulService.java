package hello.core.singleton;

public class StatefulService {
    private int price;  //상태를 유지하는 필드 -> 무상태로 설계 (공유필드를 지우고 바로 리턴시킨다)

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = "+ price);
        this.price = price;
    }

    /**
     * 무상태 설계방법
     * -> 바로 리턴 시켜버리자.
     */
//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = "+ price);
//        return price;
//    }

    public int getPrice() {
        return price;
    }
}
