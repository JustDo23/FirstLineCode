package com.just.first.chapter05;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
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
    showDialog(context);
    abortBroadcast();// 截断广播
  }

  /**
   * 静态广播中无法弹出 Dialog
   *
   * @param context 上下文
   */
  private void showDialog(final Context context) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle("静态广播")
        .setMessage("在静态广播中不能显示对话框")
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
