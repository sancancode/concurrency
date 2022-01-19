package forkjoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class FibnacciFJBetterMain {


    public static void main(String[] args) {

        Map<String, Long> map = new HashMap<>();
        Long findFib = 99L;
        FibnacciFJBetter task  = new FibnacciFJBetter(findFib,map);
        ForkJoinPool pool = new ForkJoinPool(4);
        Long n = pool.invoke(task);
        System.out.format( "Fibonacci for %d : %d", findFib, n);
        pool.shutdown();
    }
}
