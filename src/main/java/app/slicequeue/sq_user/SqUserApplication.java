package app.slicequeue.sq_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"app.slicequeue.sq_user.user.command.infra",
		"app.slicequeue.common.messagerelay.outbox"
})
@EntityScan(basePackages = {
		"app.slicequeue.sq_user.user.command.domain",
		"app.slicequeue.common.messagerelay.outbox"
})
public class SqUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqUserApplication.class, args);
	}
}
