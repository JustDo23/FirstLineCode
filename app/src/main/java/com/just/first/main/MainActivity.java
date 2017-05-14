package com.just.first.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.base.OnBaseOperationListener;
import com.just.first.chapter02.IntentActivity;
import com.just.first.chapter02.KillAllActivity;
import com.just.first.chapter02.LifeCycleActivity;
import com.just.first.chapter02.MenuActivity;
import com.just.first.chapter02.RecoveryActivity;
import com.just.first.chapter02.StartActivity;
import com.just.first.chapter03.CommonViewActivity;
import com.just.first.chapter03.PercentLayoutActivity;
import com.just.first.chapter03.RelativeLayoutActivity;
import com.just.first.chapter08.NotificationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 2017年05月02日开始看郭霖的第一行代码第二版，过程中感觉有些地方需要代码实践一番。
 * <p>
 * 程序入口
 *
 * @author JustDo23
 * @since 2017年05月04日
 */
public class MainActivity extends BaseActivity implements OnBaseOperationListener {

  private RecyclerView rv_chapters;// 章节列表
  private List<String> chapterList = new ArrayList<>();// 章节列表数据集合
  private ChapterListAdapter adapter;// 适配器

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().hide();// 隐藏标题栏 getActionBar(); return null
    initData();
    rv_chapters = (RecyclerView) findViewById(R.id.rv_chapters);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);// 线性布局管理器
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);// 方向垂直
    rv_chapters.setLayoutManager(linearLayoutManager);
    adapter = new ChapterListAdapter(chapterList);
    adapter.setOnBaseOperationListener(this);// 添加了点击事件
    rv_chapters.setAdapter(adapter);
  }

  /**
   * 初始化数据
   */
  private void initData() {
    chapterList.add("2.2.5 在活动使用 Menu");
    chapterList.add("2.3.3 更多隐式 Intent 的用法");
    chapterList.add("2.4.4 体验活动的生命周期");
    chapterList.add("2.4.5 活动被回收了怎么办");
    chapterList.add("2.6.2 随时随地退出程序");
    chapterList.add("2.6.3 启动活动的最佳写法");
    chapterList.add("3.2.x 常用控件的使用方法");
    chapterList.add("3.3.2 相对布局");
    chapterList.add("3.3.4 百分比布局");

    chapterList.add("8.2.2 通知的使用方法");
  }

  @Override
  public void onBaseOperationListener(Object... obj) {
    int position = (int) obj[0];
    switch (chapterList.get(position)) {
      case "2.2.5 在活动使用 Menu":
        startActivity(new Intent(this, MenuActivity.class));
        break;
      case "2.3.3 更多隐式 Intent 的用法":
        startActivity(new Intent(this, IntentActivity.class));
        break;
      case "2.4.4 体验活动的生命周期":
        startActivity(new Intent(this, LifeCycleActivity.class));
        break;
      case "2.4.5 活动被回收了怎么办":
        startActivity(new Intent(this, RecoveryActivity.class));
        break;
      case "2.6.2 随时随地退出程序":
        startActivity(new Intent(this, KillAllActivity.class));
        break;
      case "2.6.3 启动活动的最佳写法":
        StartActivity.actionStart(this, "JustDo23", "KeepKeep");
        break;
      case "3.2.x 常用控件的使用方法":
        startActivity(new Intent(this, CommonViewActivity.class));
        break;
      case "3.3.2 相对布局":
        startActivity(new Intent(this, RelativeLayoutActivity.class));
        break;
      case "3.3.4 百分比布局":
        startActivity(new Intent(this, PercentLayoutActivity.class));
        break;


      case "8.2.2 通知的使用方法":
        startActivity(new Intent(this, NotificationActivity.class));
        break;
    }
  }
}
