package com.just.first.chapter06;

import android.os.Bundle;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

/**
 * 6.5.3 LitePal 数据库操作
 *
 * @author JustDo23
 * @since 2017年07月20日
 */
public class LitePalActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lite_pal);
  }

  public void createDatabase(View view) {
    LitePal.getDatabase();// 使用 LitePal 创建数据库
  }

  public void save(View view) {
    Book book = new Book();// 实例化实体类
    book.setName("老人与海");
    book.setAuthor("海明威");
    book.setPages("76");
    book.setPrice(32.23);
    book.setPress("机械");
    book.save();// 使用 LitePal 插入数据
    book.setPages("324");// 更新数据
    book.save();// 对插入的数据进行更新
  }

  public void updateAll(View view) {
    Book book = new Book();// 实例化实体类
    book.setPages("776");// 更新数据
    book.setToDefault("price");// 设置默认值
    book.updateAll("name = ? and pages = ?", "老人与海", "76");
  }

  public void deleteAll(View view) {
    DataSupport.deleteAll(Book.class, "pages < ?", "400");// 指定表名及约束进行删除
  }

  public void findAll(View view) {
    DataSupport.findAll(Book.class);// 查询所有
    DataSupport.findFirst(Book.class);// 查询第一条
    DataSupport.findLast(Book.class);// 查询最后一条
  }

  public void find(View view) {
    DataSupport.select("name", "author", "pages")// 指定查询的列
        .where("pages > ?", "400")// 指定查询的约束条件
        .order("pages desc")// 指定查询结果排序
        .limit(10)// 指定查询结果数量
        .offset(2)// 指定查询结果偏移-抛弃前2条
        .find(Book.class);// 指定查询的表名
  }

}
