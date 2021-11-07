package Application;

<<<<<<< HEAD
import Application.models.Track;
import Application.services.TrackService;
=======
import Application.models.Concert;
import Application.models.Track;
import Application.services.APIClientService;
import Application.services.ConcertService;
import Application.services.TrackService;
import ch.qos.logback.core.net.SyslogOutputStream;
>>>>>>> bd93c80105452fa6d2fc22b344109e5ed39147a9
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.xml.bind.SchemaOutputResolver;


@SpringBootApplication(scanBasePackages = "Application")
<<<<<<< HEAD
@EntityScan("Application.models")
@ComponentScan("Application")
=======
@EntityScan("Application")
@ComponentScan("Application")
@EnableTransactionManagement
>>>>>>> bd93c80105452fa6d2fc22b344109e5ed39147a9
public class P2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(P2Application.class, args);

<<<<<<< HEAD
		Track bean = context.getBean(Track.class);
		System.out.println(bean.getTitle());

		TrackService service = context.getBean(TrackService.class);

		Track model = new Track(1, "test");
		Track model1 = new Track(2, "test2");
		Track model2 = new Track(3, "test3");
		service.save(model);
		service.save(model1);
		service.save(model2);

=======
		TrackService service = context.getBean(TrackService.class);
		ConcertService service2 = context.getBean(ConcertService.class);

//		Track track = new Track(1,"Never Gonna Give You Up");
//		service.save(track);
//
//		Concert concert = new Concert(1,"10/23/22","Daft Punk Concert",null,null);
//		service2.save(concert);
//		System.out.println(service2.getConcert(1));
//		service2.deleteConcert(concert);

//		String json = APIClientService.get("https://api.deezer.com/track/3135556");
//		System.out.println("Test track information: \n" + json);

		Track track2 = service.getTrack(1);
		System.out.println(track2.toString());
>>>>>>> bd93c80105452fa6d2fc22b344109e5ed39147a9
	}
}
