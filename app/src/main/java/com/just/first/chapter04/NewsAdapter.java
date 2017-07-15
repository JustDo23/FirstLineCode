package com.just.first.chapter04;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.just.first.R;

import java.util.List;

/**
 * 4.5.1 Fragment 简易新闻 - 新闻列表适配器
 *
 * @author JustDo23
 * @since 2017年07月15日
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

  private boolean isTwoPane;
  private List<News> newsList;

  public NewsAdapter(List<News> newsList) {
    this.newsList = newsList;
  }

  @Override
  public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_title_item, parent, false);
    final ViewHolder viewHolder = new ViewHolder(itemView);
    itemView.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        int position = viewHolder.getAdapterPosition();
        News news = newsList.get(position);
        if (isTwoPane) {// 双页模式
          NewsContentFragment newsContentFragment = (NewsContentFragment) ((NewsTitleActivity) v.getContext()).getSupportFragmentManager().findFragmentById(R.id.frag_news_content);
          newsContentFragment.refreshContent(news.getTitle(), news.getContent());
        } else {// 单页模式
          NewsContentActivity.actionStart(v.getContext(), news.getTitle(), news.getContent());
        }
      }
    });
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
    holder.tv_title_item.setText(newsList.get(position).getTitle());
  }

  @Override
  public int getItemCount() {
    return newsList == null ? 0 : newsList.size();
  }


  /**
   * 复用的 ViewHolder
   *
   * @author JustDo23
   */
  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView tv_title_item;

    public ViewHolder(View itemView) {
      super(itemView);
      tv_title_item = (TextView) itemView.findViewById(R.id.tv_title_item);
    }
  }


  public void setTwoPane(boolean twoPane) {
    isTwoPane = twoPane;
  }

}
