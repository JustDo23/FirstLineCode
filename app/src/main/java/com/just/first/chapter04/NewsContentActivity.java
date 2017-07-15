package com.just.first.chapter04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.just.first.R;

/**
 * 4.5.1 Fragment 简易新闻
 *
 * @author JustDo23
 * @since 2017年07月15日
 */
public class NewsContentActivity extends AppCompatActivity {

  private String title;
  private String content;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news_content);

    title = getIntent().getStringExtra("title");
    content = getIntent().getStringExtra("content");// 上个界面传递数据
    NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.frag_news_content);// 获取 Fragment 实例
    newsContentFragment.refreshContent(title, content);// 进行界面刷新
  }

  /**
   * 启动界面并传值
   *
   * @param context 上下文
   * @param title   标题
   * @param content 内容
   */
  public static void actionStart(Context context, String title, String content) {
    Intent intent = new Intent(context, NewsContentActivity.class);
    intent.putExtra("title", title);
    intent.putExtra("content", content);
    context.startActivity(intent);
  }
}
