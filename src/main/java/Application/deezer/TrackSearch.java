package Application.deezer;

import Application.models.Track;
import Application.services.APIClientService;

import java.util.List;

public class TrackSearch {

//    public static List<Track> searchTracks(String track_title){
//        String url = "https://api.deezer.com/search/track?q=" + track_title;
//        String json_response = APIClientService.get(url);
//    }

}
//https://api.deezer.com/search/track?q=eminem
/**
 * Search by album, artist, track
 * Search-type comes from front end
 * Marshall the request String based on that information
 * Check if model exists, and add information as necessary
 * If query is for an Artist, query Deezer by default
 * (When we query an Artist, we get top 5 tracks, then albums... if they want a specific track,
 * ...search by Track or album)
 * (If they request a track or album by name, search deezer, because different tracks can have the same name)
 * If not query Deezer
 * Return results as a List of whichever model is requested (just for data transfer, no persistence)
 * When the front end makes a selection from results, we send that model back
 * Find what information from Deezer we need to persist a consistent model in our database
 */