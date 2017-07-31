package com.just.first.chapter09;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.LogUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 9.3.1 Pull 解析方式
 *
 * @author JustDo23
 * @since 2017年07月31日
 */
public class PullActivity extends BaseActivity {

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
          Request request = new Request.Builder()
              .url("http://osxmqydw4.bkt.clouddn.com/FirstLine_pull.xml")
              .build();
          Response response = okHttpClient.newCall(request).execute();// 执行请求返回响应对象
          String responseContent = response.body().string();// 从响应对象中获取字符串
          parseXMLWithPull(responseContent);// 进行数据解析
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  /**
   * 使用 Pull 方式解析 XML
   */
  private void parseXMLWithPull(String responseContent) {
    try {
      XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();// 获取工厂实例
      XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();// 工厂实例获取一个解析器
      xmlPullParser.setInput(new StringReader(responseContent));// 以流的方式给解析器设置数据源
      int eventType = xmlPullParser.getEventType();// 获取事件类型
      String id = "";
      String name = "";
      String version = "";
      while (eventType != XmlPullParser.END_DOCUMENT) {// 不是文档结尾
        String nodeName = xmlPullParser.getName();// 获取节点名称
        switch (eventType) {// 事件类型
          case XmlPullParser.START_TAG:// 标签开始
            if ("id".equals(nodeName)) {// 判断标签名称
              id = xmlPullParser.nextText();// 获取标签中的内容
            } else if ("name".equals(nodeName)) {
              name = xmlPullParser.nextText();
            } else if ("version".equals(nodeName)) {
              version = xmlPullParser.nextText();
            }
            break;
          case XmlPullParser.END_TAG:// 标签结束
            if ("app".equals(nodeName)) {
              LogUtils.e("id = " + id + " name = " + name + " version = " + version + "\n");
            }
            break;
          default:
            break;
        }
        eventType = xmlPullParser.next();// 获取下一个事件
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
