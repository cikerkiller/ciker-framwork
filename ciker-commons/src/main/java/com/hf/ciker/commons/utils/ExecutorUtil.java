package com.hf.ciker.commons.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorUtil {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        exec.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
