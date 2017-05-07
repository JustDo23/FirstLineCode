package com.just.first.chapter02;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

/**
 * 2.2.5 在活动使用 Menu
 *
 * @author JustDo23
 * @since 2017年05月07日
 */
public class MenuActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);
  }

  /**
   * 创建自定义菜单
   *
   * @param menu 系统指定的菜单对象
   * @return true, 表示允许创建的菜单显示出来
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);// 从资源中加载菜单
    return true;// 允许菜单显示
  }

  /**
   * 菜单 Item 的点击事件
   *
   * @param item 菜单 Item
   * @return
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_add:
        ToastUtil.showShortToast(this, "Add");
        break;
      case R.id.menu_remove:
        ToastUtil.showShortToast(this, "Remove");
        break;
    }
    return true;
  }
}
