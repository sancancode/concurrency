package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class FibnacciFJBetterMain {


    public static void main(String[] args) {

        Long findFib = 8L;
        FibnacciFJBetter task  = new FibnacciFJBetter(findFib);
        ForkJoinPool pool = new ForkJoinPool();
        Long n = pool.invoke(task);
        System.out.format( "Fibonacci for %d : %d", findFib, n);
        pool.shutdown();
    }
}
