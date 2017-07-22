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

  public void update(View view) {
    dao.update();
  }

  public void delete(View view) {
    dao.delete();
  }

  public void query(View view) {
    dao.query();
  }


  public void insertSQL(View view) {
    dao.insertSQL();
  }

  public void updateSQL(View view) {
    dao.updateSQL();
  }

  public void deleteSQL(View view) {
    dao.deleteSQL();
  }

  public void querySQL(View view) {
    dao.querySQL();
  }

}
