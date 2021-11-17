package Application.deezer;

import Application.models.Album;
import Application.services.APIClientService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlbumSearch {
    public static List<Album> albumSearch(String albumTitle, int numberOfResults){
        List<Album> albumSearch = new ArrayList<>();

        String urlStart = "https://api.deezer.com/search/album?q="+albumTitle+"&index=";
        for(int i=0;i<numberOfResults;i++){
            //This limits the results of our get request to 1 result per request
            String url = urlStart + i + "&limit=1";

            //This sends the request and assigns the response to a String
            String jsonResponse = APIClientService.get(url);

            //This section gets the artist data from the response
            JSONObject jsonObject = new JSONObject(jsonResponse);

            //data is NOT a string, it is a JSONArray with 1 JSONObject in it...
            JSONArray data = jsonObject.getJSONArray("data");

            if(data.isEmpty()){
                return albumSearch;
            }
            JSONObject jsonData = data.getJSONObject(0);

            int id = jsonData.getInt("id");

            String newUrl = "https://api.deezer.com/album/" + id;
            String stringJsonAlbum = APIClientService.get(newUrl);

            Album resultAlbum = JSONStringToModelConverter.albumConverter(stringJsonAlbum);
            albumSearch.add(resultAlbum);
        }
        return albumSearch;
    }
}
