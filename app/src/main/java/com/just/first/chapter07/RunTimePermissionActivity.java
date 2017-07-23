package com.just.first.chapter07;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

/**
 * 7.2.2 运行时申请权限
 *
 * @author JustDo23
 * @since 2017年07月23日
 */
public class RunTimePermissionActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_run_time_permission);
  }

  /**
   * 点击按钮执行操作
   */
  public void request(View view) {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {// 判断没有权限
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);// 请求权限[上下文][权限数组集合][请求码]
      return;
    } else {// 判断有权限
      callPhone();
    }
  }

  private void callPhone() {
    try {
      Intent intent = new Intent(Intent.ACTION_CALL);
      intent.setData(Uri.parse("tel:10086"));
      startActivity(intent);
    } catch (SecurityException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * 请求权限用户操作后回调函数
   *
   * @param requestCode  请求码
   * @param permissions  权限数组集合
   * @param grantResults 授权情况数组集合
   */
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case 1:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          callPhone();
        } else {
          ToastUtil.showShortToast(this, "You denied the permission");
        }
        break;
    }
  }

}
