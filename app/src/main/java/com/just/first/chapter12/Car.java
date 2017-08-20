package com.just.first.chapter12;

/**
 * 12.5.1 卡片布局-汽车
 *
 * @author JustDo23
 * @since 2017年08月19日
 */
public class Car {

  private String name;
  private int imageId;

  public Car(String name, int imageId) {
    this.name = name;
    this.imageId = imageId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }
}
