package ToModelConverterTests.StringToModel;

import Application.models.Track;
import org.junit.*;

import static Application.deezer.JSONStringToModelConverter.trackConverter;

/**
 * This class tests the TrackConverter method in (Application.deezer.JSONStringToModelConverter.trackConverter)
 * @author Andrew Petersen
 * @date 11/08/21
 */
public class TrackConverterTest {
    private String trackJson;

    @BeforeClass //before ALL tests have started
    public static void beforeClass() {
    }

    @AfterClass //after ALL tests completed
    public static void afterClass() {
    }

    @Before //before each test starts
    public void before() {
        trackJson = "{id:"+1+",title:Song Title,artist:{id:"+2+",name:Artist Name,picture_medium:pictureURL},album:{id:"+3+",title:Album Title,release_date:"+"\"10/14/1988\"}}";//,type:track}";

    }

    @After //after each test completes
    public void after() {
        trackJson = null;
    }

    @Test
    public void trackConverterTest(){
        int track_id = trackConverter(trackJson).getTrack_id();
        String track_title = trackConverter(trackJson).getTitle();

        int album_id = trackConverter(trackJson).getAlbum().getID();
        String album_title = trackConverter(trackJson).getAlbum().getAlbum_title();
        String release = trackConverter(trackJson).getAlbum().getDate();

        int artist_id = trackConverter(trackJson).getArtist().getID();
        String artist_name = trackConverter(trackJson).getArtist().getName();
        String artistPic = trackConverter(trackJson).getArtist().getImage_url();

        Assert.assertEquals(track_id,1);
        Assert.assertEquals(track_title,"Song Title");
        Assert.assertEquals(album_id,3);
        Assert.assertEquals(album_title,"Album Title");
        Assert.assertEquals(release,"10/14/1988");
        Assert.assertEquals(artist_id,2);
        Assert.assertEquals(artist_name,"Artist Name");
        Assert.assertEquals(artistPic,"pictureURL");
        Assert.assertEquals(trackConverter(trackJson).getClass(), Track.class);
    }
}
