package forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class FindMaxArrayRecursiveTask  extends RecursiveTask<Integer> {

    int[] data;
    int threshold;

    public FindMaxArrayRecursiveTask(int[] data, int threshold){

        this.data = data;
        this.threshold = threshold;
    }


    @Override
    protected Integer compute() {

        if(data.length<threshold){

            return processSequential(data);
        }

        else {
              int mid = data.length/2;
              FindMaxArrayRecursiveTask fj1 = new FindMaxArrayRecursiveTask(Arrays.copyOfRange(data,0,mid), threshold);
              FindMaxArrayRecursiveTask fj2 = new FindMaxArrayRecursiveTask(Arrays.copyOfRange(data,mid,data.length), threshold);
              fj1.fork();
              return Math.max(fj2.compute() , fj1.join());
        }
    }

    int processSequential(int[] data){

        int maxSoFar = Integer.MIN_VALUE;

        for(int i : data){
            maxSoFar = Math.max(maxSoFar, i);
        }

        return maxSoFar;

    }
}
