package com.just.first.chapter13;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.main.MainActivity;

import java.util.Calendar;

/**
 * 13.5.1 定时任务 Alarm 机制
 *
 * @author JustDo23
 * @since 2017年08月22日
 */
public class AlarmActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alarm);
  }

  public void one(View view) {
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);// 利用上下文获取管理对象
    long triggerAtTime = SystemClock.elapsedRealtime() + 10 * 1000;// 10秒之后执行
    Intent intent = new Intent(this, MainActivity.class);// 意图
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 23, intent, 0);// 延迟意图
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);// 启动计时
  }

  public void two(View view) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.set(Calendar.HOUR_OF_DAY, 22);// 8
    calendar.set(Calendar.MINUTE, 44);// 30
    long triggerAtTime = calendar.getTimeInMillis();// 指定时间
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);// 利用上下文获取管理对象
    Intent intent = new Intent(this, AlarmReceiver.class);// 意图
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 23, intent, 0);// 延迟意图
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtTime, 60 * 1000, pendingIntent);// 启动计时
  }

  public void three(View view) {
    Intent intent = new Intent(this, LongRunningService.class);
    startService(intent);
  }
}
