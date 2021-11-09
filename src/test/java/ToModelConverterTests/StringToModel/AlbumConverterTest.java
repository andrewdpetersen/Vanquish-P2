package ToModelConverterTests.StringToModel;

import org.junit.*;

import static Application.deezer.JSONStringToModelConverter.albumConverter;

public class AlbumConverterTest {
    private String albumJson;

    @BeforeClass //before ALL tests have started
    public static void beforeClass() {
    }

    @AfterClass //after ALL tests completed
    public static void afterClass() {
    }

    @Before //before each test starts
    public void before() {
        albumJson = "{id:"+1+",title:Album Title,release_date:"+"\"01/01/2001\"";
        //need to add genre String here
    }

    @After //after each test completes
    public void after() {
        albumJson = null;
    }

    @Test
    public void albumConverterTest(){
        int album_id = albumConverter(albumJson).getID();
        String title = albumConverter(albumJson).getAlbum_title();
        String date = albumConverter(albumJson).getDate();

        Assert.assertEquals(album_id,1);
        Assert.assertEquals(title,"Album Title");
        Assert.assertEquals(date,"01/01/2001");
    }

}
