package VanquishP2.DataGenerators;

import VanquishP2.Application.Beans.ModelServices.LocationService;
import VanquishP2.Application.Beans.ModelServices.UserInfoService;
import VanquishP2.Application.Beans.ModelServices.UserService;
import VanquishP2.Application.Beans.Models.Location;
import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.Models.UserInfo;
import VanquishP2.DTOs.UserRegistrationDTO;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Random;

import static VanquishP2.DataGenerators.UserGen.DataLists.*;


//@Service
public class UserGen {
    private final static Random rand = new Random();
    private static int index;
    private final UserService userService;
    private final UserInfoService userInfoService;
    private final LocationService locationService;

    //@Autowired
    public UserGen(UserService userService, UserInfoService userInfoService, LocationService locationService) {
        this.locationService = locationService;
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    //@PostConstruct
    private void populate() {
        for (int i = 0; i < 20; i++) {
            UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(getAFirstName(), getALastName(),
                    getAUsername(), getAPassword(), getAEmail(), getACity(), getAState());

            UserInfo userInfo = new UserInfo(userRegistrationDTO);
            locationService.save(new Location(userRegistrationDTO.getCity(), userRegistrationDTO.getState()));
            userInfoService.save(userInfo);

            User user = new User(User.Role.BASIC, userInfo);
            userService.save(user);

            userInfo = userInfoService.getByFirstName(userInfo.getFirstName());
            userInfo.setUser(user);
            userInfoService.save(userInfo);
        }
    }

    public static Location getALocation() {
        Random rand = new Random();
        int index = rand.nextInt(cities.length);
        return new Location(cities[index], states[index]);
    }

    public static String getACity(){
        Random rand = new Random();
        int index = rand.nextInt(cities.length);
        return cities[index];
    }

    public static String getAState(){
        Random rand = new Random();
        int index = rand.nextInt(cities.length);
        return states[index];
    }

    public static String getAFirstName() {
        index = rand.nextInt(names.length);
        return names[index];
    }

    public static String getALastName() {
        index = rand.nextInt(names.length);
        return names[index];
    }

    public static String getAPassword() {
        index = rand.nextInt(passwords.length);
        return passwords[index];
    }

    public static String getAUsername() {
        index = rand.nextInt(usernames.length);
        return usernames[index];
    }

    public static String getAEmail() {
        index = rand.nextInt(emails.length);
        return emails[index];
    }

    static class DataLists {
        public static String[] states = {
                "Freeport",
                "Alcoa",
                "Fayetteville",
                "Vail",
                "Pekin",
                "Ishpeming",
                "Puyallup",
                "Houlton",
                "Rome",
                "Pampa",
        };

        public static String[] cities = {
                "Nacogdoches",
                "Queens",
                "Elgin",
                "West Lafayette",
                "Cushing",
                "Payson",
                "South Hadley",
                "Daytona Beach",
                "Meriden",
                "Randolph",
        };

        public static String[] names = {
                "Chace", "Nielsen",
                "Leticia", "Allen",
                "Lyric", "Branch",
                "Cooper", "Malone",
                "Kasen", "Hardy",
                "Keira", "Poole",
                "Braedon", "Wood",
                "Porter", "Gentry",
                "Augustus", "James",
                "Rodolfo", "Patton",
                "Iyana", "Berry",
                "Dawson", "Sheppard"
        };

        public static @Length(min = 8, max = 20)
        @NotNull(message = "*Crickets*.. That's an empty password!")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>])$",
                message = "Come on. Your password should be strong, like Russian.")
        String[] passwords = {
                "m]hQB7>d", "wRr^){3Q",
                "h@w/V[8k", "]tqWC_D4",
                "kKL9?sx6", "WmHu{V9K",
                "(@veR46w", ">?x&D2N9",
                ">6J.yd7t", "6Qm@av{/",
                "t(6_R8^{", "j)4A?[=h",
                "!kZ%h[6b", "q7yS[gB{",
                "_3$nJQ,r", "UCaH5[$S",
                "D5p_,yVG", "J29a-j?@",
                "DRyQp3}]", "n3cYM@B^"
        };

        public static @Length(min = 5, max = 20)
        @NotNull(message = "Null, a username can not be.")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username does not follow constraints!")
        String[] usernames = {
                "Aireril", "Alisomet",
                "Anostung", "Aspenbo",
                "AuthorPlatinum", "Astroboy",
                "Bigg2free", "Bookwood",
                "Bucklippe", "Chwiredu",
                "Clubbieli", "Conveonsu",
                "FreshTary", "HiVibrant",
                "Katrien", "Leadessl",
                "Litachet", "Manpsler",
                "MoLight", "Nonpactel",
                "Onitypema", "Perachet",
                "Pherietm", "Puffro"
        };

        public static @Email(message = "Email is incorrectly formatted.") String[] emails = {
                "andre.kling@mills.com",
                "gayle.turcotte@connelly.biz",
                "adelbert.bauch@reilly.com",
                "jerel.von@dach.biz",
                "qtillman@krajcik.com",
                "joyce.wuckert@mayert.com",
                "tillman03@terry.com",
                "jeremie.altenwerth@yahoo.com",
                "pemmerich@yahoo.com",
                "gpouros@hessel.biz",
                "percy.jaskolski@hotmail.com",
                "apaucek@hotmail.com",
                "hessel.gideon@yahoo.com",
                "sydney31@nolan.org",
                "freda.tromp@hotmail.com",
                "leonie.lemke@maggio.com",
                "agustina50@hotmail.com",
                "graham.bertha@hotmail.com",
                "alessandra68@kuphal.com",
                "mraz.cory@yahoo.com"
        };
    }
}
