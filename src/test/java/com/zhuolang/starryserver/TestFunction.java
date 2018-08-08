package com.zhuolang.starryserver;

import org.junit.Test;

public class TestFunction {

    @Test
    public void addUserByPhonePsw() {

        System.out.println("开始。。。。");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("线程。。。。" + i);
                }
            }
        }).start();
        for (int i = 6; i < 10; i++) {
            System.out.println("结束。。。。" + i);
        }

    }
}
