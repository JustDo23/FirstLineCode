package com.just.first.chapter02;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 2.3.3 更多隐式 Intent 的用法
 *
 * @author JustDo23
 * @since 2017年05月07日
 */
public class IntentActivity extends BaseActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intent);
    findViewById(R.id.bt_browser).setOnClickListener(this);
    findViewById(R.id.bt_dial).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_browser:// 启动浏览器
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse("https://www.baidu.com"));
        startActivity(browserIntent);
        break;
      case R.id.bt_dial:// 启动拨号
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:10086"));
        startActivity(dialIntent);
        break;
    }
  }
}
