package com.just.first.chapter12;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

/**
 * 12.3.1 滑动菜单
 *
 * @author JustDo23
 * @since 2017年08月12日
 */
public class DrawerLayoutActivity extends BaseActivity {

  private DrawerLayout drawerLayout;
  private NavigationView navigationView;
  private FloatingActionButton floatingActionButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drawer_layout);
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);// 找控件
    toolbar.setTitle("DrawerLayout");// 修改标题
    setSupportActionBar(toolbar);// 设置
    drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    ActionBar actionBar = getSupportActionBar();// 获取相应的 toolbar
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);// 允许导航按钮显示
      actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);// 设置导航按钮的图标
    }
    navigationView = (NavigationView) findViewById(R.id.navigationView);// 找控件
    navigationView.setCheckedItem(R.id.menu_nav_call);// 设置选中
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {// 设置菜单选中监听

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        return true;
      }
    });
    floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
    floatingActionButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Data deleted", Snackbar.LENGTH_LONG)
            .setAction("Undo", new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                ToastUtil.showShortToast(v.getContext(), "Data restored");
              }
            })
            .show();
      }
    });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:// 处理点击事件
        drawerLayout.openDrawer(GravityCompat.START);// 打开菜单
        break;
    }
    return true;
  }

}
