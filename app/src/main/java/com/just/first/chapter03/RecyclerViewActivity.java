package com.just.first.chapter03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.just.first.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 3.6.1 RecyclerView 的简单用法
 *
 * @author JustDo23
 * @since 2017年05月14日
 */
public class RecyclerViewActivity extends AppCompatActivity {

  private RecyclerView rv_fruit;
  private List<Fruit> fruitList = new ArrayList<>();
  private FruitAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);

    initFruits();// 初始化数据

    rv_fruit = (RecyclerView) findViewById(R.id.rv_fruit);

    // 瀑布流
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    rv_fruit.setLayoutManager(staggeredGridLayoutManager);

    adapter = new FruitAdapter(fruitList);
    rv_fruit.setAdapter(adapter);
  }

  private void initFruits() {
    for (int i = 0; i < 20; i++) {
      fruitList.add(new Fruit(R.mipmap.ic_launcher, getRandomLengthName("Apple" + i)));
    }
  }

  private String getRandomLengthName(String name) {
    Random random = new Random();
    int length = random.nextInt(30) + 1;
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      stringBuilder.append(name);
    }
    return stringBuilder.toString();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_recycler, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_horizontal:// 水平
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_fruit.setLayoutManager(linearLayoutManager);
        break;
      case R.id.menu_vertical:// 垂直
        LinearLayoutManager vlm = new LinearLayoutManager(this);
        vlm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_fruit.setLayoutManager(vlm);
        break;
      case R.id.menu_staggered:// 瀑布
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rv_fruit.setLayoutManager(staggeredGridLayoutManager);
        break;
    }
    return true;
  }

}
