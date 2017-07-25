package com.just.first.chapter07;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.just.first.utils.LogUtils;

/**
 * 7.4.1 自定义内容提供器
 *
 * @author JustDo23
 * @since 2017年07月24日
 */
public class FirstContentProvider extends ContentProvider {

  public static final int TABLE_1_DIR = 0;// 自定义码
  public static final int TABLE_1_ITEM = 1;
  public static final int TABLE_2_DIR = 2;
  public static final int TABLE_2_ITEM = 3;

  public static UriMatcher uriMatcher;// 用于匹配的对象
  public static final String PACKAGE_NAME = "com.just.first";// 主包名

  static {// 静态代码块
    uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);// 用于匹配的对象
    uriMatcher.addURI(PACKAGE_NAME, "table1", TABLE_1_DIR);// 添加路径
    uriMatcher.addURI(PACKAGE_NAME, "table1/#", TABLE_1_ITEM);// 可以使用通配符
    uriMatcher.addURI(PACKAGE_NAME, "table2", TABLE_2_DIR);
    uriMatcher.addURI(PACKAGE_NAME, "table2/#", TABLE_2_ITEM);
  }

  /**
   * 初始化内容提供器。完成数据库的创建和升级操作。[只有当存在 ContentResolver 尝试访问时才会初始化]
   *
   * @return [true, 初始化成功][false,初始化失败]
   */
  @Override
  public boolean onCreate() {
    return false;
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
  @Nullable
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    switch (uriMatcher.match(uri)) {// 进行匹配并返回相应的自定义码
      case TABLE_1_DIR:
        LogUtils.e("查询 table1 表中的所有数据");
        break;
      case TABLE_1_ITEM:
        LogUtils.e("查询 table1 表中的单条数据");
        break;
      case TABLE_2_DIR:
        LogUtils.e("查询 table2 表中的所有数据");
        break;
      case TABLE_2_ITEM:
        LogUtils.e("查询 table2 表中的单条数据");
        break;
    }
    return null;
  }

  /**
   * 向内容提供器中添加数据。
   *
   * @param uri    指定哪张表
   * @param values 待添加数据键值对
   * @return 返回一个用户表示这条新纪录的 URI
   */
  @Nullable
  @Override
  public Uri insert(Uri uri, ContentValues values) {
    return null;
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
    return 0;
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
    return 0;
  }

  /**
   * 返回 MIME 类型
   *
   * @param uri 指定哪张表
   * @return 返回 MIME 类型
   */
  @Nullable
  @Override
  public String getType(Uri uri) {
    switch (uriMatcher.match(uri)) {// 进行匹配并返回相应的自定义码
      case TABLE_1_DIR:
        return "vnd.android.cursor.dir/vnd.com.just.first.table1";
      case TABLE_1_ITEM:
        return "vnd.android.cursor.item/vnd.com.just.first.table1";
      case TABLE_2_DIR:
        return "vnd.android.cursor.dir/vnd.com.just.first.table2";
      case TABLE_2_ITEM:
        return "vnd.android.cursor.item/vnd.com.just.first.table2";
    }
    return null;
  }

}
