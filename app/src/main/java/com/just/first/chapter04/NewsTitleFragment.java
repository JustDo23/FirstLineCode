package com.just.first.chapter04;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.just.first.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 4.5.1 Fragment 简易新闻
 *
 * @author JustDo23
 * @since 2017年07月15日
 */
public class NewsTitleFragment extends Fragment {

  private View rootView;
  private RecyclerView rv_news_title;
  private List<News> newsList = new ArrayList<>();
  private NewsAdapter adapter;

  private boolean isTwoPane;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_news_title, container, false);
    findViewsById();
    return rootView;
  }

  private void findViewsById() {
    rv_news_title = (RecyclerView) rootView.findViewById(R.id.rv_news_title);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    rv_news_title.setLayoutManager(linearLayoutManager);
    initNewsList();
    adapter = new NewsAdapter(newsList);
    rv_news_title.setAdapter(adapter);
  }

  private void initNewsList() {
    for (int i = 0; i < 50; i++) {
      String title = "News Title " + i;
      String content = getRandomContent("This is news content.");
      News news = new News();
      news.setTitle(title);
      news.setContent(content);
      newsList.add(news);
    }
  }

  private String getRandomContent(String content) {
    Random random = new Random();
    int count = random.nextInt(20) + 1;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
      sb.append(i + " ").append(content).append("").append("\n");
    }
    return sb.toString();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (getActivity().findViewById(R.id.fl_news_content) != null) {
      isTwoPane = true;// 双页模式
    } else {
      isTwoPane = false;// 单页模式
    }
    adapter.setTwoPane(isTwoPane);
  }

}
