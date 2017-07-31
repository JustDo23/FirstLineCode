package com.just.first.chapter09;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 9.1.1 加载网页
 *
 * @author JustDo23
 * @since 2017年07月30日
 */
public class WebViewActivity extends BaseActivity {

  private WebView wb_net;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web_view);
    wb_net = (WebView) findViewById(R.id.wb_net);
    initWebView();
  }

  private void initWebView() {
    wb_net.getSettings().setJavaScriptEnabled(true);// 支持 JavaScript 脚本
    wb_net.setWebViewClient(new WebViewClient());// 网页跳转仍在当前浏览器
    wb_net.loadUrl("http://www.baidu.com");// 加载网页
  }

}
