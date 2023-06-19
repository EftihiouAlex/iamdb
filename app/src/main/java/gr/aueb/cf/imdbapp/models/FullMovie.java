package gr.aueb.cf.imdbapp.models;

import java.util.List;

public class FullMovie {
    private Movie movie;
    private List<Director> director;
    private List<Actor> actors;
    private List<Genre> genres;
    private List<Review> reviews;

    public FullMovie() {
    }

    public FullMovie(Movie movie, List<Director> director, List<Actor> actors, List<Genre> genres, List<Review> reviews) {
        this.movie = movie;
        this.director = director;
        this.actors = actors;
        this.genres = genres;
        this.reviews = reviews;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Director> getDirector() {
        return director;
    }

    public void setDirector(List<Director> director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenre(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
