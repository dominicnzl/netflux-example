package nl.ktmc.netfluxexample.bootstrap;

import nl.ktmc.netfluxexample.domain.Movie;
import nl.ktmc.netfluxexample.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

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
                Flux.just("abc", "def", "ghi", "jkl")
                        .map(title -> new Movie(title, UUID.randomUUID().toString()))
                        .flatMap(movieRepository::save))
                        .subscribe(null, null, () -> {
                            movieRepository.findAll().subscribe(System.out::println);
                        });
    }
}
