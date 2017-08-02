package com.just.first.chapter09;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * 9.5.1 最佳实践-网络请求工具类
 *
 * @author JustDo23
 * @since 2017年08月01日
 */
public class HttpUtil {

  public static void sendHttpRequest(final String address, final HttpCallBackListener httpCallBackListener) {
    new Thread(new Runnable() {

      @Override
      public void run() {
        HttpURLConnection httpURLConnection = null;
        try {
          URL url = new URL(address);
          httpURLConnection = (HttpURLConnection) url.openConnection();
          httpURLConnection.setRequestMethod("GET");
          httpURLConnection.setConnectTimeout(8000);
          httpURLConnection.setReadTimeout(8000);
          httpURLConnection.setDoInput(true);
          httpURLConnection.setDoOutput(true);
          InputStream inputStream = httpURLConnection.getInputStream();
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
          StringBuilder response = new StringBuilder();
          String line;
          while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
          }
          if (httpCallBackListener != null) {
            httpCallBackListener.onFinish(response.toString());
          }
        } catch (Exception e) {
          if (httpCallBackListener != null) {
            httpCallBackListener.onError(e);
          }
        } finally {
          if (httpURLConnection != null) {
            httpURLConnection.disconnect();
          }
        }
      }
    }).start();
  }

  public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder()
        .url(address)
        .build();
    okHttpClient.newCall(request).enqueue(callback);// 开启子线程
  }

}
