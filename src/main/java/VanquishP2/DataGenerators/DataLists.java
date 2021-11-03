package VanquishP2.DataGenerators;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Service
@Validated
public class DataLists {
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
            "m]hQB7>d", "wRr^){3Q" ,
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
