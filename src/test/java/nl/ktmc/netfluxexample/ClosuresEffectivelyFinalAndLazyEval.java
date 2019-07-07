package nl.ktmc.netfluxexample;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ClosuresEffectivelyFinalAndLazyEval {

    @Test
    public void lambdaExample() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        numbers.stream()
                .map(number -> number * 2)
                .forEach(System.out::println);
    }

    @Test
    public void closureExample() {
        List<Integer> numbers = Arrays.asList(3,4,5,78);
        Integer multiplier = 2;
        numbers.stream()
                .map(number -> number * multiplier)
                .forEach(System.out::println);
//        multiplier = 3; compile error want de multiplier is effectively final wanneer die in de stream komt,
//        ook al is het niet als final gedefinieerd
    }

    @Test
    public void breakingFinal() {
        List<Integer> numbers = Arrays.asList(3, 5, 5, 7);
        final Integer[] multiplier = { 2 };
        Stream<Integer> numberStream = numbers.stream()
                .map(number -> number * multiplier[0]);

        multiplier[0] = 0;
        numberStream.forEach(System.out::print);
        /*
        * output is 0000, door lezy evaluation wordt de Stream pas uitgevoerd op regel 39 met .forEach
        * zo kun je voor de final multiplier array toch nog de state veranderen voordat je de stream uitvoert
        * (hoor je eigenlijk niet te doen)
        */
    }
}
