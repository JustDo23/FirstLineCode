package com.just.first.chapter14.db;

import org.litepal.crud.DataSupport;

/**
 * [实体类][省份]
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class Province extends DataSupport {

  private int id;// 必要
  private String provinceName;// 省名
  private int provinceCode;// 省码

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public int getProvinceCode() {
    return provinceCode;
  }

  public void setProvinceCode(int provinceCode) {
    this.provinceCode = provinceCode;
  }

}
