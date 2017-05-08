package com.just.first.chapter02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.LogUtils;

/**
 * 2.4.5 活动被回收了怎么办
 *
 * @author JustDo23
 * @since 2017年05月07日
 */
public class RecoveryActivity extends BaseActivity implements View.OnClickListener {

  private EditText et_email;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recovery);
    LogUtils.e(simpleName + " ---> " + "onCreate()");
    findViewById(R.id.bt_go).setOnClickListener(this);
    et_email = (EditText) findViewById(R.id.et_email);
    if (savedInstanceState != null) {
      String tempData = savedInstanceState.getString("data_key");
      LogUtils.e(simpleName + " ---> " + "tempData = " + tempData);
    }
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
      case R.id.bt_go:
        startActivity(new Intent(this, IntentActivity.class));
        break;
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    LogUtils.e(simpleName + " ---> " + "onSaveInstanceState()");
    String tempData = "Something you just typed";
    outState.putString("data_key", tempData);
  }
}
