package com.just.first.chapter11;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.LogUtils;
import com.just.first.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 11.3.2 百度定位
 *
 * @author JustDo23
 * @since 2017年08月06日
 */
public class LBSActivity extends BaseActivity {

  private TextView tv_position;// 位置结果

  private LocationClient locationClient;// 百度定位客户端

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    locationClient = new LocationClient(getApplicationContext());// 实例化百度定位
    locationClient.registerLocationListener(new MyLocationListener());// 注册监听回调
    setContentView(R.layout.activity_lbs);// 设置显示布局
    tv_position = (TextView) findViewById(R.id.tv_position);// 找控件
    List<String> permissionList = new ArrayList<>();// 权限集合
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {// 位置权限
      permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);// 加入待申请集合
    }
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {// 位置权限
      permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);// 加入待申请集合
    }
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {// 电话权限
      permissionList.add(Manifest.permission.READ_PHONE_STATE);// 加入待申请集合
    }
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {// 存储卡权限
      permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);// 加入待申请集合
    }
    if (!permissionList.isEmpty()) {// 需要申请权限
      String[] permissions = permissionList.toArray(new String[permissionList.size()]);// 转转为数组
      ActivityCompat.requestPermissions(this, permissions, 11);// 申请权限
    } else {
      requestLocation();// 开始定位
    }
  }

  private void requestLocation() {
    initLocation();// 添加后可以多次定位
    locationClient.start();// 开始定位[只会进行一次调用]
  }

  /**
   * 初始化配置
   */
  private void initLocation() {
    LocationClientOption locationClientOption = new LocationClientOption();// 获取操作对象
    locationClientOption.setScanSpan(5000);// 设置刷新时间差
    locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 默认模式
    locationClient.setLocOption(locationClientOption);// 设置操作对象
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    locationClient.stop();// 停止定位[避免耗电]
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case 11:
        if (grantResults.length > 0) {
          for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
              ToastUtil.showShortToast(this, "必须同意所有权限才能使用");
              finish();
              return;
            }
          }
          requestLocation();// 有权限了进行定位
        } else {
          ToastUtil.showShortToast(this, "发生未知错误");
        }
        break;
    }
  }


  /**
   * 百度定位监听回调
   *
   * @since 2017年08月07日
   */
  public class MyLocationListener implements BDLocationListener {

    /**
     * 接收位置信息
     *
     * @param bdLocation
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
      final StringBuilder currentPosition = new StringBuilder();
      currentPosition.append("纬度：").append(bdLocation.getLatitude()).append("\n");// 纬度
      currentPosition.append("纬度：").append(bdLocation.getLongitude()).append("\n");// 纬度
      currentPosition.append("定位方式：");
      if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {// GPS
        currentPosition.append("GPS");
      } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络
        currentPosition.append("网络");
      }
      currentPosition.append("\n");
      runOnUiThread(new Runnable() {

        @Override
        public void run() {
          tv_position.setText(currentPosition.toString());// 界面更新
        }
      });
    }

    /**
     * 连接热点消息
     *
     * @param message
     * @param what
     */
    @Override
    public void onConnectHotSpotMessage(String message, int what) {
      LogUtils.e("--> onConnectHotSpotMessage()" + " >> message = " + message + " >> what = " + what);
    }

  }

}
