package com.just.first.chapter07;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.just.first.chapter06.BookOpenHelper;

/**
 * 7.4.2 实现跨程序数据访问
 *
 * @author JustDo23
 * @since 2017年07月25日
 */
public class DataBaseProvider extends ContentProvider {

  public static final int BOOK_DIR = 24;
  public static final int BOOK_ITEM = 25;
  public static final int CATEGORY_DIR = 26;
  public static final int CATEGORY_ITEM = 27;
  public static final String AUTHORITY = "com.just.first.provider";
  private static UriMatcher uriMatcher;

  private BookOpenHelper bookOpenHelper;

  static {
    uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
    uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
    uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
    uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
  }

  /**
   * 初始化内容提供器。完成数据库的创建和升级操作。[只有当存在 ContentResolver 尝试访问时才会初始化]
   *
   * @return [true, 初始化成功][false,初始化失败]
   */
  @Override
  public boolean onCreate() {
    bookOpenHelper = new BookOpenHelper(getContext());
    return true;
  }

  /**
   * 从内容提供器查询数据。
   *
   * @param uri           指定查询哪张表
   * @param projection    确定查询哪些列
   * @param selection     约束查询哪些行
   * @param selectionArgs 为约束赋值
   * @param sortOrder     查询结果排序
   * @return 游标对象
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    SQLiteDatabase readableDatabase = bookOpenHelper.getReadableDatabase();
    Cursor cursor = null;
    switch (uriMatcher.match(uri)) {
      case BOOK_DIR:
        cursor = readableDatabase.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
        break;
      case BOOK_ITEM:
        String bookId = uri.getPathSegments().get(1);
        cursor = readableDatabase.query("Book", projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
        break;
      case CATEGORY_DIR:
        cursor = readableDatabase.query("Category", projection, selection, selectionArgs, null, null, sortOrder);
        break;
      case CATEGORY_ITEM:
        String categoryId = uri.getPathSegments().get(1);
        cursor = readableDatabase.query("Category", projection, "id = ?", new String[]{categoryId}, null, null, sortOrder);
        break;
    }
    return cursor;// 这个是不能进行数据库关闭的[数据库关闭会导致cursor关闭]
  }

  /**
   * 向内容提供器中添加数据。
   *
   * @param uri    指定哪张表
   * @param values 待添加数据键值对
   * @return 返回一个用户表示这条新纪录的 URI
   */
  @Override
  public Uri insert(Uri uri, ContentValues values) {
    SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();
    Uri insertUri = null;
    switch (uriMatcher.match(uri)) {
      case BOOK_DIR:
      case BOOK_ITEM:
        long insertBookId = writableDatabase.insert("Book", null, values);
        insertUri = Uri.parse("content://" + AUTHORITY + "/book/" + insertBookId);
        break;
      case CATEGORY_DIR:
      case CATEGORY_ITEM:
        long insertCategoryId = writableDatabase.insert("Category", null, values);
        insertUri = Uri.parse("content://" + AUTHORITY + "/category/" + insertCategoryId);
        break;
    }
    if (writableDatabase != null) {
      writableDatabase.close();
    }
    return insertUri;
  }

  /**
   * 更新内容提供器中已有数据。
   *
   * @param uri           指定哪张表
   * @param values        待更新数据键值对
   * @param selection     约束更新哪些行
   * @param selectionArgs 为约束赋值
   * @return 返回受影响的行数
   */
  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();
    int updateRows = 0;
    switch (uriMatcher.match(uri)) {
      case BOOK_DIR:
        updateRows = writableDatabase.update("Book", values, selection, selectionArgs);
        break;
      case BOOK_ITEM:
        String bookId = uri.getPathSegments().get(1);
        updateRows = writableDatabase.update("Book", values, "id = ?", new String[]{bookId});
        break;
      case CATEGORY_DIR:
        updateRows = writableDatabase.update("Category", values, selection, selectionArgs);
        break;
      case CATEGORY_ITEM:
        String categoryId = uri.getPathSegments().get(1);
        updateRows = writableDatabase.update("Category", values, "id = ?", new String[]{categoryId});
        break;
    }
    if (writableDatabase != null) {
      writableDatabase.close();
    }
    return updateRows;
  }

  /**
   * 从内容提供器中删除数据。
   *
   * @param uri           指定哪张表
   * @param selection     约束删除哪些行
   * @param selectionArgs 为约束赋值
   * @return 返回被删除的行数
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();
    int deleteRows = 0;
    switch (uriMatcher.match(uri)) {
      case BOOK_DIR:
        deleteRows = writableDatabase.delete("Book", selection, selectionArgs);
        break;
      case BOOK_ITEM:
        String bookId = uri.getPathSegments().get(1);
        deleteRows = writableDatabase.delete("Book", "id = ?", new String[]{bookId});
        break;
      case CATEGORY_DIR:
        deleteRows = writableDatabase.delete("Category", selection, selectionArgs);
        break;
      case CATEGORY_ITEM:
        String categoryId = uri.getPathSegments().get(1);
        deleteRows = writableDatabase.delete("Category", "id = ?", new String[]{categoryId});
        break;
    }
    if (writableDatabase != null) {
      writableDatabase.close();
    }
    return deleteRows;
  }

  /**
   * 返回 MIME 类型
   *
   * @param uri 指定哪张表
   * @return 返回 MIME 类型
   */
  @Override
  public String getType(Uri uri) {
    String type = null;
    switch (uriMatcher.match(uri)) {
      case BOOK_DIR:
        type = "vn.android.cursor.dir/vn." + AUTHORITY + ".book";
        break;
      case BOOK_ITEM:
        type = "vn.android.cursor.item/vn." + AUTHORITY + ".book";
        break;
      case CATEGORY_DIR:
        type = "vn.android.cursor.dir/vn." + AUTHORITY + ".category";
        break;
      case CATEGORY_ITEM:
        type = "vn.android.cursor.item/vn." + AUTHORITY + ".category";
        break;
    }
    return type;
  }

}
