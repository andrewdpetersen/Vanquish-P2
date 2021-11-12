package VanquishP2.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "VanquishP2")
@EntityScan("VanquishP2.Application.Beans.Models")
@PropertySource(value = "classpath:application.properties")
public class P2Application {

	public static void main(String[] args) {
		SpringApplication.run(P2Application.class, args);
	}

}
