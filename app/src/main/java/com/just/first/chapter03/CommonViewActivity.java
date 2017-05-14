package com.just.first.chapter03;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 3.2.x 常用控件的使用方法
 *
 * @author JustDo23
 * @since 2017年05月08日
 */
public class CommonViewActivity extends BaseActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_common_view);
    findViewById(R.id.bt_progress_dialog).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_progress_dialog:
        showProgressDialog();
        break;
    }
  }

  private void showProgressDialog() {
    ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading...");
    progressDialog.setTitle("This is ProgressDialog");
    progressDialog.setCancelable(true);// 按下返回键隐藏
    progressDialog.show();
  }
}
