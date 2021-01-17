package forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class FindMaxArray extends RecursiveAction {

    int[] data;
    int threshold;
    int result;
    public FindMaxArray(int[] data, int threshold){

        this.data = data;
        this.threshold = threshold;

    }

    @Override
    protected void compute() {

        if(data.length<threshold){

            result = processSequential(data);

        }

        else {

            int mid = data.length/2;
            FindMaxArray fj1 = new FindMaxArray(Arrays.copyOfRange(data,0 , mid), threshold);
            FindMaxArray fj2= new FindMaxArray(Arrays.copyOfRange(data,mid , data.length), threshold);

            fj1.fork();
            fj2.compute();;
            fj1.join();

            result = Math.max(fj1.result, fj2.result);

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
