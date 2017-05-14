package com.just.first.chapter03;

/**
 * 3.6.1 RecyclerView 的简单用法
 *
 * @author JustDo23
 * @since 2017年05月14日
 */
public class Fruit {

  private int imageResource;
  private String fruitName;

  public Fruit(int imageResource, String fruitName) {
    this.imageResource = imageResource;
    this.fruitName = fruitName;
  }

  public int getImageResource() {
    return imageResource;
  }

  public String getFruitName() {
    return fruitName;
  }
}
