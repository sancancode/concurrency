package forkjoin;

import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class SumActionRecursiveTask extends RecursiveTask<Long> {

    List<Long> data;
    private Map<String, Long> map;
    private static final int SEQ_THRESHOLD = 5;

    SumActionRecursiveTask(List<Long> data, Map map){
        this.map = map;
        this.data = data;
    }

    @Override
    protected Long compute() {

        long sum = 0L;
        map.merge(Thread.currentThread().getName(), 1L, Long::sum);
        if(data.size()<SEQ_THRESHOLD){
            sum = computeSequnetial();
            System.out.format(Thread.currentThread().getName() + " Sum of the sub data %s, %d\n",data.toString(), sum);
            return sum;
        }

        else{

            int mid = data.size()/2;
            SumActionRecursiveTask firstSubTask = new SumActionRecursiveTask(data.subList(0,mid), map);
            SumActionRecursiveTask secondSubTask = new SumActionRecursiveTask(data.subList(mid, data.size()), map);


            firstSubTask.fork();
            secondSubTask.fork();
            sum+=firstSubTask.join();
            sum+=secondSubTask.join();

            //firstSubTask.fork();
             //Long l = secondSubTask.compute()+ firstSubTask.join();
            return sum;
        }

    }

    long computeSequnetial(){

        return data.stream().mapToLong(e->e).sum();

    }

}
