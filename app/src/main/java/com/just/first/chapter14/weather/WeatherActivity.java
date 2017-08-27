package com.just.first.chapter14.weather;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.just.first.R;
import com.just.first.chapter14.ConstantPool;
import com.just.first.chapter14.domain.Forecast;
import com.just.first.chapter14.domain.Weather;
import com.just.first.chapter14.net.HttpUtils;
import com.just.first.chapter14.utils.JsonParseUtil;
import com.just.first.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 天气 Activity
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class WeatherActivity extends AppCompatActivity {

  private ScrollView sl_weather;// 滚动布局
  private TextView tv_show_title;// 标题
  private TextView tv_show_time;// 时间
  private TextView tv_degree;// 温度
  private TextView tv_weather;// 天气信息
  private LinearLayout ll_forecast;// 未来几天布局
  private TextView tv_aqi;// 指标
  private TextView tv_pm25;// PM2.5
  private TextView tv_comfort;// 建议
  private TextView tv_car_wash;// 建议汽车
  private TextView tv_sport;// 建议运动

  private Weather weather;

  private SharedPreferences sharedPreferences;
  private ImageView iv_bing;// 背景图片

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.weather_activity);
    initStatusBar();
    findViews();
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    String weatherString = sharedPreferences.getString("weather", null);
    if (weatherString != null) {// 有缓存
      weather = JsonParseUtil.handleWeatherResponse(weatherString);
      showWeatherInfo(weather);
    } else {// 无缓存
      String weatherId = getIntent().getStringExtra("weather_id");
      sl_weather.setVisibility(View.INVISIBLE);
      requestWeather(weatherId);
    }
    initBackground();
  }

  private void initStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      View decorView = getWindow().getDecorView();
      decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
      getWindow().setStatusBarColor(Color.TRANSPARENT);
    }
  }

  private void findViews() {
    sl_weather = (ScrollView) findViewById(R.id.sl_weather);
    tv_show_title = (TextView) findViewById(R.id.tv_show_title);
    tv_show_time = (TextView) findViewById(R.id.tv_show_time);
    tv_degree = (TextView) findViewById(R.id.tv_degree);
    tv_weather = (TextView) findViewById(R.id.tv_weather);
    ll_forecast = (LinearLayout) findViewById(R.id.ll_forecast);
    tv_aqi = (TextView) findViewById(R.id.tv_aqi);
    tv_pm25 = (TextView) findViewById(R.id.tv_pm25);
    tv_comfort = (TextView) findViewById(R.id.tv_comfort);
    tv_car_wash = (TextView) findViewById(R.id.tv_car_wash);
    tv_sport = (TextView) findViewById(R.id.tv_sport);
  }

  private void showWeatherInfo(Weather weather) {
    String cityName = weather.basic.cityName;
    String updateTime = weather.basic.update.updateTime.split(" ")[1];
    String degree = weather.now.temperature + "℃";
    String weatherInfo = weather.now.more.info;
    tv_show_title.setText(cityName);
    tv_show_time.setText(updateTime);
    tv_degree.setText(degree);
    tv_weather.setText(weatherInfo);
    ll_forecast.removeAllViews();// 先移除
    for (Forecast forecast : weather.forecastList) {
      View view = LayoutInflater.from(this).inflate(R.layout.weather_forecast_item, null, false);
      TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
      TextView tv_info = (TextView) view.findViewById(R.id.tv_info);
      TextView tv_max = (TextView) view.findViewById(R.id.tv_max);
      TextView tv_min = (TextView) view.findViewById(R.id.tv_min);
      tv_date.setText(forecast.date);
      tv_info.setText(forecast.more.info);
      tv_max.setText(forecast.temperature.max);
      tv_min.setText(forecast.temperature.min);
      ll_forecast.addView(view);// 添加进布局
    }
    if (weather.aqi != null) {
      tv_aqi.setText(weather.aqi.city.aqi);
      tv_pm25.setText(weather.aqi.city.pm25);
    }
    String comfort = "舒适度：" + weather.suggestion.comfort.info;
    String carWash = "汽车指数：" + weather.suggestion.carWash.info;
    String sport = "运动建议：" + weather.suggestion.sport.info;
    tv_comfort.setText(comfort);
    tv_car_wash.setText(carWash);
    tv_sport.setText(sport);
    sl_weather.setVisibility(View.VISIBLE);
  }

  private void requestWeather(String weatherId) {
    String address = "https://free-api.heweather.com/v5/weather?key=" + ConstantPool.HE_WEATHER_KEY + "&" + "city=" + weatherId;
    HttpUtils.sendOkHttpRequest(address, new Callback() {

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        String responseText = response.body().string();
        weather = JsonParseUtil.handleWeatherResponse(responseText);
        runOnUiThread(new Runnable() {

          @Override
          public void run() {
            if (weather != null && "ok".equals(weather.status)) {
              SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
              edit.putString("weather", responseText);
              edit.apply();
              showWeatherInfo(weather);
            } else {
              ToastUtil.showShortToast(WeatherActivity.this, "获取天气信息失败");
            }
          }
        });
      }

      @Override
      public void onFailure(Call call, IOException e) {
        e.printStackTrace();
        runOnUiThread(new Runnable() {

          @Override
          public void run() {
            ToastUtil.showShortToast(WeatherActivity.this, "获取天气信息失败");
          }
        });
      }
    });
  }


  private void initBackground() {
    iv_bing = (ImageView) findViewById(R.id.iv_bing);
    String bing = sharedPreferences.getString("bing", null);
    if (bing == null) {
      requestBingPic();
    } else {
      loadBingPic(bing);
    }
  }

  private void loadBingPic(String bing) {
    Glide.with(this).load(bing).into(iv_bing);
  }

  private void requestBingPic() {
    HttpUtils.sendOkHttpRequest("http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1", new Callback() {

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        String responseText = response.body().string();
        if (!TextUtils.isEmpty(responseText)) {
          try {
            JSONObject responseObject = new JSONObject(responseText);
            JSONArray imageArray = responseObject.optJSONArray("images");
            JSONObject imageObject = imageArray.optJSONObject(0);
            String imageUrl = imageObject.optString("url");
            String bingPic = "http://www.bing.com" + imageUrl;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("bing", bingPic);
            edit.apply();
            runOnUiThread(new Runnable() {

              @Override
              public void run() {
                loadBingPic(bingPic);
              }
            });
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }

      @Override
      public void onFailure(Call call, IOException e) {
        runOnUiThread(new Runnable() {

          @Override
          public void run() {
            ToastUtil.showShortToast(WeatherActivity.this, "必应异常");
          }
        });
      }
    });
  }

}
