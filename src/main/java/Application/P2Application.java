package Application;

import Application.deezer.TrackSearch;
import Application.models.Concert;
import Application.models.Track;
import Application.services.APIClientService;
import Application.services.ConcertService;
import Application.services.TrackService;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

@SpringBootApplication(scanBasePackages = "Application")
@EntityScan("Application")
@ComponentScan("Application")
@EnableTransactionManagement
public class P2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(P2Application.class, args);

		TrackService service = context.getBean(TrackService.class);
		ConcertService service2 = context.getBean(ConcertService.class);

		System.out.println("TESTING: TrackSearch for \"kiwi\"");
		List<Track> trackList = TrackSearch.searchTracks("kiwi");
		for(Track track:trackList){
			System.out.println(track.toString());
		}

		/**
		 * The following block shows an issue, the code throws a JSONException,
		 * because there are no search results from deezer, we should be able to recover
		 * from this, by sending a message back to the front end.
		 */
		try{
			System.out.println("TESTING: TrackSearch for \"xxxxyxyxxxyx\"");
			List<Track> trackListBadName = TrackSearch.searchTracks("xxxxyxyxxxyx");
			for(Track track:trackListBadName){
				System.out.println(track.toString());
			}
		}catch(JSONException jex){
			System.out.println("No search results for this search");
			jex.getClass();
		}


//		Track track = new Track(1,"Never Gonna Give You Up");
//		service.save(track);
//
//		Concert concert = new Concert(1,"10/23/22","Daft Punk Concert",null,null);
//		service2.save(concert);
//		System.out.println(service2.getConcert(1));
//		service2.deleteConcert(concert);

//		String json = APIClientService.get("https://api.deezer.com/track/3135556");
//		System.out.println("Test track information: \n" + json);

//		Track track2 = service.getTrack(1);
//		System.out.println(track2.toString());
	}
}
