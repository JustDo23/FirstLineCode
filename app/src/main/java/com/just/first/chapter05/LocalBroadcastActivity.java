package com.just.first.chapter05;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.just.first.R;

/**
 * 5.4.1 本地广播机制
 *
 * @author JustDo23
 * @since 2017年07月17日
 */
public class LocalBroadcastActivity extends AppCompatActivity {

  private LocalBroadcastReceiver localBroadcastReceiver;
  private LocalBroadcastManager localBroadcastManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_local_broadcast);

    localBroadcastManager = LocalBroadcastManager.getInstance(this);// 获取实例
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("com.just.first.LOCAL");
    localBroadcastReceiver = new LocalBroadcastReceiver();// 实例化接收器
    localBroadcastManager.registerReceiver(localBroadcastReceiver, intentFilter);// 注册本地广播
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
  }

  public void sendLocalBroadcast(View view) {
    Intent intent = new Intent("com.just.first.LOCAL");
    localBroadcastManager.sendBroadcast(intent);// 发送本地广播
  }

}
