package forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddSumExecutor {

    public void invoke() {

        List<Integer> list = new ArrayList<>();
        final ExecutorService executor = Executors.newFixedThreadPool(10);
        list = Stream.iterate(0, n->n+1).limit(25).collect(Collectors.toList());
        class AddTask implements Callable<Integer> {

            int startIndex;
            int endIndex;
            int THRESHOLD = 5;
            int sum = 0;
            public AddTask(int startIndex, int endIndex) {

                this.startIndex = startIndex;
                this.endIndex = endIndex;
            }


            @Override
            public Integer call() throws Exception {


                if(endIndex-startIndex <= THRESHOLD){

                  sum+= (int)Stream.iterate(startIndex, n->n+1).limit(endIndex).mapToInt(i->i).sum();
                }

                else {

                    int mid = (startIndex+endIndex)/2;
                    AddTask task1 = new AddTask(startIndex, mid);
                    AddTask task2 = new AddTask( mid+1, endIndex);

                    Future<Integer> future1=executor.submit(task1);
                    Future<Integer> future2=executor.submit(task2);

                    sum+=future1.get();
                    sum+=future2.get();
                }
                System.out.println(sum);
                return sum;

            }
        }

    }

    public static void main(String[] args) {

        AddSumExecutor exec1 = new AddSumExecutor();
        exec1.invoke();
    }
}
