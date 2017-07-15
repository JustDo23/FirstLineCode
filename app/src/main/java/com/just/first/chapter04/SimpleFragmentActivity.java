package com.just.first.chapter04;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.LogUtils;

/**
 * 4.2.1 Fragment 的简单用法
 *
 * @author JustDo23
 * @since 2017年05月16日
 */
public class SimpleFragmentActivity extends BaseActivity implements View.OnClickListener {

  private Button bt_left;// 静态 Fragment 中的按钮
  private BottomFragment bottomFragment;// 底部的碎片

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    LogUtils.e(simpleName + " --> onCreate() 前");
    super.onCreate(savedInstanceState);
    LogUtils.e(simpleName + " --> onCreate() 后");
    setContentView(R.layout.activity_simple_fragment);

    bt_left = (Button) findViewById(R.id.bt_left);
    bt_left.setOnClickListener(this);

    bottomFragment = new BottomFragment();

    getFragment();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_left:
        replaceFragment(bottomFragment);
        break;
    }
  }

  /**
   * 切换 Fragment 操作
   *
   * @param fragment 新的碎片
   */
  private void replaceFragment(Fragment fragment) {
    FragmentManager supportFragmentManager = this.getSupportFragmentManager();// 获取碎步管理类
    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();// 获取碎片管理事务
    fragmentTransaction.replace(R.id.fl_bottom, fragment);// 进行替换
    fragmentTransaction.addToBackStack(null);// 字符串参数用于描述返回栈状态
    fragmentTransaction.commit();// 事务提交
  }

  /**
   * 在 Activity 中获取静态 Fragment 的实例
   */
  private void getFragment() {
    LeftFragment leftFragment = (LeftFragment) getSupportFragmentManager().findFragmentById(R.id.frag_left);
    LogUtils.e(leftFragment.toString());
    LogUtils.e(this.toString());
  }


  @Override
  protected void onStart() {
    super.onStart();
    LogUtils.e(simpleName + " --> onStart()");
  }

  @Override
  protected void onResume() {
    super.onResume();
    LogUtils.e(simpleName + " --> onResume()");
  }

  @Override
  protected void onPause() {
    super.onPause();
    LogUtils.e(simpleName + " --> onPause()");
  }

  @Override
  protected void onStop() {
    super.onStop();
    LogUtils.e(simpleName + " --> onStop()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    LogUtils.e(simpleName + " --> onDestroy()");
  }
}
