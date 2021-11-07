package Application.deezer;

import Application.models.Album;
import Application.models.Artist;
import Application.models.Genre;
import Application.models.Track;
import Application.services.APIClientService;
import org.json.JSONObject;

public class JSONToModelConverter {

    public static Track trackConverter(String json){

        JSONObject jsonObject = new JSONObject(json);
        int track_id = jsonObject.getInt("id");
        String title = jsonObject.getString("title");

        String artistString = jsonObject.getString("artist");
        Artist artist = artistConverter(artistString);
        String albumString = jsonObject.getString("album");
        //call jsonToAlbumConverter
        Album album = albumConverter(albumString);

        Track track = new Track();
        track.setTrack_id(track_id);
        track.setTitle(title);
        track.setAlbum(album);
        track.setArtist(artist);
        return track;
    }

    public static Artist artistConverter(String json){
        JSONObject jsonObject = new JSONObject(json);
        int artist_id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String pictureUrl = jsonObject.getString("picture_medium");

        return new Artist(name,artist_id,pictureUrl);
    }

    public static Album albumConverter(String json){
        JSONObject jsonObject = new JSONObject(json);
        int album_id = jsonObject.getInt("id");
        String title = jsonObject.getString("title");
        String release_date = jsonObject.getString("release_date");

        int genre_id = jsonObject.getInt("genre_id");
        //GET from deezer the Genre by the ID
        String genreJsonString = "https://api.deezer.com/genre/"+genre_id;
        String genre_json_response = APIClientService.get(genreJsonString);

        //call genreConverter here
        Genre genre = genreConverter(genre_json_response);

        Album album = new Album();
        album.setID(album_id);
        album.setAlbum_title(title);
        album.setDate(release_date);
        album.setGenre(genre);

        return album;
    }

    public static Genre genreConverter(String json){
        JSONObject jsonObject = new JSONObject(json);
        int genre_id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String pictureUrl = jsonObject.getString("picture_medium");

        return new Genre(genre_id,name,pictureUrl);
    }
}
