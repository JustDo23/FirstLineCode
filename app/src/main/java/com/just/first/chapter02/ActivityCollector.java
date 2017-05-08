package com.just.first.chapter02;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 2.6.2 随时随地退出程序
 *
 * @author JustDo23
 * @since 2017年05月08日
 */
public class ActivityCollector {

  public static List<Activity> activityList = new ArrayList<>();

  public static void addActivity(Activity activity) {
    activityList.add(activity);
  }

  public static void removeActivity(Activity activity) {
    activityList.remove(activity);
  }

  public static void finishAll() {
    for (Activity activity : activityList) {
      if (!activity.isFinishing()) {
        activity.finish();
      }
    }
    activityList.clear();
    android.os.Process.killProcess(android.os.Process.myPid());// 删掉当前进程
  }

}
