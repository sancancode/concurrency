package forkjoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class FibnacciFJBetter extends RecursiveTask<Long> {


    long number;
    Map<String, Long> map ;

    public FibnacciFJBetter(long number, Map map) {
        this.map = map;
        this.number = number;
    }

    @Override
    protected Long compute() {
        return    fib(number);
    }

    long fib(long number) {

        //System.out.println(map);
        //System.out.println(getSurplusQueuedTaskCount());
        System.out.format("    %s :   % d  \n ", map, getSurplusQueuedTaskCount());
        map.merge(Thread.currentThread().getName(), 1L, Long::sum);
        if (number <= 1)
            return number;

        if (number > 10 && getSurplusQueuedTaskCount() < 2) {

            FibnacciFJBetter f1 = new FibnacciFJBetter(number - 1, map);
            FibnacciFJBetter f2 = new FibnacciFJBetter(number - 2, map);

            f1.fork();
            return f2.compute() + f1.join();
        } else {
            return fib(number - 1)+fib(number - 2);
        }
    }
}
