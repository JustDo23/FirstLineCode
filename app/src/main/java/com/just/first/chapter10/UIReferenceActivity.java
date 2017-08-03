package com.just.first.chapter10;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.base.BaseActivity;

/**
 * 10.2.2 UI 刷新
 *
 * @author JustDo23
 * @since 2017年08月02日
 */
public class UIReferenceActivity extends BaseActivity {

  private TextView tv_result;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_uireference);
    tv_result = (TextView) findViewById(R.id.tv_result);
  }

  public void uiReference(View view) {
    new Thread(new Runnable() {

      @Override
      public void run() {
        tv_result.setText("Nice to meet you");
      }
    }).start();
  }

  private Handler handler = new Handler() {

    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what) {
        case 32:
          // 更新 UI 操作
          tv_result.setText("Nice to meet you");
          break;
      }
    }
  };

  public void uiReferenceHandler(View view) {
    new Thread(new Runnable() {

      @Override
      public void run() {
        Message message = handler.obtainMessage();// 获取消息对象
        message.what = 32;// 设置标志码
        handler.sendMessage(message);// 发送消息
      }
    }).start();
  }

}