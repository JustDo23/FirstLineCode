package com.just.first.chapter10;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.just.first.R;
import com.just.first.main.MainActivity;

/**
 * 10.5.1 前台服务
 *
 * @author JustDo23
 * @since 2017年08月05日
 */
public class ForegroundService extends Service {

  @Override
  public void onCreate() {
    super.onCreate();// 构建一个通知
    Intent intent = new Intent(this, MainActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 23, intent, 0);
    Notification notification = new NotificationCompat.Builder(this)
        .setContentTitle("ForegroundService")
        .setContentText("This the foreground service")
        .setWhen(System.currentTimeMillis())
        .setSmallIcon(R.mipmap.ic_launcher)
        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mountain))
        .setContentIntent(pendingIntent)
        .build();
    startForeground(22, notification);// 前台运行服务
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public boolean onUnbind(Intent intent) {
    return super.onUnbind(intent);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

}
