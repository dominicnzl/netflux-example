package nl.ktmc.netfluxexample;

import org.junit.Test;

public class FunctionalProgrammingExamples {

    @Test
    public void functionWith4Things() throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("in thread t1");
            }
        });
        t1.start();
        System.out.println("in main test");
    }
}
