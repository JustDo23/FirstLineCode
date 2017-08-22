package com.just.first.chapter13;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.just.first.utils.LogUtils;
import com.just.first.utils.ToastUtil;

/**
 * 13.5.1 定时任务 Alarm 机制
 *
 * @author JustDo23
 * @since 2017年07月11日
 */
public class AlarmReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    ToastUtil.showShortToast(context, "闹铃");
    LogUtils.e("闹铃");
  }
}
