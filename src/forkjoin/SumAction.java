package forkjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveAction;

public class SumAction extends RecursiveAction {

    private static final int SEQUENTIAL_THRESHOLD = 5;

    private final List<Long> data;

    private final Map<String, Long> map;



    public SumAction(List<Long> data, Map<String, Long> map){

        this.data = data;
        this.map = map;
    }


    @Override
    protected void compute() {

        map.merge(Thread.currentThread().getName(), 1L, Long::sum);

        if(data.size()<=SEQUENTIAL_THRESHOLD){
            long sum =   computeSequential();
            System.out.format("sum of %s : %d\n ", data.toString(), sum);
            System.out.println(map);
        }
        else {
            int mid = data.size()/2;
            SumAction firstSubTask = new SumAction(data.subList(0,mid), map);
            SumAction secondSubTask = new SumAction(data.subList(mid,data.size()), map);
            firstSubTask.fork();
            secondSubTask.compute();
            firstSubTask.join();
        }
    }

    private long computeSequential(){

        return data.stream().mapToLong(e->e).sum();

    }


}
