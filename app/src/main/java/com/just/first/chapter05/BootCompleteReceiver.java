package com.just.first.chapter05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.just.first.utils.ToastUtil;

/**
 * 5.2.2 静态广播监听手机启动
 *
 * @author JustDo23
 * @since 2017年07月17日
 */
public class BootCompleteReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    ToastUtil.showShortToast(context, "Boot Complete Receiver.");
  }

}
