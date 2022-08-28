package at.ac.htl.leonding.workloads.user;

import at.ac.htl.leonding.workloads.playlist.Playlist;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAll() {
        return this.userRepo.getAll();
    }
    @Override
    public List<Playlist> getPlaylistbyname(String username) {
        return this.userRepo.getAllPlaylistsbyUsername(username);
    }

    @Override
    public User getUser(Long id) {
        return this.userRepo.getUser(id);
    }

    @Override
    public User getUserbyname(String name) {
        return this.userRepo.getUserbyName(name);
    }



    @Override
    public void addPlaylist(User user, String name, long id) {

        user.addPlaylist(name,user.getId());

        this.userRepo.update(user);
    }

    @Override
    public User addUser(String username, String name, String lastname, String email, String password) {
        var p = User.create(username, name, lastname, email, password);
        this.userRepo.add(p);
        return p;
    }

    @Override
    public void removePlaylist(User usre, Playlist playlist) {

    }


}
