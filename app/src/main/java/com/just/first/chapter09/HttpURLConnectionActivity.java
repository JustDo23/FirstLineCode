package com.just.first.chapter09;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.base.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 9.2.1 使用 HttpURLConnection
 *
 * @author JustDo23
 * @since 2017年07月30日
 */
public class HttpURLConnectionActivity extends BaseActivity {

  private TextView tv_result;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_http_urlconnection);
    tv_result = (TextView) findViewById(R.id.tv_result);
  }

  public void sendRequest(View view) {
    sendRequestWithHttpURLConnection();
  }

  private void sendRequestWithHttpURLConnection() {
    new Thread(new Runnable() {// 网络请求耗时操作放在子线程中

      @Override
      public void run() {
        HttpURLConnection httpURLConnection = null;// 连接对象
        BufferedReader bufferedReader = null;// 数据读取流
        try {
          URL url = new URL("https://www.baidu.com");// URL 对象
          httpURLConnection = (HttpURLConnection) url.openConnection();// 打开连接
          httpURLConnection.setRequestMethod("GET");// 设置网络请求模式
          httpURLConnection.setConnectTimeout(8000);// 设置连接超时时间
          httpURLConnection.setReadTimeout(8000);// 设置数据读取超时时间
          InputStream inputStream = httpURLConnection.getInputStream();// 获取数据读取流
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream));// 对流封装提供效率
          StringBuilder response = new StringBuilder();// 请求结果
          String line;
          while ((line = bufferedReader.readLine()) != null) {
            response.append(line);// 从数据读取流中读取数据
          }
          showResponse(response.toString());// 进行界面展示
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          if (bufferedReader != null) {
            try {
              bufferedReader.close();// 关闭数据流
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          if (httpURLConnection != null) {
            httpURLConnection.disconnect();// 关闭网络连接
          }
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
