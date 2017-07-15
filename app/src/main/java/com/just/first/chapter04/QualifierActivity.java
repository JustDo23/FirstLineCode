package com.just.first.chapter04;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 4.4.1 动态加载 Fragment 技巧 使用限定符
 *
 * @author JustDo23
 * @since 2017年07月15日
 */
public class QualifierActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_qualifier);
  }

}
