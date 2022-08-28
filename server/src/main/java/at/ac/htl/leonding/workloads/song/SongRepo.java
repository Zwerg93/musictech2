package at.ac.htl.leonding.workloads.song;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@ApplicationScoped
public class SongRepo implements PanacheRepository<Song> {


    private final EntityManager entityManager;

    public SongRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Song> getAll() {
        TypedQuery<Song> query = this.entityManager
                .createQuery("select p from Song p",
                        Song.class);
        return query.getResultList();
    }

    public Song getSong(Long id) {
        TypedQuery<Song> query = this.entityManager
                .createQuery("select p from Song p where p.id = :id",
                        Song.class);
        query.setParameter("id", id);
        return query.getResultList().stream()
                .findFirst().orElse(null);
    }


    public void addSong(Song song) {
        this.entityManager.persist(song);
    }
}

