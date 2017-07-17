package com.just.first.chapter05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 5.3.2 发送广播
 *
 * @author JustDo23
 * @since 2017年07月17日
 */
public class SendBroadcastActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_send_broadcast);
  }

  @Override
  protected String getActionTitle() {
    return "5.3.2 发送广播";
  }

  /**
   * 发送标准广播
   *
   * @param view 按钮
   */
  public void sendStandBroadcast(View view) {
    sendBroadcast(new Intent("com.just.first.CUSTOM"));// Intent 指定 action
  }

  /**
   * 发送有序广播
   *
   * @param view 按钮
   */
  public void sendOrderBroadcast(View view) {
    sendOrderedBroadcast(new Intent("com.just.first.CUSTOM"), null);// 第二参数是一个与权限相关的字符串
  }

}
