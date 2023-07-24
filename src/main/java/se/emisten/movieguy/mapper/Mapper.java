package se.emisten.movieguy.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import se.emisten.movieguy.dto.MovieDto;
import se.emisten.movieguy.entity.Movie;

import java.util.List;

@ApplicationScoped
public class Mapper {

    public List<MovieDto> map(List<Movie> all) {return all.stream().map(MovieDto::new).toList();}

    public Movie map (MovieDto movie) {
        var m = new Movie();
        m.setId(movie.getId());
        m.setMovieName(movie.getMovieName());
        m.setGenre(movie.getGenre());
        m.setRunTime(movie.getRunTime());
        return m;
    }

    public MovieDto map (Movie movie) {
        return new MovieDto(movie);
    }
}
