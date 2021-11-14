package VanquishP2.Deezer;

import VanquishP2.Application.Beans.Models.Album;
import VanquishP2.Application.Beans.Models.Artist;
import VanquishP2.Application.Beans.Models.Genre;
import VanquishP2.Application.Beans.Models.Track;
import VanquishP2.Application.Beans.Service.APIClientService;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectToModelConverter {
    public static Track trackConverter(JSONObject jsonObject) throws JSONException {

        int trackID = jsonObject.getInt("id");
        String title = jsonObject.getString("title");

        //This gets the artist JSONObject and pulls data from it
        JSONObject artistJson = jsonObject.getJSONObject("artist");
        int artistId = artistJson.getInt("id");
        String name = artistJson.getString("name");
        String pictureUrl = artistJson.getString("picture_medium");
        Artist artist = new Artist(name,artistId,pictureUrl);

        JSONObject albumJson = jsonObject.getJSONObject("album");
        int albumID = albumJson.getInt("id");
        String albumTitle = albumJson.getString("title");
        String releaseDate = albumJson.getString("release_date");

        Album album = new Album();
        album.setAlbumID(albumID);
        album.setAlbumTitle(albumTitle);
        album.setDate(releaseDate);

        Track track = new Track();
        track.setTrackID(trackID);
        track.setTitle(title);
        track.setAlbum(album);
        track.setArtist(artist);
        return track;
    }

    public static Artist artistConverter(JSONObject jsonObject) throws JSONException {
        int artistId = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String pictureUrl = jsonObject.getString("picture_medium");

        return new Artist(name, artistId, pictureUrl);
    }

    public static Album albumConverter(JSONObject jsonObject) throws JSONException {
        int albumID = jsonObject.getInt("id");
        String title = jsonObject.getString("title");
        String releaseDate = jsonObject.getString("release_date");

        int genre_id = jsonObject.getInt("genre_id");

        //GET from deezer the Genre by the ID
        String genreJsonString = "https://api.deezer.com/genre/"+genre_id;
        String genre_json_response = APIClientService.get(genreJsonString);

        //call genreConverter here
        Genre genre = genreConverter(genre_json_response);

        Album album = new Album();
        album.setAlbumID(albumID);
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

    public static Track topTrackConverter(JSONObject jsonTrack, Artist artist, Album album) throws JSONException {
        int trackID = jsonTrack.getInt("id");
        String title = jsonTrack.getString("title");
        Track track = new Track(trackID, title);
        track.setArtist(artist);
        track.setAlbum(album);
        return track;
    }
}