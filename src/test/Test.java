package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Test {

    public static void main(String[] args) {

        System.out.println("sdsd");

        String test = "hello world how ae you doing good i guess  aa bba  aasa sdsds wewsc " +
                "" +
                "2323 2323232 23323232 23232" +
                "3232323232" +
                "32323232" +
                "" +
                "23232323" +
                "" +
                "2323232" +
                "" +
                "323232" +
                "23232 2323" +
                "2323" +
                "3232323 23232 32323 232232 323232323 ddfdfdf";
            reverseLine(test);


    }


    static void reverseLine(String s){

        Stack

        String[] words = s.split(" ");
        StringBuilder sb  = new StringBuilder();

        Arrays.stream(words).forEach(e->
            sb.append( new StringBuilder(e).reverse() + " "));

        System.out.println(sb.toString());
        StringBuilder sb1  = new StringBuilder();

        Map<String, Integer> map = new HashMap();

        Arrays.stream(words).parallel().forEachOrdered(e->{

            map.merge(Thread.currentThread().getName(), 1 , (v1,v2)->v1+1);


                   sb1.append( new StringBuilder(e).reverse() + " ");
        });

        System.out.println(map);
        System.out.println(sb1.toString());


    }




}
