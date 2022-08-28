package at.ac.htl.leonding.workloads.playlist;

import at.ac.htl.leonding.workloads.song.SongRepo;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PlaylistServiceImpl implements PlaylistService{

    private final PlaylistRepo playlistRepo;
    private final SongRepo songrepo;

    public PlaylistServiceImpl(PlaylistRepo playlistRepo, SongRepo songrepo) {
        this.playlistRepo = playlistRepo;
        this.songrepo = songrepo;
    }


    @Override
    public List<Playlist> getAll() {
        return this.playlistRepo.getAll();
    }

    @Override
    public Playlist getPlaylistbyid(Long id) {
        return this.playlistRepo.getUser(id);
    }

    @Override
    public Playlist createPlaylist(String name) {
        var p = Playlist.create(name);
        this.playlistRepo.add(p);
        return p;
    }

    @Override
    public void addSong(Playlist playlist, long id) {

        playlist.addSong(songrepo.getSong(id));
        this.playlistRepo.update(playlist);
    }


}
