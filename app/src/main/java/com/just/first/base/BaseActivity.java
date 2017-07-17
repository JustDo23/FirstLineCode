package com.just.first.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.just.first.chapter02.ActivityCollector;
import com.just.first.utils.LogUtils;

/**
 * 抽象出Activity基类
 *
 * @author JustDo23
 * @since 2017年05月06日
 */
public class BaseActivity extends AppCompatActivity {

  protected String simpleName;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    simpleName = getClass().getSimpleName();
    LogUtils.e(simpleName);
    ActivityCollector.addActivity(this);
    if (!TextUtils.isEmpty(getActionTitle())) {
      getSupportActionBar().setTitle(getActionTitle());
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ActivityCollector.removeActivity(this);
  }

  /**
   * 获取 ActionBar 需要显示的标题
   *
   * @return 标题
   */
  protected String getActionTitle() {
    return null;
  }

}
