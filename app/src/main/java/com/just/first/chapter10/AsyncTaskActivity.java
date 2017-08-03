package com.just.first.chapter10;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.base.BaseActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 10.2.4 使用 AsyncTask
 *
 * @author JustDo23
 * @since 2017年08月03日
 */
public class AsyncTaskActivity extends BaseActivity {

  private TextView tv_result;
  private ProgressDialog progressDialog;// 加载进度弹框

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_async_task);
    tv_result = (TextView) findViewById(R.id.tv_result);
    progressDialog = new ProgressDialog(this);
    progressDialog.setTitle("网络请求");
    progressDialog.setMessage("Loading...");
    progressDialog.setProgress(0);
    progressDialog.setMax(100);
  }

  public void startAsyncTask(View view) {
    new NetAsyncTask().execute("https://www.baidu.com");
  }


  /**
   * 自定义异步任务
   *
   * @since 2017年08月03日
   */
  class NetAsyncTask extends AsyncTask<String, Integer, String> {

    /**
     * 任务启动之前
     */
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog.show();
    }

    /**
     * 任务启动并后台运行
     */
    @Override
    protected String doInBackground(String... params) {
      publishProgress(20);
      try {
        OkHttpClient okHttpClient = new OkHttpClient();// OK 客户端
        Request request = new Request.Builder()
            .url("https://www.baidu.com")
            .build();
        Response response = okHttpClient.newCall(request).execute();// 执行请求返回响应对象
        String responseContent = response.body().string();// 从响应对象中获取字符串
        return responseContent;
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }

    /**
     * 任务运行进度
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
      super.onProgressUpdate(values);
      progressDialog.setProgress(values[0]);
    }

    /**
     * 任务执行完毕
     */
    @Override
    protected void onPostExecute(String s) {
      super.onPostExecute(s);
      tv_result.setText(s);
      progressDialog.dismiss();
    }

  }

}