package com.just.first.chapter10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.LogUtils;

/**
 * 10.5.2 IntentService
 *
 * @author JustDo23
 * @since 2017年08月05日
 */
public class IntentServiceActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_foreground_service);
  }

  public void startService(View view) {
    LogUtils.e("IntentServiceActivity --> Thread id is " + Thread.currentThread().getId());
    Intent startIntent = new Intent(this, FirstIntentService.class);// 意图指定服务
    startService(startIntent);// 启动服务
  }

}
