package forkjoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class FibonacciUsingForkJoinMain {


    public static void main(String[] args) {

        Long findFib = 8L;
        Map<String, Long> map = new HashMap<>();
        FibonacciUsingForkJoin task  = new FibonacciUsingForkJoin(findFib, map);
        ForkJoinPool pool = new ForkJoinPool();
        Long n = pool.invoke(task);
        System.out.format( "Fibonacci for %d : %d", findFib, n);
        pool.shutdown();

    }
}
