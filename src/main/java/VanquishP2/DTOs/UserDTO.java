package VanquishP2.DTOs;

import VanquishP2.Application.Beans.Models.Track;
import VanquishP2.Application.Beans.Models.User;

import java.util.List;

public class UserDTO {
    List<Track> favoriteTracks;
    User user;

    public UserDTO(User user) {
        this.user = user;
    }
}
