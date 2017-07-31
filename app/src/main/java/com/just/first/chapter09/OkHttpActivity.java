package com.just.first.chapter09;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.base.BaseActivity;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 9.2.2 使用 OkHttp
 *
 * @author JustDo23
 * @since 2017年07月31日
 */
public class OkHttpActivity extends BaseActivity {

  private TextView tv_result;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ok_http);
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
          RequestBody requestBody = new FormBody.Builder()
              .add("userName", "admin")
              .add("passWord", "232323")
              .build();// 参数封装
          Request request = new Request.Builder()
              .url("https://www.baidu.com")
              .post(requestBody)// 用 POST 请求携带参数
              .build();
          Response response = okHttpClient.newCall(request).execute();// 执行请求返回响应对象
          String responseContent = response.body().string();// 从响应对象中获取字符串
          showResponse(responseContent);// 进行界面展示
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  private void showResponse(final String response) {
    runOnUiThread(new Runnable() {// 界面刷新的工作必须放在主线程中

      @Override
      public void run() {
        tv_result.setText(response);
      }
    });
  }

}
