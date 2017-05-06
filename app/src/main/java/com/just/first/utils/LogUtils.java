package com.just.first.utils;

import android.util.Log;

import com.just.first.BuildConfig;

/**
 * 打印日志工具类
 *
 * @author JustDo23
 * @since 2017年05月06日
 */
public class LogUtils {

  public static boolean DEBUG = BuildConfig.DEBUG;
  private static String TAG = "JustDo23";

  public static void e(String tag, String content) {
    if (DEBUG) Log.e(tag, content);
  }

  public static void e(String content) {
    if (DEBUG) Log.e(TAG, content);
  }

}
