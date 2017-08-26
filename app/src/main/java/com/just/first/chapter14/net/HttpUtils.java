package com.just.first.chapter14.net;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 网络请求工具
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class HttpUtils {

  public static void sendOkHttpRequest(String address, Callback callback) {
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder().url(address).build();
    okHttpClient.newCall(request).enqueue(callback);
  }

}
