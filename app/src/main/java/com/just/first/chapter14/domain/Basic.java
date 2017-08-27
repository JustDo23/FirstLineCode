package com.just.first.chapter14.domain;

import com.google.gson.annotations.SerializedName;

/**
 * 天气信息实体类[基础信息]
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class Basic {

  @SerializedName("city")
  public String cityName;
  @SerializedName("id")
  public String weatherId;

  public Update update;

  public static class Update {

    @SerializedName("loc")
    public String updateTime;

  }

}
