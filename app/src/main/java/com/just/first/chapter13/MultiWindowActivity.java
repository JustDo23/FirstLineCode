package com.just.first.chapter13;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.just.first.R;

/**
 * 13.6.2 多窗口生命周期
 *
 * @author JustDo23
 * @since 2017年08月22日
 */
public class MultiWindowActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multi_window);
    LogUtils.e("JustDo23", "--> onCreate()");
  }

  @Override
  protected void onStart() {
    super.onStart();
    LogUtils.e("JustDo23", "--> onStart()");
  }

  @Override
  protected void onResume() {
    super.onResume();
    LogUtils.e("JustDo23", "--> onResume()");
  }

  @Override
  protected void onPause() {
    super.onPause();
    LogUtils.e("JustDo23", "--> onPause()");
  }

  @Override
  protected void onStop() {
    super.onStop();
    LogUtils.e("JustDo23", "--> onStop()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    LogUtils.e("JustDo23", "--> onDestroy()");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    LogUtils.e("JustDo23", "--> onRestart()");
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
  }
}
