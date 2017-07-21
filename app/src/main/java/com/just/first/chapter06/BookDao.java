package com.just.first.chapter06;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
  }

public void insert() {
  SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();// 获取数据操作对象
  ContentValues contentValues = new ContentValues();// 键值对集合
  contentValues.put("name", "FirstLine");// 列名-数据
  contentValues.put("author", "Guo");
  contentValues.put("pages", "570");
  contentValues.put("price", 79.0);
  writableDatabase.insert("Book", null, contentValues);// 指定表名添加
}
}
