package com.just.first.chapter04;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.just.first.R;

/**
 * 4.5.1 Fragment 简易新闻
 *
 * @author JustDo23
 * @since 2017年07月15日
 */
public class NewsContentFragment extends Fragment {

  private View rootView;
  private TextView tv_title;
  private TextView tv_content;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_news_content, container, false);
    findViewsById();
    return rootView;
  }

  private void findViewsById() {
    tv_title = (TextView) rootView.findViewById(R.id.tv_title);
    tv_content = (TextView) rootView.findViewById(R.id.tv_content);
  }

  /**
   * 数据刷新
   *
   * @param title   标题
   * @param content 内容
   */
  public void refreshContent(String title, String content) {
    tv_title.setText(title);
    tv_content.setText(content);
  }

}
