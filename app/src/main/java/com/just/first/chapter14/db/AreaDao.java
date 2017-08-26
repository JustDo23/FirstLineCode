package com.just.first.chapter14.db;

import org.litepal.LitePal;
import org.litepal.LitePalDB;

/**
 * 数据库操作
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class AreaDao {

  /**
   * 创建数据库
   */
  public void createDB() {
    LitePalDB litePalDB = new LitePalDB("Area", 1);
    litePalDB.addClassName(Province.class.getName());
    litePalDB.addClassName(City.class.getName());
    litePalDB.addClassName(County.class.getName());
    LitePal.use(litePalDB);
  }

}
