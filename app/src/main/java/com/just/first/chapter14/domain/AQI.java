package com.just.first.chapter14.domain;

/**
 * 天气信息实体类
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class AQI {

  public AQICity city;

  public static class AQICity {

    public String aqi;
    public String pm25;
  }

}
