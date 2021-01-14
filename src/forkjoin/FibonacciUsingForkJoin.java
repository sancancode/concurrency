package forkjoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class FibonacciUsingForkJoin  extends RecursiveTask<Long> {


    long number;

    Map<String, Long> map ;

    public FibonacciUsingForkJoin(long number, Map<String, Long> map){

        this.number = number;
        this.map = map;
    }


    @Override
    protected Long compute() {

        if(number<=1)
            return number;


        else{

            map.merge(Thread.currentThread().getName(), 1L, Long::sum);
            System.out.println(map);
            FibonacciUsingForkJoin f1 = new FibonacciUsingForkJoin(number-1,map);
            FibonacciUsingForkJoin f2 = new FibonacciUsingForkJoin(number-2, map);

            f1.fork();
            return f2.compute() + f1.join();

        }
    }
}
