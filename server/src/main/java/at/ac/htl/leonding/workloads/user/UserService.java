package at.ac.htl.leonding.workloads.user;

import at.ac.htl.leonding.workloads.playlist.Playlist;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<User> getAll();
    User getUser(Long id);

    void addPlaylist(User user, String name, long id);

    User addUser(String username,String name, String lastname, String email, String password);
    void removePlaylist(User usre, Playlist playlist);

    User getUserbyname(String name);
    List<Playlist> getPlaylistbyname(String username);
}
