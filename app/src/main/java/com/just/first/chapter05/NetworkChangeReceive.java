package com.just.first.chapter05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.just.first.utils.ToastUtil;

/**
 * 5.2.1 动态广播监听网络变化
 *
 * @author JustDo23
 * @since 2017年07月17日
 */
public class NetworkChangeReceive extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    ToastUtil.showShortToast(context, "Network changes.");
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    if (networkInfo != null && networkInfo.isAvailable()) {
      ToastUtil.showShortToast(context, "Network is available.");
    } else {
      ToastUtil.showShortToast(context, "Network is unavailable.");
    }
  }

}
