package forkjoin;

import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class PrimeCounter extends RecursiveTask<Integer> {


    int high;
    int low;

    PrimeCounter(int low, int high) {

        this.high = high;
        this.low = low;
    }

    @Override
    protected Integer compute() {
        if (high - low < 100) {
            int count = processSequential(low, high);
            return count;
        }
         else {
            int mid = (high + low) / 2;
            PrimeCounter p1 = new PrimeCounter(low, mid);
            PrimeCounter p2 = new PrimeCounter(mid, high);
            p1.fork();
            return p2.compute() + p1.join();
        }
    }

    int processSequential(int low, int high) {
        return (int) IntStream.range(low, high).filter(i -> isPrime(i)).count();
    }

    boolean isPrime(int p) {

        if(p==2)
            return true;

        for (int i = 2; i <= Math.sqrt(p); i++) {
            if (p % i == 0) return false;
        }
        return true;

    }

}