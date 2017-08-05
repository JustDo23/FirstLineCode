package com.just.first.chapter10;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.just.first.utils.LogUtils;

/**
 * 10.3.1 服务入门
 *
 * @author JustDo23
 * @since 2017年08月03日
 */
public class FirstService extends Service {

  private DownloadBinder downloadBinder = new DownloadBinder();

  /**
   * 在服务被创建时调用
   */
  @Override
  public void onCreate() {
    super.onCreate();
    LogUtils.e("FirstService --> onCreate()");
  }

  /**
   * @param intent 意图
   * @return 绑定对象
   */
  @Override
  public IBinder onBind(Intent intent) {
    LogUtils.e("FirstService --> onBind()");
    return downloadBinder;
  }

  /**
   * 在每次服务启动的时候调用
   *
   * @param intent  意图
   * @param flags   标识
   * @param startId 启动码
   * @return 整型
   */
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    LogUtils.e("FirstService --> onStartCommand()");
    return super.onStartCommand(intent, flags, startId);
  }

  /**
   * 在服务销毁时调用
   */
  @Override
  public void onDestroy() {
    super.onDestroy();
    LogUtils.e("FirstService --> onDestroy()");
  }

  /**
   * 使用 Binder 机制
   *
   * @since 2017年08月04日
   */
  class DownloadBinder extends Binder {

    public void startDownload() {
      LogUtils.e("DownloadBinder --> startDownload()");
    }

    public int getProgress() {
      LogUtils.e("DownloadBinder --> getProgress()");
      return 0;
    }

  }

}
