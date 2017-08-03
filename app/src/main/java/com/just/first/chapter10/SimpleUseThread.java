package com.just.first.chapter10;

/**
 * 10.2.1 线程的基本使用
 *
 * @author JustDo23
 * @since 2017年08月02日
 */
public class SimpleUseThread {

  class FirstThread extends Thread {

    @Override
    public void run() {
      // 耗时操作
    }
  }

  class FirstRunnable implements Runnable {

    @Override
    public void run() {
      // 耗时操作
    }
  }

  void startThread() {
    new FirstThread().start();
    new Thread(new FirstRunnable()).start();
    new Thread(new Runnable() {

      @Override
      public void run() {
        // 耗时操作
      }
    }).start();
  }

}
