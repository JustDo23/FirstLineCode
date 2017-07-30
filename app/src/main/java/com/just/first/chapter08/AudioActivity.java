package com.just.first.chapter08;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

import java.io.File;
import java.io.IOException;

/**
 * 8.4.1 播放音频
 *
 * @author JustDo23
 * @since 2017年07月30日
 */
public class AudioActivity extends BaseActivity implements View.OnClickListener {

  private Button bt_play;
  private Button bt_pause;
  private Button bt_stop;

  private MediaPlayer mediaPlayer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_audio);
    bt_play = (Button) findViewById(R.id.bt_play);
    bt_pause = (Button) findViewById(R.id.bt_pause);
    bt_stop = (Button) findViewById(R.id.bt_stop);
    bt_play.setOnClickListener(this);
    bt_pause.setOnClickListener(this);
    bt_stop.setOnClickListener(this);

    checkPermission();// 检查权限
  }

  private void checkPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 30);
    } else {
      initMediaPlay();// 有权限进行初始化
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case 30:
        if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          initMediaPlay();
        } else {
          ToastUtil.showShortToast(this, "You denied the permission.");
          finish();
        }
        break;
    }
  }

  /**
   * 初始化播放器
   */
  private void initMediaPlay() {
    release();// 资源释放
    mediaPlayer = new MediaPlayer();// 初始化
    File audioFile = new File(Environment.getExternalStorageDirectory() + "/JustDo23/audio/", "Sugar.mp3");// 指定文件路径
    try {
      mediaPlayer.setDataSource(audioFile.getPath());
      mediaPlayer.prepare();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 释放资源
   */
  private void release() {
    if (mediaPlayer != null) {
      if (mediaPlayer.isPlaying()) {
        mediaPlayer.stop();
        mediaPlayer.reset();
      }
      mediaPlayer.release();
      mediaPlayer = null;
    }
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_play:// 播放
        if (mediaPlayer != null) {
          mediaPlayer.start();
        }
        break;
      case R.id.bt_pause:// 暂停
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
          mediaPlayer.pause();
        }
        break;
      case R.id.bt_stop:// 停止
        initMediaPlay();
        break;
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    release();
  }

}
