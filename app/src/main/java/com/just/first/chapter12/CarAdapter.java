package com.just.first.chapter12;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.just.first.R;

import java.util.List;

/**
 * 12.5.1 卡片布局-汽车
 *
 * @author JustDo23
 * @since 2017年08月19日
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

  private Context context;// 上下文
  private List<Car> carList;// 汽车列表

  public CarAdapter(Context context, List<Car> carList) {
    this.context = context;
    this.carList = carList;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_drawer_layout_item, parent, false);
    ViewHolder viewHolder = new ViewHolder(itemView);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.cardView.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

      }
    });
    holder.tv_car_name.setText(carList.get(position).getName());
    Glide.with(context).load(carList.get(position).getImageId()).into(holder.iv_car);

  }

  @Override
  public int getItemCount() {
    return carList == null ? 0 : carList.size();
  }


  /**
   * 内部复用提高性能
   *
   * @since 2017年08月19日
   */
  class ViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView iv_car;
    TextView tv_car_name;

    public ViewHolder(View itemView) {
      super(itemView);
      cardView = (CardView) itemView.findViewById(R.id.cardView);
      iv_car = (ImageView) itemView.findViewById(R.id.iv_car);
      tv_car_name = (TextView) itemView.findViewById(R.id.tv_car_name);
    }

  }

}
