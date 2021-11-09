package ToModelConverterTests.ObjectToModel;

import Application.models.Album;
import Application.models.Artist;
import Application.models.Track;
import org.junit.*;
import org.json.JSONObject;

import static Application.deezer.JSONObjectToModelConverter.topTrackConverter;

public class TopTrackConverterTest {

    private Album album;
    private Artist artist;
    private JSONObject jsonTrack;
    private Track track;

    @BeforeClass //before ALL tests have started
    public static void beforeClass() {
    }

    @AfterClass //after ALL tests completed
    public static void afterClass() {
    }

    @Before //before each test starts
    public void before() {
        album = new Album();
        album.setID(2);
        album.setAlbum_title("Album Title");
        album.setDate("01/03/1997");
        artist = new Artist("Cursive",1,"no_Picture");
        jsonTrack = new JSONObject();
        jsonTrack.put("id",3);
        jsonTrack.put("title","Track Title");
        album.setArtist(artist);
        track = new Track();
        track.setTrack_id(3);
        track.setTitle("Track Title");
    }

    @After //after each test completes
    public void after() {
        album = null;
        artist = null;
        jsonTrack = null;
        track = null;
    }

    @Test
    public void testTopTrackConverter(){
        int trackID = topTrackConverter(jsonTrack,artist,album).getTrack_id();
        String trackTitle = topTrackConverter(jsonTrack,artist,album).getTitle();
        Class thisClass = topTrackConverter(jsonTrack,artist,album).getClass();
        int artistID = topTrackConverter(jsonTrack,artist,album).getArtist().getID();
        String artistName = topTrackConverter(jsonTrack,artist,album).getArtist().getName();
        String artistPicture = topTrackConverter(jsonTrack,artist,album).getArtist().getImage_url();
        int albumID = topTrackConverter(jsonTrack,artist,album).getAlbum().getID();
        String albumTitle = topTrackConverter(jsonTrack,artist,album).getAlbum().getAlbum_title();
        String release_date = topTrackConverter(jsonTrack,artist,album).getAlbum().getDate();
        String jsonString = topTrackConverter(jsonTrack,artist,album).toString();

        Assert.assertEquals(thisClass,track.getClass());
        Assert.assertEquals(trackID, 3);
        Assert.assertEquals(trackTitle, "Track Title");
        Assert.assertEquals(artistID, 1);
        Assert.assertEquals(artistName,"Cursive");
        Assert.assertEquals(artistPicture,"no_Picture");
        Assert.assertEquals(albumID, 2);
        Assert.assertEquals(albumTitle,"Album Title");
        Assert.assertEquals(release_date,"01/03/1997");
        Assert.assertEquals(jsonString, track.toString());
    }

}
