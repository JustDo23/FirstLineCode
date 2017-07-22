package com.just.first.chapter06;

import org.litepal.crud.DataSupport;

/**
 * 6.5.3 数据库中对象实体类
 *
 * @author JustDo23
 * @since 2017年07月22日
 */
public class Category extends DataSupport {

  private int id;
  private String categoryName;
  private String categoryCode;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryCode() {
    return categoryCode;
  }

  public void setCategoryCode(String categoryCode) {
    this.categoryCode = categoryCode;
  }
}
