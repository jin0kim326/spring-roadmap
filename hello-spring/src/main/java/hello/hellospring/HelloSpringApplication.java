package hello.hellospring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class HelloSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
		log.info("✅ Server Started...");
	}
}

/**
 * AOP가 필요한 상황
 * -> 모든 메서드의 호출 시간을 측정하고 싶다면??
 * 1000개의 메소드에 초단위로 측정 -> 추후 ms로 변경한다면? 또 1000개를 다 수정?
 */
