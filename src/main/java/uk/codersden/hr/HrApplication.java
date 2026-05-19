package uk.codersden.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "uk.codersden")
@EnableJpaRepositories(basePackages = "uk.codersden")
@EntityScan(basePackages = "uk.codersden")
public class HrApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

}
