package com.just.first.chapter03;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.utils.ToastUtil;

import java.util.List;

/**
 * 3.6.1 RecyclerView 的简单用法
 *
 * @author JustDo23
 * @since 2017年05月14日
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

  private List<Fruit> fruitList;

  public FruitAdapter(List<Fruit> fruitList) {
    this.fruitList = fruitList;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_item, parent, false);
    final ViewHolder viewHolder = new ViewHolder(rootView);
    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        int position = viewHolder.getAdapterPosition();
        ToastUtil.showShortToast(v.getContext(), fruitList.get(position).getFruitName());
      }
    });
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.tv_fruit_name.setText(fruitList.get(position).getFruitName());
  }

  @Override
  public int getItemCount() {
    return fruitList == null ? 0 : fruitList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    View rootView;
    ImageView iv_fruit;
    TextView tv_fruit_name;

    public ViewHolder(View itemView) {
      super(itemView);
      rootView = itemView;
      iv_fruit = (ImageView) itemView.findViewById(R.id.iv_fruit);
      tv_fruit_name = (TextView) itemView.findViewById(R.id.tv_fruit_name);
    }
  }
}
