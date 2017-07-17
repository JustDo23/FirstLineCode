package com.just.first.chapter05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.just.first.utils.ToastUtil;

/**
 * 5.4.1 本地广播机制
 *
 * @author JustDo23
 * @since 2017年07月17日
 */
public class LocalBroadcastReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    ToastUtil.showShortToast(context, "Local Broadcast Receiver.");
  }

}
