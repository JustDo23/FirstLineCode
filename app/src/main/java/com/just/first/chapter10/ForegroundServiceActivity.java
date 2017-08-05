package com.just.first.chapter10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 10.5.1 前台服务
 *
 * @author JustDo23
 * @since 2017年08月05日
 */
public class ForegroundServiceActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_foreground_service);
  }

  public void startService(View view) {
    Intent startIntent = new Intent(this, ForegroundService.class);// 意图指定服务
    startService(startIntent);// 启动服务
  }

  public void stopService(View view) {
    Intent stopIntent = new Intent(this, ForegroundService.class);// 意图指定服务
    stopService(stopIntent);// 停止服务
  }

}
