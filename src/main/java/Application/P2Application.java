package Application;

import Application.models.Track;
import Application.services.TrackService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = "Application")
@EntityScan("Application.models")
@ComponentScan("Application")
public class P2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(P2Application.class, args);

		Track bean = context.getBean(Track.class);
		System.out.println(bean.getTitle());

		TrackService service = context.getBean(TrackService.class);

		Track model = new Track(1, "test");
		Track model1 = new Track(2, "test2");
		Track model2 = new Track(3, "test3");
		service.save(model);
		service.save(model1);
		service.save(model2);

	}
}
