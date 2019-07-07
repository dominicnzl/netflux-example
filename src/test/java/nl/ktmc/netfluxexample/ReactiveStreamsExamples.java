package nl.ktmc.netfluxexample;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ReactiveStreamsExamples {

    @Test
    public void simpleStreamExample() {
        Flux<String> dogs = Flux.just("Lab", "Golden", "Poedel", "Hotdog", "Terrier");
        dogs.toStream()
                .forEach(System.out::println);
    }

    @Test
    public void simpleStreamExample2() {
        Flux<String> dogs = Flux.just("Lab", "Golden", "Poedel", "Hotdog", "Terrier");
        dogs.subscribe(System.out::println);
    }

    /* hier zou de doOnEach nog niets doen totdat je subscribe() aanroept
    *  wanneer je met reactive streams werkt, zal er niets gebeuren totdat iets om een actie vraagt
    *  de output hiervan is op het eind null omdat er geen actie is gedefinieer voor wanneer
    *  deze done is */
    @Test
    public void simpleStreamExample3() {
        Flux<String> dogs = Flux.just("Lab", "Golden", "Poedel", "Hotdog", "Terrier");
        dogs.doOnEach(dog -> System.out.println(dog.get())).subscribe();
    }

    @Test
    public void simpleStreamExamplesWithSubscriber() {
        Flux<String> dogs = Flux.just("Lab", "Golden", "Poedel", "Hotdog", "Terrier");
        dogs.subscribe(System.out::println, null, () -> System.out.println("helemaal klaar mee"));
    }

    @Test
    public void simpleStreamWithSubscriberConsumers() {
        Flux<String> dogs = Flux.just("Lab", "Golden", "Poedel", "Hotdog", "Terrier");

        //subscriber lambda
        Consumer<String> printer = System.out::println;

        //error handler
        Consumer<Throwable> errorhandler = e -> System.out.println("foutje");

        //runnable upon complete
        Runnable allDone = () -> System.out.println("helemaal helemaal klaar");

        dogs.subscribe(printer, errorhandler, allDone);
    }

    /* Output is: doOnEach_onNext(3)doOnEach_onNext(6)doOnEach_onNext(6)doOnEach_onNext(6)doOnEach_onNext(7)onComplete() */
    @Test
    public void mapStreamExample() {
        Flux<String> dogs = Flux.just("Lab", "Golden", "Poedel", "Hotdog", "Terrier");
        dogs.map(String::length)
                .doOnEach(System.out::print)
                .subscribe();
    }

    /* .take(2) werkt hier op de Flux zoals .limit(2) op een normale collectie zou werken. Output:
    * Golden
    * Poedel */
    @Test
    public void filterLimitExample() {
        Flux<String> dogs = Flux.just("Lab", "Golden", "Poedel", "Hotdog", "Terrier");
        dogs.filter(s -> s.length() == 6)
                .take(2)
                .subscribe(System.out::println);
    }

    /* Je krijgt hier een Mono terug
     * Output: Golden, Poedel, Hotdog */
    @Test
    public void filterLimitJoinExample(){
        Flux<String> dogs = Flux.just("Lab", "Golden", "Poedel", "Hotdog", "Terrier");
        dogs.filter(s -> s.length() == 6)
                .take(3)
                .collect(Collectors.joining(", "))
                .subscribe(System.out::println);
    }
}
