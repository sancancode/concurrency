package forkjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SumActionMain {

    public static void main(String[] args) {


        List<Long> list = LongStream.range(0, 100).boxed().collect(Collectors.toList());
        final Map<String, Long> map = new HashMap<>();
        SumAction task = new SumAction(list, map);
        /*ForkJoinPool pool = new ForkJoinPool(2);
        pool.invoke(task);
        pool.shutdown();
        System.out.println(map.size());
        */System.out.println("----");

        System.out.println("##########################");
        System.out.println("/n/n");

        ForkJoinPool poolCoreSize = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        poolCoreSize.invoke(task);
        poolCoreSize.shutdown();
        System.out.println(map.size());
    }
}
