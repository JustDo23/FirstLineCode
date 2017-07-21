package com.just.first.chapter06;

import android.os.Bundle;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 6.4.1 SQLite 数据库
 *
 * @author JustDo23
 * @since 2017年07月20日
 */
public class SQLiteActivity extends BaseActivity {

  private BookDao dao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sqlite);
    dao = new BookDao(this);
  }

  public void insert(View view) {
    dao.insert();
  }

}
