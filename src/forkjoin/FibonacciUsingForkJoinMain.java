package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class FibonacciUsingForkJoinMain {


    public static void main(String[] args) {

        Long findFib = 8L;

        FibonacciUsingForkJoin task  = new FibonacciUsingForkJoin(findFib);

        ForkJoinPool pool = new ForkJoinPool();

        Long n = pool.invoke(task);

        System.out.format( "Fibonacci for %d : %d", findFib, n);
        pool.shutdown();

    }
}
