package ToModelConverterTests.StringToModel;

import Application.models.Artist;
import org.json.JSONException;
import org.junit.*;

import static Application.deezer.JSONStringToModelConverter.artistConverter;

public class ArtistConverterTest {
    private String artistJson;

    @BeforeClass //before ALL tests have started
    public static void beforeClass() {
    }

    @AfterClass //after ALL tests completed
    public static void afterClass() {
    }

    @Before //before each test starts
    public void before() {
        artistJson = "{id:"+1+",name:Artist Name,picture_medium:picURL}";
    }

    @After //after each test completes
    public void after() {
        artistJson = null;
    }

    @Test
    public void artistConverterTest() throws JSONException {
        int artist_id = artistConverter(artistJson).getID();
        String name = artistConverter(artistJson).getName();
        String picURL = artistConverter(artistJson).getImage_url();

        Assert.assertEquals(artist_id,1);
        Assert.assertEquals(name,"Artist Name");
        Assert.assertEquals(picURL,"picURL");
        Assert.assertEquals(artistConverter(artistJson).getClass(), Artist.class);
    }
}
