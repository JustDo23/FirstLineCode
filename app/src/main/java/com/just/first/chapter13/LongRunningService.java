package com.just.first.chapter13;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.just.first.utils.LogUtils;

/**
 * 13.5.1 长期后台定时运行服务
 *
 * @author JustDo23
 * @since 2017年07月11日
 */
public class LongRunningService extends Service {

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    LogUtils.e("---> LongRunningService ---> onCreate");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    LogUtils.e("---> LongRunningService ---> onStartCommand");
    new Thread(new Runnable() {

      @Override
      public void run() {// 这里执行具体的业务逻辑操作
        LogUtils.e("---> LongRunningService ---> run");// 为保证定时准确性有必要开启子线程
      }
    }).start();
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    int anHour = 1 * 1000;// [一小时]一分钟的毫秒数
    long triggerAtTime = SystemClock.elapsedRealtime() + anHour;// 开机时间 + 指定时间
    Intent intents = new Intent(this, LongRunningService.class);// 指定自己
    PendingIntent pendingIntent = PendingIntent.getService(this, 0, intents, 0);
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);// 定时启动服务自己
    return super.onStartCommand(intent, flags, startId);
  }


  @Override
  public void onDestroy() {
    super.onDestroy();
    LogUtils.e("---> LongRunningService ---> onDestroy");
  }
}
