package at.ac.htl.leonding.workloads.playlist;

import at.ac.htl.leonding.workloads.song.Song;
import at.ac.htl.leonding.workloads.user.User;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonbTransient
    private User user;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    public List<Song> songList = new ArrayList<>();

    public static Playlist create(String name) {
        var newPlaylist = new Playlist();
        newPlaylist.setName(name);
        return newPlaylist;
    }

    public Song addSong(Song song) {
        song.setPlaylist(this);
        this.songList.add(song);
        return song;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Playlist() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }


}
