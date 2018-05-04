package com.ld.multiutil;

import com.ld.multithread.lock.Person;

import java.util.concurrent.Semaphore;

public class SemophoneExam {
    private static final int MAX_AVAILABLE = 100;
    private final Semaphore semaphore = new Semaphore(MAX_AVAILABLE, true);

    public Object getItem() throws InterruptedException {
        semaphore.acquire();
        return getNextAvailableItem();
    }

    public void putItem(Object x) {
        if (markAsUnused(x))
            semaphore.release();
    }

    // Not a particularly efficient data structure; just for demo

    private Person[] items = new Person[]{};
    private boolean[] used = new boolean[MAX_AVAILABLE];

    private synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; // not reached
    }

    private synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SemophoneExam exam = new SemophoneExam();
        Person person = Person.builder().age(12).name("dsadsa").build();
        exam.putItem(person);
        try {
            exam.getItem();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
