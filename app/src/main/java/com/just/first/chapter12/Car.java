package com.just.first.chapter12;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 12.5.1 卡片布局-汽车
 *
 * @author JustDo23
 * @since 2017年08月19日
 */
public class Car implements Parcelable {

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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.name);// 写出 name
    dest.writeInt(this.imageId);// 写出 imageId
  }

  protected Car(Parcel in) {
    this.name = in.readString();// 读 name
    this.imageId = in.readInt();// 读 imageId
  }

  public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
    @Override
    public Car createFromParcel(Parcel source) {
      return new Car(source);
    }

    @Override
    public Car[] newArray(int size) {
      return new Car[size];
    }
  };
}
