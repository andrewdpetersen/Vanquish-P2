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

		TrackService service = context.getBean(TrackService.class);
		ConcertService service2 = context.getBean(ConcertService.class);

		System.out.println("TESTING: TrackSearch for \"kiwi\"");
		List<Track> trackList = TrackSearch.searchTracks("kiwi",3);
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
			List<Track> trackListBadName = TrackSearch.searchTracks("xxxxyxyxxxyx",1);
			for(Track track:trackListBadName){
				System.out.println(track.toString());
			}
		}catch(JSONException jex){
			System.out.println("No search results for this search");
			jex.getClass();
		}

		/**
		 * Test of albumSearch *** If we ask for 8 results, and there are only 7 results in deezer
		 * we will have an exception thrown, and return 0 results.
		 */
		List<Album> albumSearchTest = AlbumSearch.albumSearch("the%20ugly%20organ",3);
		for(Album album:albumSearchTest){
			System.out.println(album.toString());
		}
		/**
		 * Test of artistSearch
		 */
		List<Artist> artistSearchTest = ArtistSearch.artistSearch("Cursive",2);
		for(Artist artist:artistSearchTest){
			System.out.println(artist.toString());
		}
		List<Track> topTracks = ArtistSearch.getTopTracks(artistSearchTest.get(0),2);
		for(Track track:topTracks){
			System.out.println(track.toString());
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
