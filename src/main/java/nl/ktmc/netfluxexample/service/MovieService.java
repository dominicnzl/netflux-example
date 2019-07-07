package nl.ktmc.netfluxexample.service;

import nl.ktmc.netfluxexample.domain.Movie;
import nl.ktmc.netfluxexample.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Flux<MovieEvent> events(String  movieId);

    Mono<Movie> getMovieById(String id);

    Flux<Movie> getAllMovies();
}
