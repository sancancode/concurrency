package ompletablefuture;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class CompletableFutureExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        System.out.println("----");
        // Running asynchronous computation using runAsync() - No return result
        CompletableFuture<Void> future =  CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(1000);
                System.out.println("printing runasync " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(future.get() + " printing void get");

    //supply async - if there is a return value

        CompletableFuture<String> future1 =  CompletableFuture.supplyAsync(()->{

            return  "Hello";
        });

        System.out.println(future1.get());



        //supply async - if there is a return value with Executor
        ExecutorService exec = Executors.newFixedThreadPool(4);
        future1 = CompletableFuture.supplyAsync(()->{
            return "Hello with executor";
            }, exec);
        System.out.println(future1.get());
        exec.shutdown();;



        //Transforming a completablefuture - thenApply
        CompletableFuture<String> whatsYourNameFuture =  CompletableFuture.supplyAsync(()->{

            return  "Sanjeev";
        });

        CompletableFuture<String> sayHelloFuture =  CompletableFuture.supplyAsync(()->{

            return  "Hello";
        });

      CompletableFuture<String>  future2 =  whatsYourNameFuture.thenApply(name->"Hello " + name);

        System.out.println(future2.get());


        System.out.println(CompletableFuture.supplyAsync(()->{return "sanjeev";})
                .thenApply(name->"Hello " + name).get());

        System.out.println(CompletableFuture.supplyAsync(()->{return "sanjeev";})
                .thenApply(name->"Hello " + name + " thenApply: " + Thread.currentThread().getName()).get());

        System.out.println(CompletableFuture.supplyAsync(()->{return "sanjeev";})
                .thenApplyAsync(name->"Hello " + name + " thenApplyAsync: " + Thread.currentThread().getName()).get());

        ExecutorService exe = Executors.newFixedThreadPool(2);

        System.out.println(CompletableFuture.supplyAsync(()->{return "sanjeev";})
                .thenApplyAsync(name->"Hello " + name + " thenApplyAsyncwithexe: " + Thread.currentThread().getName(), exe).get());
        exe.shutdown();


        ExecutorService exe1 = Executors.newFixedThreadPool(2);

        System.out.println(CompletableFuture.supplyAsync(()->{return "sanjeev";})
                .thenApplyAsync(name->"Hello " + name
                        + " thenApplyAsyncwithexe: " + Thread.currentThread().getName(), exe1).get());
        exe1.shutdown();


        System.out.println(CompletableFuture.supplyAsync(()->{return "sanjeev";})
                .thenApply(name->"Hello " + name)
                .thenApply(msg->"hi " + msg).get());


//thenAccept ,  thenrun

        CompletableFuture.supplyAsync(()->{return "sanjeev"; }).thenAccept((name)-> {
            try {
                Thread.sleep(400);
                System.out.println(name + " hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

CompletableFuture.supplyAsync(()->{return "hello"; }).thenRun(()->{
    System.out.println("computation finished");
});

//Combining two CompletableFutures together - then combine

        CompletableFuture<Integer> cf = CompletableFuture
                .supplyAsync(() -> {
                    int x = ThreadLocalRandom.current().nextInt(1, 5);
                    System.out.println("Main stage: " + x);
                    return x;
                });

        CompletableFuture<Double> finalCf = cf.thenCombine(
                getOther(),
                (x, ints) -> Arrays.stream(ints).mapToDouble(i -> Math.pow(i, x))
                        .sum());


        System.out.println(finalCf.get());
    }


    private static CompletableFuture<int[]> getOther() {
        CompletableFuture<int[]> otherCf = CompletableFuture
                .supplyAsync(() -> {
                    int[] ints = IntStream.range(1, 5)
                            .map(i -> ThreadLocalRandom.current().nextInt(5, 10))
                            .toArray();
                    System.out.println("Other stage: " + Arrays.toString(ints));
                    return ints;
                });
        return otherCf;
    }

    static class User{

        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        int id;

    }




}
