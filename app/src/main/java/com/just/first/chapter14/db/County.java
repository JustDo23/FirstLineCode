package com.just.first.chapter14.db;

import org.litepal.crud.DataSupport;

/**
 * [实体类][县]
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class County extends DataSupport {

  private int id;// 必要
  private String countyName;// 县名
  private String weatherId;// 天气码
  private int cityId;// 城市 ID

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCountyName() {
    return countyName;
  }

  public void setCountyName(String countyName) {
    this.countyName = countyName;
  }

  public String getWeatherId() {
    return weatherId;
  }

  public void setWeatherId(String weatherId) {
    this.weatherId = weatherId;
  }

  public int getCityId() {
    return cityId;
  }

  public void setCityId(int cityId) {
    this.cityId = cityId;
  }

}
