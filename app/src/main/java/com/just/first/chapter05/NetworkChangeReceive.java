package com.just.first.chapter05;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
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
    showDialog(context);
  }


  /**
   * 动态广播中可以弹出 Dialog
   *
   * @param context 上下文
   */
  private void showDialog(final Context context) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle("动态广播")
        .setMessage("在动态广播中可以显示对话框")
        .setCancelable(false)
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

          @Override
          public void onClick(DialogInterface dialog, int which) {
            ToastUtil.showShortToast(context, "Click Ok.");
          }
        });
    builder.show();
  }

}
