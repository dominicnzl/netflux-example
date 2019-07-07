package nl.ktmc.netfluxexample.bootstrap;

import nl.ktmc.netfluxexample.domain.Movie;
import nl.ktmc.netfluxexample.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public BootstrapCLR(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /** todo dng wat is UUID, flatMap */
    @Override
    public void run(String... args) {

        movieRepository.deleteAll()
                .thenMany(
                Flux.just("film abc", "tv-show def", "music clip ghi", "documentary jkl")
                        .map(Movie::new)
                        .flatMap(movieRepository::save))
                        .subscribe(null, null, () -> {
                            movieRepository.findAll().subscribe(System.out::println);
                        });
    }
}
