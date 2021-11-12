package Application;

import Application.deezer.AlbumSearch;
import Application.deezer.ArtistSearch;
import Application.deezer.TrackSearch;
import Application.models.Album;
import Application.models.Artist;
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

		System.out.println("TESTING: TrackSearch for \"kiwi\"");
		List<Track> trackList = TrackSearch.searchTracks("kiwi",3);
		for(Track track:trackList){
			System.out.println(track.toString());
		}
	}
}
