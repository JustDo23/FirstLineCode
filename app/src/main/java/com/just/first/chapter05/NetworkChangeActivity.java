package com.just.first.chapter05;

import android.content.IntentFilter;
import android.os.Bundle;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 5.2.1 动态广播监听网络变化
 *
 * @author JustDo23
 * @since 2017年07月17日
 */
public class NetworkChangeActivity extends BaseActivity {

  private IntentFilter intentFilter;
  private NetworkChangeReceive networkChangeReceive;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_network_change);
    register();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unRegister();
  }

  /**
   * 注册广播
   */
  private void register() {
    intentFilter = new IntentFilter();
    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    networkChangeReceive = new NetworkChangeReceive();
    this.registerReceiver(networkChangeReceive, intentFilter);
  }

  /**
   * 解注册广播
   */
  private void unRegister() {
    unregisterReceiver(networkChangeReceive);
  }

}
