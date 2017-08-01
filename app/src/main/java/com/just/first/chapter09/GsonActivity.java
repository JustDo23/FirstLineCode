package com.just.first.chapter09;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.LogUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 9.4.2 使用 Gson
 *
 * @author JustDo23
 * @since 2017年08月01日
 */
public class GsonActivity extends BaseActivity {

  private TextView tv_result;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gson);
    tv_result = (TextView) findViewById(R.id.tv_result);
  }

  public void sendRequest(View view) {
    sendRequestWithOkHttp();
  }

  private void sendRequestWithOkHttp() {
    new Thread(new Runnable() {// 网络请求耗时操作放在子线程中

      @Override
      public void run() {
        try {
          OkHttpClient okHttpClient = new OkHttpClient();// OK 客户端
          Request request = new Request.Builder()
              .url("http://osxmqydw4.bkt.clouddn.com/FirstLine_Json.json")
              .build();
          Response response = okHttpClient.newCall(request).execute();// 执行请求返回响应对象
          String responseContent = response.body().string();// 从响应对象中获取字符串
          parseJsonWithGson(responseContent);// 进行数据解析
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  /**
   * 解析Json
   */
  private void parseJsonWithGson(String responseContent) {
    Gson gson = new Gson();// 实例化对象
    List<Product> productList = gson.fromJson(responseContent, new TypeToken<List<Product>>() {}.getType());// 解析数组方法
    for (Product product : productList) {
      LogUtils.e("id = " + product.getId() + " name = " + product.getName() + " version = " + product.getVersion() + "\n");
    }
  }

}

