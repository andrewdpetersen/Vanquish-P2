package Application;

import Application.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class P2Application {

	public static void main(String[] args) {
		SpringApplication.run(P2Application.class, args);
		User andrew = new User();
		andrew.setEmail("andrew@andrew.com");
		andrew.setFirst_name("Andrew");
		andrew.setLast_name("Petersen");
		andrew.setUsername("andrewp");
		andrew.setPassword("password1");
		andrew.setRole(false);
		andrew.setUser_id(1001);

		System.out.println("My name is "+andrew.getFirst_name()+" and I just created an account!");
	}
}
