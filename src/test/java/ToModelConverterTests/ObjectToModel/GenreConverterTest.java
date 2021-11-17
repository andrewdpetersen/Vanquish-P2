package ToModelConverterTests.ObjectToModel;

import org.junit.*;

import static Application.deezer.JSONObjectToModelConverter.genreConverter;

/**
 * This class tests the GenreConverter method in (Application.deezer.JSONObjectToModelConverter.genreConverter)
 * @author Andrew Petersen
 * @date 11/08/21
 */
public class GenreConverterTest {
    private String json;

    @BeforeClass //before ALL tests have started
    public static void beforeClass() {
    }

    @AfterClass //after ALL tests completed
    public static void afterClass() {
    }

    @Before //before each test starts
    public void before() {
        json = "Genre{"+"id="+1+","+"name"+"="+"Genre Name"+","+"picture_medium"+"="+"pictureURL"+"}";
    }

    @After //after each test completes
    public void after() {
        json="";
    }

    @Test
    public void genreConverterTest(){
        int genreID = genreConverter(json).getGenreID();
        String genreName = genreConverter(json).getGenreName();
        String genrePic = genreConverter(json).getImageURL();

        Assert.assertEquals(genreID,1);
        Assert.assertEquals(genreName,"Genre Name");
        Assert.assertEquals(genrePic,"pictureURL");
    }
}
