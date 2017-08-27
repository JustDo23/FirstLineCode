package com.just.first.chapter14.domain;

import com.google.gson.annotations.SerializedName;

/**
 * 天气信息实体类[建议信息]
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class Suggestion {


  @SerializedName("comf")
  public Comfort comfort;
  @SerializedName("cw")
  public CarWash carWash;
  public Sport sport;


  public static class Comfort {

    @SerializedName("txt")
    public String info;

  }


  public static class CarWash {

    @SerializedName("txt")
    public String info;

  }


  public static class Sport {

    @SerializedName("txt")
    public String info;

  }


}

