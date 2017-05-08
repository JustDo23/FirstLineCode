package com.just.first.chapter02;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.LogUtils;
import com.just.first.utils.ToastUtil;

/**
 * 2.4.4 体验活动的生命周期
 *
 * @author JustDo23
 * @since 2017年05月07日
 */
public class LifeCycleActivity extends BaseActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_life_cycle);
    LogUtils.e(simpleName + " ---> " + "onCreate()");
    findViewById(R.id.bt_dialog).setOnClickListener(this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    LogUtils.e(simpleName + " ---> " + "onStart()");
  }

  @Override
  protected void onResume() {
    super.onResume();
    LogUtils.e(simpleName + " ---> " + "onResume()");
  }

  @Override
  protected void onPause() {
    super.onPause();
    LogUtils.e(simpleName + " ---> " + "onPause()");
  }

  @Override
  protected void onStop() {
    super.onStop();
    LogUtils.e(simpleName + " ---> " + "onStop()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    LogUtils.e(simpleName + " ---> " + "onDestroy()");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    LogUtils.e(simpleName + " ---> " + "onRestart()");
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_dialog:
        showDialog();
        break;
    }
  }

  /**
   * 弹出一个 Dialog
   */
  private void showDialog() {
    new AlertDialog.Builder(this)
        .setTitle("弹窗")
        .setMessage("请查看 Activity 的生命周期")
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

          @Override
          public void onClick(DialogInterface dialog, int which) {
            ToastUtil.showShortToast(getApplicationContext(), "Negative");
          }
        })
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

          @Override
          public void onClick(DialogInterface dialog, int which) {
            ToastUtil.showShortToast(getApplicationContext(), "Positive");
          }
        })
        .setOnCancelListener(new DialogInterface.OnCancelListener() {

          @Override
          public void onCancel(DialogInterface dialog) {
            ToastUtil.showShortToast(getApplicationContext(), "onCancel()");
          }
        })
        .setOnDismissListener(new DialogInterface.OnDismissListener() {

          @Override
          public void onDismiss(DialogInterface dialog) {
            ToastUtil.showShortToast(getApplicationContext(), "onDismiss()");
          }
        })
        .create()
        .show();
  }
}
