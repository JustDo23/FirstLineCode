package com.just.first.chapter05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.just.first.utils.ToastUtil;

/**
 * 5.3.2 自定义广播
 *
 * @author JustDo23
 * @since 2017年07月17日
 */
public class CustomReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    ToastUtil.showShortToast(context, "Custom Receiver.");
    abortBroadcast();// 截断广播
  }

}
