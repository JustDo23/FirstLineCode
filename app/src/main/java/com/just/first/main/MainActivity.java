package com.just.first.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.base.OnBaseOperationListener;
import com.just.first.utils.ToastUtil;

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
    chapterList.add("第一张");
    chapterList.add("第2张");
    chapterList.add("第3张");
    chapterList.add("第4张");
  }

  @Override
  public void onBaseOperationListener(Object... obj) {
    int position = (int) obj[0];
    switch (chapterList.get(position)) {
      case "第一张":
        ToastUtil.showShortToast(this, "第一张");
        break;
    }
  }
}
