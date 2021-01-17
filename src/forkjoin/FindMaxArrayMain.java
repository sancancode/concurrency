package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class FindMaxArrayMain {


    public static void main(String[] args) {

        int [] data = {1,2,6,7,44,33,11,333,232,212,4,3212,343435,23232};

        FindMaxArray fj = new FindMaxArray(data, 3);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        pool.invoke(fj);
        System.out.println("Result " + fj.result);
        pool.shutdown();
    }


}
