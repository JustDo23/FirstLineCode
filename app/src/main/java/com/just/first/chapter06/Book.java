package com.just.first.chapter06;

import org.litepal.crud.DataSupport;

/**
 * 6.5.3 数据库中对象实体类
 *
 * @author JustDo23
 * @since 2017年07月22日
 */
public class Book extends DataSupport {

  private int id;
  private String name;
  private String author;
  private String pages;
  private double price;

  private String press;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPages() {
    return pages;
  }

  public void setPages(String pages) {
    this.pages = pages;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getPress() {
    return press;
  }

  public void setPress(String press) {
    this.press = press;
  }
}
