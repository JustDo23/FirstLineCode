package com.just.first.chapter10;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 10.3.2 服务入门
 *
 * @author JustDo23
 * @since 2017年08月04日
 */
public class SecondServiceActivity extends BaseActivity {

  private FirstService.DownloadBinder downloadBinder;

  /**
   * 服务连接对象
   */
  private ServiceConnection serviceConnection = new ServiceConnection() {

    /**
     * 服务连接回调
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      downloadBinder = (FirstService.DownloadBinder) service;
      downloadBinder.startDownload();
      downloadBinder.getProgress();
    }

    /**
     * 服务断开连接回调
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_service);
  }

  public void startService(View view) {
    Intent startIntent = new Intent(this, FirstService.class);// 意图指定服务
    startService(startIntent);// 启动服务
  }

  public void stopService(View view) {
    Intent stopIntent = new Intent(this, FirstService.class);// 意图指定服务
    stopService(stopIntent);// 停止服务
  }

  public void bindService(View view) {
    Intent bindIntent = new Intent(this, FirstService.class);// 意图指定服务
    bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);// 绑定服务[标志位表示活动和服务进行绑定之后自动创建服务]
  }

  public void unbindService(View view) {
    unbindService(serviceConnection);// 解绑服务[停止服务]
  }

}
