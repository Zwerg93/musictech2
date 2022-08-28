package at.ac.htl.leonding.workloads.user;

import at.ac.htl.leonding.workloads.playlist.Playlist;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "\"User\"")
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String username, name, lastname, email, password;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Playlist> playlistList = new ArrayList<>();

    public static User create(String username, String name, String lastname, String email, String password) {
        var newUser = new User();
        newUser.setName(name);
        newUser.setLastname(lastname);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        return newUser;

    }


    public Playlist addPlaylist(String name, long id) {
        Playlist playlistnew = new Playlist();
        playlistnew.setName(name);
        //playlistnew.setId(id);
        playlistnew.setUser(this);
        System.out.println("hilfe");
        // System.out.println(this.name + "test");
        this.playlistList.add(playlistnew);
        return playlistnew;
        //playlist.setUser(this);
        //playlist.setUser(this);
    }


    public User(String username, String name, String lastname, String email, String password) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Playlist> getPlaylistList() {
        return playlistList;
    }

    public void setPlaylistList(List<Playlist> playlistList) {
        this.playlistList = playlistList;
    }
}
