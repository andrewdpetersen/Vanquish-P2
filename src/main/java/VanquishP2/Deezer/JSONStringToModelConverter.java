package VanquishP2.Deezer;

import VanquishP2.Application.Beans.Models.Album;
import VanquishP2.Application.Beans.Models.Artist;
import VanquishP2.Application.Beans.Models.Genre;
import VanquishP2.Application.Beans.Models.Track;
import VanquishP2.Application.Beans.Service.APIClientService;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONStringToModelConverter {

    public static Track trackConverter(String json) throws JSONException {

        JSONObject jsonObject = new JSONObject(json);
        int track_id = jsonObject.getInt("id");
        String title = jsonObject.getString("title");

        //This gets the artist JSONObject and pulls data from it
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
        album.setAlbumID(album_id);
        album.setAlbumTitle(title);
        album.setDate(release_date);

        Track track = new Track();
        track.setTrackID(track_id);
        track.setTitle(title);
        track.setAlbum(album);
        track.setArtist(artist);
        return track;
    }

    public static Artist artistConverter(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        int artistID = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String pictureUrl = jsonObject.getString("picture_medium");

        return new Artist(name, artistID, pictureUrl);
    }

    public static Album albumConverter(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        int album_id = jsonObject.getInt("id");
        String title = jsonObject.getString("title");
        String releaseDate = jsonObject.getString("release_date");

        int genre_id = jsonObject.getInt("genre_id");
        //GET from deezer the Genre by the ID
        String genreJsonString = "https://api.deezer.com/genre/"+genre_id;
        String genre_json_response = APIClientService.get(genreJsonString);

        //call genreConverter here
        Genre genre = genreConverter(genre_json_response);

        Album album = new Album();
        album.setAlbumID(album_id);
        album.setAlbumTitle(title);
        album.setDate(releaseDate);
        album.setGenre(genre);

        return album;
    }

    public static Genre genreConverter(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        int genre_id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String pictureUrl = jsonObject.getString("picture_medium");

        return new Genre(genre_id,name,pictureUrl);
    }
}