package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class FindMaxArrayRecursiveTaskMain {

    public static void main(String[] args) {

        int[] data = {1,2,3,4,5,6,7,8};

        FindMaxArrayRecursiveTask task = new FindMaxArrayRecursiveTask(data,3);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        int result  = pool.invoke(task);
        System.out.printf("result   %d", result);
        pool.shutdown();

    }

}
