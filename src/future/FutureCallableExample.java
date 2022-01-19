package future;

import java.util.List;
import java.util.concurrent.*;

public class FutureCallableExample {


    public static void main(String[] args) throws ExecutionException,InterruptedException {

        ExecutorService exec = Executors.newFixedThreadPool(4);


        Callable<String> callable =
                ()->{

            Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());

            return "Hello";
        };

        Future<String> future  = exec.submit(callable);

        System.out.println(" Result is " + future.get() );


        exec.shutdown();

        System.out.println("-----");

        ExecutorService exec1 = Executors.newFixedThreadPool(4);

        future = exec1.submit(()-> {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
            return "world";
        });

        while(!future.isDone()){

            System.out.println("Future is not done " + Thread.currentThread().getName() );
            Thread.sleep(200);

        }

        System.out.println(future.get());
        exec1.shutdown();


        //multiple futures

        Callable<String> callable1 = ()->{
            System.out.println("Callable 1 " + Thread.currentThread().getName());
            Thread.sleep(500);
            return  "Hello";
        };

        Callable<String> callable2 = ()->{
            System.out.println("Callable 2 " + Thread.currentThread().getName());
            Thread.sleep(200);
            return  "world";
        };

        Callable<String> callable3 = ()->{

            System.out.println("Callable 3 " + Thread.currentThread().getName());
            Thread.sleep(5000);
            return  "!!!";
        };

        ExecutorService exec3 = Executors.newFixedThreadPool(4);

        List<Future<String>> futures =  exec3.invokeAll(List.of(callable1,callable2,callable3));

        for(Future<String> future1: futures) {
            // The result is printed only after all the futures are complete. (i.e. after 5 seconds)
            System.out.println(future1.get());
        }
        exec3.shutdown();

        //invokeany

        ExecutorService exec4 = Executors.newFixedThreadPool(4);
        String s  =  exec4.invokeAny(List.of(callable1,callable2,callable3));
        System.out.println("Result of invokeany will be the fastest task  " + s);
        exec4.shutdown();




    }
}
