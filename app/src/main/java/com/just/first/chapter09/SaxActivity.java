package com.just.first.chapter09;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.base.BaseActivity;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 9.3.2 SAX 解析方式
 *
 * @author JustDo23
 * @since 2017年08月01日
 */
public class SaxActivity extends BaseActivity {

  private TextView tv_result;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sax);
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
          parseXMLWithSAX(responseContent);// 进行数据解析
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  /**
   * 使用 SAX 方式解析 XML
   */
  private void parseXMLWithSAX(String responseContent) {
    try {
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();// 获取工厂实例
      XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();// 利用工厂获取解析器后获取XML读取器
      SaxHandler saxHandler = new SaxHandler();// 实例化自定义的 Handler
      xmlReader.setContentHandler(saxHandler);//  读取器设置 Handler
      xmlReader.parse(new InputSource(new StringReader(responseContent)));// 开始解析
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

