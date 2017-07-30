package com.just.first.chapter08;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

import java.io.File;

/**
 * 8.4.2 播放视频
 *
 * @author JustDo23
 * @since 2017年07月30日
 */
public class VideoActivity extends BaseActivity implements View.OnClickListener {

  private Button bt_play;
  private Button bt_pause;
  private Button bt_restart;
  private VideoView videoView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_video);
    bt_play = (Button) findViewById(R.id.bt_play);
    bt_pause = (Button) findViewById(R.id.bt_pause);
    bt_restart = (Button) findViewById(R.id.bt_restart);
    videoView = (VideoView) findViewById(R.id.vv_movie);
    bt_play.setOnClickListener(this);
    bt_pause.setOnClickListener(this);
    bt_restart.setOnClickListener(this);

    checkPermission();// 检查权限
  }

  private void checkPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 30);
    } else {
      initVideoPath();
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case 30:
        if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        } else {
          ToastUtil.showShortToast(this, "You denied the permission.");
          finish();
        }
        break;
    }
  }

  /**
   * 初始视频路径
   */
  private void initVideoPath() {
    File videoFile = new File(Environment.getExternalStorageDirectory() + "/JustDo23/video/", "luck.mp4");// 指定文件路径
    videoView.setVideoPath(videoFile.getPath());
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_play:// 播放
        if (!videoView.isPlaying()) {
          videoView.start();
        }
        break;
      case R.id.bt_pause:// 暂停
        if (videoView.isPlaying()) {
          videoView.pause();
        }
        break;
      case R.id.bt_restart:// 停止
        if (videoView.isPlaying()) {
          videoView.resume();
        }
        break;
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (videoView != null) {
      videoView.suspend();
    }
  }
}
