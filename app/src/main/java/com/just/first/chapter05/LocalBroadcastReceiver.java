package com.just.first.chapter05;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
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
    showDialog(context);
  }

  /**
   * 本地广播中无法弹出 Dialog
   *
   * @param context 上下文
   */
  private void showDialog(final Context context) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle("本地广播")
        .setMessage("在本地广播中不能显示对话框")
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
