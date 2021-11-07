package Application.deezer;

import Application.models.Track;
import Application.services.APIClientService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrackSearch {

    public static List<Track> searchTracks(String track_title){
        List<Track> trackSearch = new ArrayList<>();

        String urlStart = "https://api.deezer.com/search/track?q="+track_title+"&index=";
        for(int i=0;i<5;i++) {
            //This limits the results of our get request to 1 result per request
            String url = urlStart + i + "&limit=1";

            //This sends the request and assigns the response to a String
            String jsonResponse = APIClientService.get(url);

            //This section gets the track id from the response
            JSONObject jsonObject = new JSONObject(jsonResponse);

            //data is NOT a string, it is a JSONArray with 1 JSONObject in it...
            JSONArray data = jsonObject.getJSONArray("data");
            JSONObject jsonData = data.getJSONObject(0);

            int id = jsonData.getInt("id");

            //This sends a new request for the track by id
            String newURL = "https://api.deezer.com/track/" + id;
            String stringJsonTrack = APIClientService.get(newURL);

            Track resultTrack = JSONToModelConverter.trackConverter(stringJsonTrack);

            trackSearch.add(resultTrack);
        }
        return trackSearch;
    }

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