package at.ac.htl.leonding.workloads.playlist;

import java.util.List;

public interface PlaylistRepo {
    void update(Playlist playlist);

    void add(Playlist p);

    Playlist getUser(Long id);

    List<Playlist> getAll();
}
