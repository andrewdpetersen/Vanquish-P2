package Application;

import Application.deezer.AlbumSearch;
import Application.deezer.ArtistSearch;
import Application.deezer.TrackSearch;
import Application.models.Album;
import Application.models.Artist;
import Application.models.Track;
import Application.services.ConcertService;
import Application.services.TrackService;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication(scanBasePackages = "Application")
@EntityScan("Application")
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement
public class P2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(P2Application.class, args);

		TrackService service = context.getBean(TrackService.class);
		ConcertService service2 = context.getBean(ConcertService.class);

		System.out.println("TESTING: TrackSearch for \"kiwi\"");
		List<Track> trackList = TrackSearch.searchTracks("kiwi",3);
		for(Track track:trackList){
			System.out.println(track.toString());
		}
	}
}
