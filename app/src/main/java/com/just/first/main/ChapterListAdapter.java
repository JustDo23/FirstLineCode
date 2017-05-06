package com.just.first.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.base.OnBaseOperationListener;

import java.util.List;

/**
 * 章节列表适配器
 *
 * @author JustDo23
 * @since 2017年05月06日
 */
public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ViewHolder> {

  private List<String> chapterList;// 数据源
  private OnBaseOperationListener onBaseOperationListener;// 监听事件

  public ChapterListAdapter(List<String> chapterList) {
    this(chapterList, null);
  }

  public ChapterListAdapter(List<String> chapterList, OnBaseOperationListener onBaseOperationListener) {
    this.chapterList = chapterList;
    this.onBaseOperationListener = onBaseOperationListener;
  }

  /**
   * 显示条目总数
   *
   * @return 数据数量
   */
  @Override
  public int getItemCount() {
    return chapterList == null ? 0 : chapterList.size();
  }

  /**
   * 创建复用的 ViewHolder
   *
   * @param parent   父布局
   * @param viewType 类型
   * @return 自定义的ViewHolder
   */
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false);
    final ViewHolder viewHolder = new ViewHolder(itemView);
    itemView.setOnClickListener(new View.OnClickListener() {// 添加了根本布局的点击事件

      @Override
      public void onClick(View v) {
        int position = viewHolder.getAdapterPosition();
        if (onBaseOperationListener != null) {
          onBaseOperationListener.onBaseOperationListener(position);
        }
      }
    });
    return viewHolder;
  }

  /**
   * 将数据绘制到界面上
   *
   * @param holder   自定义的 viewHolder
   * @param position 显示位置
   */
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    String title = chapterList.get(position);
    holder.tv_title.setText(title);
  }


  /**
   * 内部的ViewHolder用于复用
   */
  static class ViewHolder extends RecyclerView.ViewHolder {

    TextView tv_title;

    public ViewHolder(View itemView) {
      super(itemView);// Item 布局
      tv_title = (TextView) itemView.findViewById(R.id.tv_title);
    }
  }


  public void setOnBaseOperationListener(OnBaseOperationListener onBaseOperationListener) {
    this.onBaseOperationListener = onBaseOperationListener;
  }

}
