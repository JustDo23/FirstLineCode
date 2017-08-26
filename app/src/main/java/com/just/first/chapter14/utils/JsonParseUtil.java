package com.just.first.chapter14.utils;

import android.text.TextUtils;

import com.just.first.chapter14.db.City;
import com.just.first.chapter14.db.County;
import com.just.first.chapter14.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 数据解析工具
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class JsonParseUtil {

  /**
   * 解析省份并逐条存入数据库
   *
   * @param response 网络请求数据
   * @return true 解析保存成功
   */
  public static boolean handleProvinceResponse(String response) {
    if (!TextUtils.isEmpty(response)) {
      try {
        JSONArray allProvinces = new JSONArray(response);
        for (int i = 0; i < allProvinces.length(); i++) {
          JSONObject provinceObject = allProvinces.optJSONObject(i);
          Province province = new Province();
          province.setProvinceName(provinceObject.optString("name"));
          province.setProvinceCode(provinceObject.optInt("id"));
          province.save();// 存入数据库中
        }
        return true;
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  /**
   * 解析城市并逐条存入数据库
   *
   * @param response   网络请求数据
   * @param provinceId 省份 ID
   * @return true 解析保存成功
   */
  public static boolean handleCityResponse(String response, int provinceId) {
    if (!TextUtils.isEmpty(response)) {
      try {
        JSONArray allCities = new JSONArray(response);
        for (int i = 0; i < allCities.length(); i++) {
          JSONObject cityObject = allCities.optJSONObject(i);
          City city = new City();
          city.setCityName(cityObject.optString("name"));
          city.setCityCode(cityObject.optInt("id"));
          city.setProvinceId(provinceId);
          city.save();// 存入数据库中
        }
        return true;
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  /**
   * 解析区县并逐条存入数据库
   *
   * @param response 网络请求数据
   * @param cityId   城市 ID
   * @return true 解析保存成功
   */
  public static boolean handleCountyResponse(String response, int cityId) {
    if (!TextUtils.isEmpty(response)) {
      try {
        JSONArray allCounties = new JSONArray(response);
        for (int i = 0; i < allCounties.length(); i++) {
          JSONObject countyObject = allCounties.optJSONObject(i);
          County county = new County();
          county.setCountyName(countyObject.optString("name"));
          county.setCityId(cityId);
          county.setWeatherId(countyObject.optString("weather_id"));
          county.save();// 存入数据库中
        }
        return true;
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

}
