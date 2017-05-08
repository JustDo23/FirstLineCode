package com.just.first.chapter02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

/**
 * 2.6.3 启动活动的最佳写法
 *
 * @author JustDo23
 * @since 2017年05月08日
 */
public class StartActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);
    Intent intent = getIntent();
    String data1 = intent.getStringExtra("param1");
    String data2 = intent.getStringExtra("param2");
    ToastUtil.showShortToast(this, "data1 = " + data1 + "\ndata2 = " + data2);
  }

  public static void actionStart(Context context, String data1, String data2) {
    Intent intent = new Intent(context, StartActivity.class);
    intent.putExtra("param1", data1);
    intent.putExtra("param2", data2);
    context.startActivity(intent);
  }

}
