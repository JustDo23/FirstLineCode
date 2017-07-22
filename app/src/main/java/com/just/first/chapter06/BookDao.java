package com.just.first.chapter06;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.just.first.utils.LogUtils;

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

  /**
   * 数据库插入操作
   */
  public void insert() {
    SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();// 获取数据操作对象
    ContentValues contentValues = new ContentValues();// 键值对集合
    contentValues.put("name", "FirstLine");// 列名-数据
    contentValues.put("author", "Guo");
    contentValues.put("pages", "570");
    contentValues.put("price", 79.0);
    writableDatabase.insert("Book", null, contentValues);// 指定表名添加
  }

  /**
   * 数据库更新操作
   */
  public void update() {
    SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();// 获取数据操作对象
    ContentValues contentValues = new ContentValues();// 键值对集合
    contentValues.put("price", 99.9);
    writableDatabase.update("Book", contentValues, "name = ?", new String[]{"FirstLine"});
  }

  /**
   * 数据库删除操作
   */
  public void delete() {
    SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();// 获取数据操作对象
    writableDatabase.delete("Book", "name = ?", new String[]{"FirstLine"});
  }

  /**
   * 数据库查询操作
   */
  public void query() {
    SQLiteDatabase readableDatabase = bookOpenHelper.getReadableDatabase();// 获取数据操作对象
    Cursor cursor = readableDatabase.query("Book", null, null, null, null, null, null);// 查询获得游标
    if (cursor.moveToFirst()) {// 是否可以移动位置
      do {
        String author = cursor.getString(cursor.getColumnIndex("author"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String pages = cursor.getString(cursor.getColumnIndex("pages"));
        String price = cursor.getString(cursor.getColumnIndex("price"));
        LogUtils.e("Book: " + author + " -- " + name + " -- " + pages + " -- " + price);
      } while (cursor.moveToNext());// 是否可以继续往下移动
    }
  }


  /**
   * 数据库插入操作[SQL]
   */
  public void insertSQL() {
    SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();// 获取数据操作对象
    writableDatabase.execSQL("insert into Book (name, author, pages, price) values (?, ?, ?, ?)", new String[]{"SecondLine", "Lin", "123", "66.6"});
  }

  /**
   * 数据库更新操作[SQL]
   */
  public void updateSQL() {
    SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();// 获取数据操作对象
    writableDatabase.execSQL("update Book set pages = ? where author = ?", new String[]{"333", "Lin"});
  }

  /**
   * 数据库删除操作[SQL]
   */
  public void deleteSQL() {
    SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();// 获取数据操作对象
    writableDatabase.execSQL("delete from Book where pages > ?", new String[]{"10"});
  }

  /**
   * 数据库查询操作[SQL]
   */
  public void querySQL() {
    SQLiteDatabase readableDatabase = bookOpenHelper.getReadableDatabase();// 获取数据操作对象
    Cursor cursor = readableDatabase.rawQuery("select * from Book", null);// 查询获得游标
    if (cursor.moveToFirst()) {// 是否可以移动位置
      do {
        String author = cursor.getString(cursor.getColumnIndex("author"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String pages = cursor.getString(cursor.getColumnIndex("pages"));
        String price = cursor.getString(cursor.getColumnIndex("price"));
        LogUtils.e("Book: " + author + " -- " + name + " -- " + pages + " -- " + price);
      } while (cursor.moveToNext());// 是否可以继续往下移动
    }
  }

}
