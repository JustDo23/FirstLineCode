package com.just.first.chapter14.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.just.first.chapter14.ConstantPool;
import com.just.first.chapter14.domain.Weather;
import com.just.first.chapter14.net.HttpUtils;
import com.just.first.chapter14.utils.JsonParseUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 后天服务自动更新
 *
 * @author JustDo23
 * @since 2017年08月28日
 */
public class AutoUpdateService extends Service {

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    updateWeather();
    updateBingPic();
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    int anHour = 8 * 60 * 60 * 1000;
    long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
    Intent intents = new Intent(this, AutoUpdateService.class);
    PendingIntent pendingIntent = PendingIntent.getService(this, 0, intents, 0);
    alarmManager.cancel(pendingIntent);
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
    return super.onStartCommand(intent, flags, startId);
  }

  private void updateWeather() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    String weatherString = sharedPreferences.getString("weather", null);
    if (weatherString != null) {
      Weather weather = JsonParseUtil.handleWeatherResponse(weatherString);
      String weatherId = weather.basic.weatherId;
      String weatherUrl = "https://free-api.heweather.com/v5/weather?key=" + ConstantPool.HE_WEATHER_KEY + "&" + "city=" + weatherId;
      HttpUtils.sendOkHttpRequest(weatherUrl, new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              String responseText = response.body().string();
              Weather weather = JsonParseUtil.handleWeatherResponse(responseText);
              if (weather != null && "ok".equals(weather.status)) {
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                edit.putString("weather", responseText);
                edit.apply();
              }
            }

            @Override
            public void onFailure(Call call, IOException e) {
              e.printStackTrace();
            }
          }
      );
    }
  }

  private void updateBingPic() {
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
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
            edit.putString("bing", bingPic);
            edit.apply();
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }

      @Override
      public void onFailure(Call call, IOException e) {
        e.printStackTrace();
      }
    });
  }

}
