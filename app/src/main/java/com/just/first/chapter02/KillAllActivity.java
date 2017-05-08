package com.just.first.chapter02;

import android.os.Bundle;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.LogUtils;

/**
 * 2.6.2 随时随地退出程序
 *
 * @author JustDo23
 * @since 2017年05月08日
 */
public class KillAllActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_kill_all);
    LogUtils.e(simpleName + " ---> getTaskId() = " + getTaskId());
    findViewById(R.id.bt_kill_all).setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        ActivityCollector.finishAll();// 关闭所有并退出
      }
    });
  }
}
