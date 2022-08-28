package at.ac.htl.leonding.workloads.playlist;

import at.ac.htl.leonding.workloads.user.User;
import at.ac.htl.leonding.workloads.user.UserRepo;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface PlaylistService {

    List<Playlist> getAll();
    Playlist getPlaylistbyid(Long id);
    Playlist createPlaylist(String name);
    void addSong(Playlist playlist, long id);
    //void update(Playlist playlist);
}
