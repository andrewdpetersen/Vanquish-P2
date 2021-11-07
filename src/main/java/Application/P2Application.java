package Application;
import Application.models.Track;
import Application.services.TrackService;
import Application.services.ConcertService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.xml.bind.SchemaOutputResolver;


@SpringBootApplication(scanBasePackages = "Application")
@EntityScan("Application")
@ComponentScan("Application")
@EnableTransactionManagement

public class P2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(P2Application.class, args);

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

	}
}
