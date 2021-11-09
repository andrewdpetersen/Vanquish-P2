package ToModelConverterTests.StringToModel;

import org.junit.*;

import static Application.deezer.JSONObjectToModelConverter.genreConverter;

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
        json = "{id:"+1+",name:Genre Name,picture_medium:pictureURL}";
    }

    @After //after each test completes
    public void after() {
        json="";
    }

    @Test
    public void genreConverterTest(){
        int genreID = genreConverter(json).getGenre_id();
        String genreName = genreConverter(json).getGenre_name();
        String genrePic = genreConverter(json).getImage_url();

        Assert.assertEquals(genreID,1);
        Assert.assertEquals(genreName,"Genre Name");
        Assert.assertEquals(genrePic,"pictureURL");
    }
}
