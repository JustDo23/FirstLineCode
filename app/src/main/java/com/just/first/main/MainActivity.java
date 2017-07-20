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
import com.just.first.chapter03.ArrayAdapterActivity;
import com.just.first.chapter03.CommonViewActivity;
import com.just.first.chapter03.CustomViewActivity;
import com.just.first.chapter03.PercentLayoutActivity;
import com.just.first.chapter03.RecyclerViewActivity;
import com.just.first.chapter03.RelativeLayoutActivity;
import com.just.first.chapter04.NewsTitleActivity;
import com.just.first.chapter04.QualifierActivity;
import com.just.first.chapter04.SimpleFragmentActivity;
import com.just.first.chapter05.LocalBroadcastActivity;
import com.just.first.chapter05.NetworkChangeActivity;
import com.just.first.chapter05.SendBroadcastActivity;
import com.just.first.chapter06.FileStoreActivity;
import com.just.first.chapter06.SQLiteActivity;
import com.just.first.chapter06.SharedPreferencesActivity;
import com.just.first.chapter08.NotificationActivity;
import com.just.first.chapter13.AlarmActivityG;

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
    chapterList.add("3.2.0 常用控件的使用方法");
    chapterList.add("3.3.2 相对布局");
    chapterList.add("3.3.4 百分比布局");
    chapterList.add("3.4.2 创建自定义控件");
    chapterList.add("3.5.1 ListView 的简单用法");
    chapterList.add("3.6.1 RecyclerView 的简单用法");
    chapterList.add("4.2.1 Fragment 的简单用法");
    chapterList.add("4.4.1 使用限定符");
    chapterList.add("4.5.1 Fragment 简易新闻");
    chapterList.add("5.2.1 动态广播监听网络变化");
    chapterList.add("5.3.2 发送广播");
    chapterList.add("5.4.1 本地广播机制");
    chapterList.add("6.2.1 文件存储");
    chapterList.add("6.3.1 SharedPreferences 存储");
    chapterList.add("6.4.1 SQLite 数据库");

    chapterList.add("----------");

    chapterList.add("8.2.2 通知的使用方法");
    chapterList.add("定时任务 Alarm 机制");
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
      case "3.2.0 常用控件的使用方法":
        startActivity(new Intent(this, CommonViewActivity.class));
        break;
      case "3.3.2 相对布局":
        startActivity(new Intent(this, RelativeLayoutActivity.class));
        break;
      case "3.3.4 百分比布局":
        startActivity(new Intent(this, PercentLayoutActivity.class));
        break;
      case "3.4.2 创建自定义控件":
        startActivity(new Intent(this, CustomViewActivity.class));
        break;
      case "3.5.1 ListView 的简单用法":
        startActivity(new Intent(this, ArrayAdapterActivity.class));
        break;
      case "3.6.1 RecyclerView 的简单用法":
        startActivity(new Intent(this, RecyclerViewActivity.class));
        break;
      case "4.2.1 Fragment 的简单用法":
        startActivity(new Intent(this, SimpleFragmentActivity.class));
        break;
      case "4.4.1 使用限定符":
        startActivity(new Intent(this, QualifierActivity.class));
        break;
      case "4.5.1 Fragment 简易新闻":
        startActivity(new Intent(this, NewsTitleActivity.class));
        break;
      case "5.2.1 动态广播监听网络变化":
        startActivity(new Intent(this, NetworkChangeActivity.class));
        break;
      case "5.3.2 发送广播":
        startActivity(new Intent(this, SendBroadcastActivity.class));
        break;
      case "5.4.1 本地广播机制":
        startActivity(new Intent(this, LocalBroadcastActivity.class));
        break;
      case "6.2.1 文件存储":
        startActivity(new Intent(this, FileStoreActivity.class));
        break;
      case "6.3.1 SharedPreferences 存储":
        startActivity(new Intent(this, SharedPreferencesActivity.class));
        break;
      case "6.4.1 SQLite 数据库":
        startActivity(new Intent(this, SQLiteActivity.class));
        break;


      case "8.2.2 通知的使用方法":
        startActivity(new Intent(this, NotificationActivity.class));
        break;

      case "定时任务 Alarm 机制":
        startActivity(new Intent(this, AlarmActivityG.class));
        break;
    }
  }
}
