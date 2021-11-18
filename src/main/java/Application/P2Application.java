package Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "Application")
@EntityScan("Application")
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement
public class P2Application {

	public static void main(String[] args) {
		SpringApplication.run(P2Application.class, args);
	}
}
