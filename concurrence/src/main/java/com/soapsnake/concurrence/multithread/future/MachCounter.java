package com.soapsnake.concurrence.multithread.future;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MachCounter implements Callable<Integer> {   //callable接口必须有返回对象

    private File directory;
    private String keyword;

    public MachCounter(File directory, String keyword) {
        // Auto-generated method stub
        this.keyword = keyword;
        this.directory = directory;
    }

    @Override
    public Integer call() throws Exception {
        // Auto-generated method stub

        int count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    MachCounter counter = new MachCounter(file, keyword);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread thread = new Thread(task);
                    thread.start();
                } else {
                    if (search(file)) {
                        count++;
                    }
                }

                for (Future<Integer> future : results) {
                    try {
                        count += future.get();
                    } catch (ExecutionException e) {
                        //: handle exception
                    }
                }
            }
        } catch (InterruptedException e) {
            //: handle exception
        }
        return count;
    }

    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file, "utf-8")) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        found = true;
                    }
                    return found;
                }
            } catch (Exception e) {
                //: handle exception
            }
        } catch (Exception e) {
            //: handle exception
        }
        return false;
    }


}
