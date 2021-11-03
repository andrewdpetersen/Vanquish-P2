package Application;

import Application.beans.TestBean;
import Application.beans.models.Track;
import Application.beans.service.TrackService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "java")
@EntityScan("java")
@ComponentScan("java")
public class P2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(P2Application.class, args);
		TestBean bean = context.getBean(TestBean.class);
		System.out.println(bean.getOtherBean().getTest());

		TrackService service = context.getBean(TrackService.class);

		Track track = new Track(1,"Never Gonna Give You Up",20);
		service.save(track);

		Track track2 = service.getTrack(1);
		System.out.println(track2.toString());
	}
}
