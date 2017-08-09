package com.just.first.chapter12;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

/**
 * 12.1.2 Toolbar 使用
 *
 * @author JustDo23
 * @since 2017年08月09日
 */
public class ToolbarActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_toolbar);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);// 找控件
    toolbar.setTitle("JJFly");// 修改标题
    setSupportActionBar(toolbar);// 设置
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_toolbar, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_backup:
        ToastUtil.showShortToast(this, "Click Backup");
        break;
      case R.id.menu_delete:
        ToastUtil.showShortToast(this, "Click Delete");
        break;
      case R.id.menu_settings:
        ToastUtil.showShortToast(this, "Click Settings");
        break;
    }
    return true;
  }

}
