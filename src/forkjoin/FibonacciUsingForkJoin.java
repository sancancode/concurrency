package forkjoin;

import java.util.concurrent.RecursiveTask;

public class FibonacciUsingForkJoin  extends RecursiveTask<Long> {


    long number;

    public FibonacciUsingForkJoin(long number){

        this.number = number;

    }


    @Override
    protected Long compute() {

        if(number<=1)
            return number;


        else{

            FibonacciUsingForkJoin f1 = new FibonacciUsingForkJoin(number-1);
            FibonacciUsingForkJoin f2 = new FibonacciUsingForkJoin(number-2);

            f1.fork();
            return f2.compute() + f1.join();

        }
    }
}
