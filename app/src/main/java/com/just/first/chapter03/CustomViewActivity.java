package com.just.first.chapter03;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 3.4.2 创建自定义控件
 *
 * @author JustDo23
 * @since 2017年05月14日
 */
public class CustomViewActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_custom_view);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.hide();
    }
  }

}
