package se.emisten.movieguy.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.emisten.movieguy.entity.Movie;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class MovieRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Movie> findAll() {

        var query = entityManager.createQuery("select m from Movie m");
        return (List<Movie>) query.getResultList();
    }
    public Optional<Movie> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));

    }

    public void insertMovie(Movie movie) {
        entityManager.persist(movie);
    }
    public void deleteMovie(Long id) {
        var movie = findOne(id);
        movie.ifPresent(m -> entityManager.remove(m));
    }

    public Movie update(Long id, Movie movie){
        var entity = entityManager.find(Movie.class, id);
        entity.setMovieName(movie.getMovieName());
        entity.setGenre(movie.getGenre());
        entity.setRunTime(movie.getRunTime());
        entityManager.persist(entity);
        return entity;
    }

    public List<Movie> findAllByName(String name) {
        var query = entityManager.createQuery("Select m from Movie m where m.movieName like :name");
        query.setParameter("name", name);
        return (List<Movie>) query.getResultList();
    }


}
