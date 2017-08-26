package com.just.first.chapter14.db;

import org.litepal.crud.DataSupport;

/**
 * [实体类][城市]
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class City extends DataSupport {

  private int id;// 必要
  private String cityName;// 城市名
  private int cityCode;// 城市码
  private int provinceId;// 省份 ID

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public int getCityCode() {
    return cityCode;
  }

  public void setCityCode(int cityCode) {
    this.cityCode = cityCode;
  }

  public int getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(int provinceId) {
    this.provinceId = provinceId;
  }

}
