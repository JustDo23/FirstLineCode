package com.just.first.chapter12;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.just.first.R;
import com.just.first.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 12.6.1 下拉刷新
 *
 * @author JustDo23
 * @since 2017年08月20日
 */
public class SwipeRefreshLayoutActivity extends AppCompatActivity {

  private RecyclerView rv_car;
  private List<Car> carList = new ArrayList<>();
  private CarAdapter carAdapter;

  private SwipeRefreshLayout srl_car;

@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_swipe_refresh_layout);
  rv_car = (RecyclerView) findViewById(R.id.rv_car);
  GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
  rv_car.setLayoutManager(gridLayoutManager);
  initCar();
  srl_car = (SwipeRefreshLayout) findViewById(R.id.srl_car);// 找控件
  srl_car.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);// 设置颜色
  srl_car.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {// 刷新监听

    @Override
    public void onRefresh() {// 主线程
      refreshCars();
    }
  });
}

private void refreshCars() {
  new Thread(new Runnable() {

    @Override
    public void run() {
      try {
        Thread.sleep(6000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      runOnUiThread(new Runnable() {

        @Override
        public void run() {
          ToastUtil.showShortToast(SwipeRefreshLayoutActivity.this, "Refresh Success");
          srl_car.setRefreshing(false);// 停止刷新
        }
      });
    }
  }).start();
}

  private void initCar() {
    for (int i = 0; i < 25; i++) {
      carList.add(new Car("Car " + i, R.mipmap.ic_orange));
    }
    carAdapter = new CarAdapter(this, carList);
    rv_car.setAdapter(carAdapter);
  }

}
