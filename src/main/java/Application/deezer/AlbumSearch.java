package Application.deezer;

import Application.models.Album;
import Application.services.APIClientService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * AlbumSearch
 * Uses Deezer API calls to search for an Album
 *
 * @author Andrew Peterson and Michael Reece
 * @date 11/6/2021
 */
public class AlbumSearch {
    public static List<Album> albumSearch(String albumTitle, int numberOfResults){
        List<Album> albumSearch = new ArrayList<>();

        String urlStart = "https://api.deezer.com/search/album?q="+albumTitle+"&index=";
        for(int i=0;i<numberOfResults;i++){
            String url = urlStart + i + "&limit=1";

            String jsonResponse = APIClientService.get(url);

            JSONObject jsonObject = new JSONObject(jsonResponse);

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
