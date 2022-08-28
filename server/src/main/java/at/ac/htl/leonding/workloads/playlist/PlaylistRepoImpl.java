package at.ac.htl.leonding.workloads.playlist;

import at.ac.htl.leonding.workloads.song.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class PlaylistRepoImpl implements PlaylistRepo {

    private final EntityManager entityManager;

    public PlaylistRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void update(Playlist playlist) {
        this.entityManager.merge(playlist);
    }

    @Override
    public void add(Playlist p) {
        this.entityManager.persist(p);
    }

    @Override
    public Playlist getUser(Long id) {
        TypedQuery<Playlist> query = this.entityManager
                .createQuery("select p from Playlist p where p.id = :id",
                        Playlist.class);
        query.setParameter("id", id);
        return query.getResultList().stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<Playlist> getAll() {
        TypedQuery<Playlist> query = this.entityManager
                .createQuery("select p from Playlist p",
                        Playlist.class);
        return query.getResultList();
    }

    public void addSong(Song song) {
        this.entityManager.persist(song);
    }
}
