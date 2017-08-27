package com.just.first.chapter14.domain;

import com.google.gson.annotations.SerializedName;

/**
 * 天气信息实体类[单日天气]
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class Forecast {

  public String date;
  @SerializedName("tmp")
  public Temperature temperature;
  @SerializedName("cond")
  public Now.More more;


  public static class Temperature {

    public String max;
    public String min;

  }


  public static class More {

    @SerializedName("txt_d")
    public String info;

  }

}

