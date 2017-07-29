package com.just.first.chapter08;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.just.first.R;
import com.just.first.main.MainActivity;

import java.io.File;

/**
 * 8.2.2 通知的使用方法
 *
 * @author JustDo23
 * @since 2017年05月12日
 */
public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);
    findViewById(R.id.bt_notification_simple).setOnClickListener(this);
    findViewById(R.id.bt_notification_advanced).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_notification_simple:// 基本使用
        showNotification();
        break;
      case R.id.bt_notification_advanced:// 高阶使用
        showNotificationAdvanced();
        break;
    }
  }

  /**
   * 通知的基本使用
   */
  private void showNotification() {
    Intent intent = new Intent(this, MainActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);// 获取通知管理对象
    Notification notification = new NotificationCompat.Builder(this)// Builder 设计模式
        .setContentTitle("This is title")// 设置标题
        .setContentText("This is content")// 设置文本
        .setWhen(System.currentTimeMillis())// 指定通知被创建的时间以毫秒为单位
        .setSmallIcon(R.mipmap.ic_launcher)// 设置小图标只能使用纯 alpha 图层的图片
        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mario))// 设置大图标
        .setContentIntent(pendingIntent)// 设置延迟意图
        .setAutoCancel(true)// 设置自动取消
        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/23_Game.ogg")))// 设置声音
        .setVibrate(new long[]{0, 1000, 1000, 1000, 1000, 1000})// 设置振动[静止时长][振动时长]单位毫秒
        .setLights(Color.YELLOW, 1000, 1000)// 设置 LED 灯 [颜色][亮灯时长][灭灯时长]单位毫秒
        .setDefaults(NotificationCompat.DEFAULT_ALL)// 设置默认配置[删除]
        .build();// 创建
    notificationManager.notify(23, notification);// 通知管理器去显示该条通知[通知的ID][通知对象]要保证 ID 的不同
  }

  /**
   * 通知的高阶使用
   */
  private void showNotificationAdvanced() {
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);// 获取通知管理对象
    Notification notification = new NotificationCompat.Builder(this)// Builder 设计模式
        .setContentTitle("Title")// 设置标题
        .setContentText("Content")// 设置文本
        .setWhen(System.currentTimeMillis())// 指定通知被创建的时间以毫秒为单位
        .setSmallIcon(R.mipmap.ic_launcher)// 设置小图标只能使用纯 alpha 图层的图片
        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mario))// 设置大图标
        .setAutoCancel(true)// 设置自动取消
        .setDefaults(NotificationCompat.DEFAULT_ALL)// 设置默认配置
        .setStyle(new NotificationCompat.BigTextStyle().bigText("If we can only encounter each other rather than stay with each other,then I wish we had never encountered."))// 显示特别长的文本
        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mountain)))
        .setPriority(NotificationCompat.PRIORITY_MAX)// 设置通知的重要程度
        .build();// 创建
    notificationManager.notify(24, notification);// 通知管理器去显示该条通知[通知的ID][通知对象]要保证 ID 的不同
  }

}
