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

		// 어플리케이션 종료
		log.info("✅ Server Started...");
	}

}
