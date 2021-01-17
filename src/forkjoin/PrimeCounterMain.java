package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class PrimeCounterMain {

    public static void main(String[] args) {

        PrimeCounter p1 = new PrimeCounter(3,100);
        ForkJoinPool fp  =  new ForkJoinPool();
          int count =  fp.invoke(p1);
        System.out.println("count : "  + count);

    }


}
