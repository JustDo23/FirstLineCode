package com.just.first.application;

import android.app.Application;

import org.litepal.LitePal;

/**
 * 全局唯一
 *
 * @author JustDo23
 * @since 2017年07月22日
 */
public class FirstLineApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    LitePal.initialize(this);// 初始化 LitePal 数据库
  }

}
