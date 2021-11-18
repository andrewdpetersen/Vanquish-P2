package Application.deezer;

import Application.models.Album;
import Application.models.Artist;
import Application.models.Genre;
import Application.models.Track;
import Application.services.APIClientService;
import org.json.JSONObject;

/**
 * JSONStringToModelConverter
 * This converter contains logic to parse API call information and convert the data to Models
 *
 * @author Andrew Peterson and Michael Reece
 * @date 11/6/2021
 */
public class JSONStringToModelConverter {
    /**
     *
     */
    public static Track trackConverter(String json){
        JSONObject jsonObject = new JSONObject(json);
        int track_id = jsonObject.getInt("id");
        String title = jsonObject.getString("title");

        JSONObject artistJson = jsonObject.getJSONObject("artist");
        int artist_id = artistJson.getInt("id");
        String name = artistJson.getString("name");
        String pictureUrl = artistJson.getString("picture_medium");
        Artist artist = new Artist(name,artist_id,pictureUrl);

        JSONObject albumJson = jsonObject.getJSONObject("album");
        int album_id = albumJson.getInt("id");
        String album_title = albumJson.getString("title");
        String release_date = albumJson.getString("release_date");

        Album album = new Album();
        album.setID(album_id);
        album.setAlbum_title(album_title);
        album.setDate(release_date);

        Track track = new Track();
        track.setTrack_id(track_id);
        track.setTitle(title);
        track.setAlbum(album);
        track.setArtist(artist);
        return track;
    }

    /**
     *
     * @param json
     * @return
     */
    public static Artist artistConverter(String json){
        JSONObject jsonObject = new JSONObject(json);
        int artist_id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String pictureUrl = jsonObject.getString("picture_medium");

        return new Artist(name,artist_id,pictureUrl);
    }

    /**
     *
     * @param json
     * @return
     */
    public static Album albumConverter(String json){
        JSONObject jsonObject = new JSONObject(json);
        int album_id = jsonObject.getInt("id");
        String title = jsonObject.getString("title");
        String release_date = jsonObject.getString("release_date");

        int genre_id = jsonObject.getInt("genre_id");

        String genreJsonString = "https://api.deezer.com/genre/"+genre_id;
        String genre_json_response = APIClientService.get(genreJsonString);

        Genre genre = genreConverter(genre_json_response);

        JSONObject artistJson = jsonObject.getJSONObject("artist");
        int artist_id = artistJson.getInt("id");
        String name = artistJson.getString("name");
        String pictureUrl = artistJson.getString("picture_medium");
        Artist artist = new Artist(name,artist_id,pictureUrl);

        Album album = new Album();
        album.setID(album_id);
        album.setAlbum_title(title);
        album.setDate(release_date);
        album.setGenre(genre);
        album.setArtist(artist);

        return album;
    }

    /**
     *
     * @param json
     * @return
     */
    public static Genre genreConverter(String json){
        JSONObject jsonObject = new JSONObject(json);
        int genre_id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String pictureUrl = jsonObject.getString("picture_medium");

        return new Genre(genre_id,name,pictureUrl);
    }
}
