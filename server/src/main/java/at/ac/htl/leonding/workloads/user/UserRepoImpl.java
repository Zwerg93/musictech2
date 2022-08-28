package at.ac.htl.leonding.workloads.user;


import at.ac.htl.leonding.workloads.playlist.Playlist;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
// implements PanacheRepository<User>

@ApplicationScoped
public class UserRepoImpl implements UserRepo {
    private final EntityManager entityManager;

    public UserRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = this.entityManager
                .createQuery("select p from User p",
                        User.class);
        return query.getResultList();
    }
    @Override
    public List<Playlist> getAllPlaylistsbyUsername(String username) {
        TypedQuery<Playlist> query = this.entityManager
                .createQuery("select p from Playlist p where p.user.username = :id",
                        Playlist.class);
        query.setParameter("id", username);
        return query.getResultList();
    }

    @Override
    public User getUser(Long id) {
        TypedQuery<User> query = this.entityManager
                .createQuery("select p from User p where p.id = :id",
                        User.class);
        query.setParameter("id", id);
        return query.getResultList().stream()
                .findFirst().orElse(null);
    }

    @Override
    public User getUserbyName(String name) {
        System.out.println(name + " :name");
        TypedQuery<User> query = this.entityManager
                .createQuery("select p from User p where p.username = :id",
                        User.class);
        query.setParameter("id", name);
        return query.getResultList().stream()
                .findFirst().orElse(null);
    }



    @Override
    public void update(User user) {
        //System.out.println(user.playlistList.get(0).getName() + "fuck h");
        this.entityManager.merge(user);
    }

    @Override
    public void add(User p) {
        this.entityManager.persist(p);
    }


    public String getPassword(String username) {
        TypedQuery<String> query = this.entityManager
                .createQuery("select p.password from User p where p.username = :username ",
                        String.class)
                .setParameter("username", username);

        return query.getResultList().stream().findFirst().orElse(null);
    }



/*



    public void update(User user) {
        this.entityManager.merge(user);
    }


    public List<User> getAll() {
        TypedQuery<User> query = this.entityManager
                .createQuery("select p from User p",
                        User.class);
        return query.getResultList();
    }


    public void addPlaylist(User user, Playlist playlist) {
        user.addPlaylist(playlist);
        System.out.println("hab dich fuck");
        //update(user);
    }

 */

}
