package at.ac.htl.leonding.workloads.user;

import at.ac.htl.leonding.workloads.playlist.Playlist;

import java.util.List;

public interface UserRepo {
    List<User> getAll();

    User getUser(Long id);

    void update(User user);

    void add(User p);

    User getUserbyName(String name);

    List<Playlist> getAllPlaylistsbyUsername(String username);
}
