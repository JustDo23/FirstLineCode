package com.just.first.chapter06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 6.4.1 SQLite 数据库
 *
 * @author JustDo23
 * @since 2017年07月20日
 */
public class BookOpenHelper extends SQLiteOpenHelper {

  public BookOpenHelper(Context context) {
    super(context, "BookStore" + ".db", null, 1);
  }

  /**
   * 构造方法[必须实现]
   *
   * @param context 上下文
   * @param name    数据库名称[带上后缀 .db]
   * @param factory 工厂[允许数据查询使用自定义 Cursor][一般传 null]
   * @param version 版本[整型]
   */
  public BookOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    super(context, "BookStore" + ".db", null, 1);
  }

  /**
   * 创建数据表方法[必须实现]
   *
   * @param db 数据库操作对象
   */
  @Override
  public void onCreate(SQLiteDatabase db) {
    String sql = "create table Book ( "
        + "id integer primary key autoincrement" + ", "
        + "author text" + ", "
        + "price real" + ", "
        + "pages integer" + ", "
        + "name text"
        + ")";
    db.execSQL(sql);// 执行 SQL 语句
  }

  /**
   * 数据库升级方法[必须实现]
   *
   * @param db         数据库操作对象
   * @param oldVersion 旧的版本号
   * @param newVersion 新的版本号
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }

}
