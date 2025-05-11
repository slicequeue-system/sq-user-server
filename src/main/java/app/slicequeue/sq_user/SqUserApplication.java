package app.slicequeue.sq_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SqUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqUserApplication.class, args);
	}

}
