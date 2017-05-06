package com.just.first.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.just.first.utils.LogUtils;

/**
 * 抽象出Activity基类
 *
 * @author JustDo23
 * @since 2017年05月06日
 */
public class BaseActivity extends AppCompatActivity {

  private String simpleName;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    simpleName = getClass().getSimpleName();
    LogUtils.e(simpleName);
  }

}
