package forkjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SumActionRecursiveTaskMain {


    public static void main(String[] args) {

        List<Long> list = LongStream.range(0,11).boxed().collect(Collectors.toList());
        Map<String, Long> map = new HashMap<>();
        SumActionRecursiveTask task = new SumActionRecursiveTask(list,map);
        //ForkJoinPool pool = new ForkJoinPool(3);

        //ForkJoinPool pool =  ForkJoinPool.commonPool();
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());


        Long l = pool.invoke(task);
        System.out.format("\n result : %d", l);
        System.out.println("\n"+ map + " size  " + map.size());
        pool.shutdown();
    }
}
