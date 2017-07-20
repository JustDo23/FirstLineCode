package com.just.first.chapter06;

import android.content.Context;

/**
 * 6.4.1 SQLite 数据库
 *
 * @author JustDo23
 * @since 2017年07月20日
 */
public class BookDao {

  private Context context;
  private BookOpenHelper bookOpenHelper;

  public BookDao(Context context) {
    this.context = context;
    bookOpenHelper = new BookOpenHelper(context);
    bookOpenHelper.getReadableDatabase();
    bookOpenHelper.getWritableDatabase();
  }
}
