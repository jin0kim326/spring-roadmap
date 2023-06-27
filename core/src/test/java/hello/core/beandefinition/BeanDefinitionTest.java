package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * BeanDefinition을 직접 생성해서 스프링 컨테이너에 등록할 수 도 있지만, 잘 안함.
 * 💡 스프링이 다양한 형태의 설정 정보를 BeanDefinition으로 추상화 해서 사용한다!!
 *
 * 스프링빈을 등록 하는방법 크게 두가지
 * - 직접 스프링 빈을 등록하는 방법 (xml)
 * - 팩토리 메소드를 통해서 사용하는 방법 (@Bean)
 *
 */
public class BeanDefinitionTest {
//    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("bean 설정 메타정보 확인")
    public void findApplicationBean() throws Exception {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println(
                        " beanDefinitionName = " + beanDefinitionName +
                        " beanDefinition = " + beanDefinition
                        );
            }
        }
    }
}
