package com.just.first.chapter14.domain;

import com.google.gson.annotations.SerializedName;

/**
 * 天气信息实体类[当前信息]
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class Now {

  @SerializedName("tmp")
  public String temperature;// 温度
  @SerializedName("cond")
  public More more;

  public static class More {

    @SerializedName("txt")
    public String info;

  }

}

