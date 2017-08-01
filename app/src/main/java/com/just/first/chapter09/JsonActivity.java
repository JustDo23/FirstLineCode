package com.just.first.chapter09;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 9.4.1 解析 JSON
 *
 * @author JustDo23
 * @since 2017年08月01日
 */
public class JsonActivity extends BaseActivity {

  private TextView tv_result;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_json);
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
          parseJson(responseContent);// 进行数据解析
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  /**
   * 解析Json
   */
  private void parseJson(String responseContent) {
    try {
      JSONArray jsonArray = new JSONArray(responseContent);// 获取数组对象
      for (int i = 0; i < jsonArray.length(); i++) {// 对数组进行循环
        JSONObject jsonObject = jsonArray.getJSONObject(i);// 挨个获取JSONObject
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String version = jsonObject.getString("version");
        LogUtils.e("id = " + id + " name = " + name + " version = " + version + "\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

