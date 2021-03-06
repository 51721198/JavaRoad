package com.soapsnake.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by soapsnake on 2017/5/24.
 */
public class Lambda {
    public static void main(String[] args) {

        List<String> languages = Arrays.asList("java", "scala", "haskell", "");

        ExecutorService service = Executors.newCachedThreadPool();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        };

        Future<String> future = service.submit(languages::toString);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
