package com.just.first.application;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * 全局唯一
 *
 * @author JustDo23
 * @since 2017年07月22日
 */
public class FirstLineApplication extends Application {

  private static Context context;

  @Override
  public void onCreate() {
    super.onCreate();
    context = getApplicationContext();
    LitePal.initialize(this);// 初始化 LitePal 数据库
  }

  public static Context getContext() {
    return context;
  }
}
