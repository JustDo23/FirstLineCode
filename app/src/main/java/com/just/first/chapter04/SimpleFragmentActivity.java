package com.just.first.chapter04;

import android.os.Bundle;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 4.2.1 Fragment 的简单用法
 *
 * @author JustDo23
 * @since 2017年05月16日
 */
public class SimpleFragmentActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple_fragment);
  }

}
