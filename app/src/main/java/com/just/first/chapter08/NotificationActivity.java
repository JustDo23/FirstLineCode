package com.just.first.chapter08;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.just.first.R;

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
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_notification_simple:// 基本使用
        simple();
        break;
    }
  }

  /**
   * 通知的基本使用
   */
  private void simple() {
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    Notification notification = new NotificationCompat.Builder(this)
        .setContentTitle("This is content title")
        .setContentText("This is content text")
        .setWhen(System.currentTimeMillis())
        .setSmallIcon(R.mipmap.ic_launcher)
        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mario))
        .build();
    notificationManager.notify(23, notification);
  }
}
