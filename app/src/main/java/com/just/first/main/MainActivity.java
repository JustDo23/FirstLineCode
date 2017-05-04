package com.just.first.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.just.first.R;

/**
 * 2017年05月02日开始看郭霖的第一行代码第二版，过程中感觉有些地方需要代码实践一番。
 * <p>
 * 程序入口
 *
 * @author JustDo23
 * @since 2017年05月04日
 */
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
