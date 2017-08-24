package com.just.first.chapter13;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.just.first.R;

/**
 * 13.7.1 Lambda 表达式
 *
 * @author JustDo23
 * @since 2017年08月24日
 */
public class LambdaActivity extends AppCompatActivity {

  private Runnable runnable = new Runnable() {

    @Override
    public void run() {
      // 处理具体逻辑
    }
  };

  private Runnable runnableScroll = () -> {
    // 处理具体逻辑
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lambda);
    findViewById(R.id.bt_click).setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // 处理具体逻辑
      }
    });

    findViewById(R.id.bt_click).setOnClickListener(v -> {
      // 处理具体逻辑
    });

  }

  public void four(View view) {
    new Thread(new Runnable() {

      @Override
      public void run() {
        // 处理具体逻辑
      }
    }).start();

    new Thread(() -> {
      // 处理具体逻辑
    }).start();

  }

}
