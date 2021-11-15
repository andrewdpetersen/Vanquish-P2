package Application.deezer;

import Application.models.Track;
import Application.services.APIClientService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * TrackSearch
 * Handles putting together requests to our 3rd party API and returning the results
 * @date 11/6/21
 * @author Michael Reece, Andrew Petersen
 */
public class TrackSearch {

    public static List<Track> searchTracks(String track_title, int numberOfResults){
        List<Track> trackSearch = new ArrayList<>();

        String urlStart = "https://api.deezer.com/search/track?q="+track_title+"&index=";
        for(int i=0;i<numberOfResults;i++) {
            //This limits the results of our get request to 1 result per request
            String url = urlStart + i + "&limit=1";

            //This sends the request and assigns the response to a String
            String jsonResponse = APIClientService.get(url);

            //This section gets the track data from the response
            JSONObject jsonObject = new JSONObject(jsonResponse);

            //data is NOT a string, it is a JSONArray with 1 JSONObject in it...
            JSONArray data = jsonObject.getJSONArray("data");
            if(data.isEmpty()){
                return trackSearch;
            }
            JSONObject jsonData = data.getJSONObject(0);

            int id = jsonData.getInt("id");

            //This sends a new request for the track by id
            String newURL = "https://api.deezer.com/track/" + id;
            String stringJsonTrack = APIClientService.get(newURL);

            Track resultTrack = JSONStringToModelConverter.trackConverter(stringJsonTrack);

            trackSearch.add(resultTrack);
        }
        return trackSearch;
    }
}