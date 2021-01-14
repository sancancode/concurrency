package forkjoin;

import java.util.concurrent.RecursiveTask;

public class FibnacciFJBetter extends RecursiveTask<Long> {


    long number;

    public FibnacciFJBetter(long number) {
        this.number = number;
    }

    @Override
    protected Long compute() {
        return    fib(number);
    }

    long fib(long number) {
        if (number <= 1)
            return number;

        if (number > 10 && getSurplusQueuedTaskCount() < 2) {

            FibonacciUsingForkJoin f1 = new FibonacciUsingForkJoin(number - 1);
            FibonacciUsingForkJoin f2 = new FibonacciUsingForkJoin(number - 2);

            f1.fork();
            return f2.compute() + f1.join();
        } else {
            return fib(number - 1)+fib(number - 2);
        }
    }
}
