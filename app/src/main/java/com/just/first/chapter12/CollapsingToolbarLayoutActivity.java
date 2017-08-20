package com.just.first.chapter12;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.just.first.R;

/**
 * 12.7.1 可折叠式标题栏
 *
 * @author JustDo23
 * @since 2017年08月20日
 */
public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private ImageView iv_hide;
  private TextView tv_content;
  private FloatingActionButton fab_content;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_collapsing_toolbar_layout);
    // 可折叠标题栏
    CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
    collapsingToolbarLayout.setTitle("Fruit Detail");// 设置标题
    // 图片
    ImageView iv_hide = (ImageView) findViewById(R.id.iv_hide);
    Glide.with(this).load(R.mipmap.ic_orange).into(iv_hide);
    // Toolbar
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
    // 内容详情
    tv_content = (TextView) findViewById(R.id.tv_content);
    tv_content.setText("orange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\norange\n");
    fab_content = (FloatingActionButton) findViewById(R.id.fab_content);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
    }
    return true;
  }
}
