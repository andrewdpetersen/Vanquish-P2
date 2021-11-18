package Application.deezer;

import Application.models.Album;
import Application.models.Artist;
import Application.models.Track;
import Application.services.APIClientService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ArtistSearch
 * Uses Deezer API calls to search for an Artist
 *
 * @author Andrew Peterson and Michael Reece
 * @date 11/6/2021
 */
public class ArtistSearch {

    public static List<Artist> artistSearch(String artistName, int numberOfResults){
        List<Artist> artistSearch = new ArrayList<>();

        String urlStart = "https://api.deezer.com/search/artist?q="+artistName+"&index=";
        for(int i=0;i<numberOfResults;i++){
            String url = urlStart + i + "&limit=1";

            String jsonResponse = APIClientService.get(url);

            JSONObject jsonObject = new JSONObject(jsonResponse);

            JSONArray data = jsonObject.getJSONArray("data");
            if(data.isEmpty()){
                return artistSearch;
            }

            JSONObject jsonData = data.getJSONObject(0);

            int id = jsonData.getInt("id");

            String newUrl = "https://api.deezer.com/artist/" + id;
            String stringJsonArtist = APIClientService.get(newUrl);

            Artist resultArtist = JSONStringToModelConverter.artistConverter(stringJsonArtist);

            artistSearch.add(resultArtist);
        }
        return artistSearch;
    }

    public static List<Track> getTopTracks(Artist artist,int numberOfTracks){

        List<Track> topTracks = new ArrayList<>();
        String request = "https://api.deezer.com/artist/"+artist.getID()+"/top?limit="+numberOfTracks;

        String stringJsonTracks = APIClientService.get(request);
        JSONObject jsonObject = new JSONObject(stringJsonTracks);

        JSONArray data = jsonObject.getJSONArray("data");
        for(int i=0;i<data.length();i++){

            JSONObject jsonTrack = data.getJSONObject(i);
            JSONObject jsonAlbum = jsonTrack.getJSONObject("album");
            int albumID = jsonAlbum.getInt("id");

            String albumRequest = "https://api.deezer.com/album/"+albumID;
            String albumJsonString = APIClientService.get(albumRequest);
            Album album = JSONStringToModelConverter.albumConverter(albumJsonString);

            Track track = JSONObjectToModelConverter.topTrackConverter(jsonTrack,artist,album);
            topTracks.add(track);
        }
        return topTracks;
    }
}
