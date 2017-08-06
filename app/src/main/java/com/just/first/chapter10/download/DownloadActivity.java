package com.just.first.chapter10.download;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

/**
 * 下载界面
 *
 * @author JustDo23
 * @since 2017年08月06日
 */
public class DownloadActivity extends BaseActivity {

  private DownloadService.DownloadBinder downloadBinder;

  private ServiceConnection serviceConnection = new ServiceConnection() {

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      downloadBinder = (DownloadService.DownloadBinder) service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      downloadBinder = null;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_download);
    bindService();// 绑定服务
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 33);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case 33:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          bindService();// 绑定服务
        } else {
          ToastUtil.showShortToast(this, "暂无权限");
          finish();
        }
        break;
    }
  }

  /**
   * 绑定服务
   */
  private void bindService() {
    Intent intent = new Intent(this, DownloadService.class);
    startService(intent);// 启动
    bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);// 绑定服务
  }

  public void startDownload(View view) {
    if (downloadBinder != null) {
      String downloadUrl = "http://www.guixue.com/soft/cet.apk";
      downloadBinder.startDownload(downloadUrl);
    }
  }

  public void pauseDownload(View view) {
    if (downloadBinder != null) {
      downloadBinder.pauseDownload();
    }
  }

  public void cancelDownload(View view) {
    if (downloadBinder != null) {
      downloadBinder.cancelDownload();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unbindService(serviceConnection);// 必须进行解绑
  }
}
