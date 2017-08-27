package com.just.first.chapter14.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 天气信息实体类[建议信息]
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class Weather {

  public String status;
  public Basic basic;
  public AQI aqi;
  public Now now;
  public Suggestion suggestion;
  @SerializedName("daily_forecast")
  public List<Forecast> forecastList;

}
