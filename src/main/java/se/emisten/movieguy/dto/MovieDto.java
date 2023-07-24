package se.emisten.movieguy.dto;



import se.emisten.movieguy.entity.Movie;

import java.math.BigDecimal;


public class MovieDto {

    private Long id;
    String movieName;

    String genre;

    Integer runTime;

    public MovieDto() {}


    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.movieName = movie.getMovieName();
        this.genre = movie.getGenre();
        this.runTime = movie.getRunTime();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

}
