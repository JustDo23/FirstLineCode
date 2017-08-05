package com.just.first.chapter10;

import android.app.IntentService;
import android.content.Intent;

import com.just.first.utils.LogUtils;

/**
 * 10.5.2 IntentService
 *
 * @author JustDo23
 * @since 2017年08月05日
 */
public class FirstIntentService extends IntentService {

  public FirstIntentService() {
    super("FirstIntentService");// 父类含参构造。参数用来命名工作线程。
  }

  /**
   * 运行在子线程中，运行结束后销毁服务。每次只处理一个 Intent。
   *
   * @param intent 意图
   */
  @Override
  protected void onHandleIntent(Intent intent) {
    LogUtils.e("FirstIntentService --> onHandleIntent()");
    LogUtils.e("FirstIntentService --> Thread id is " + Thread.currentThread().getId());
    LogUtils.e("FirstIntentService --> Thread name is " + Thread.currentThread().getName());
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    LogUtils.e("FirstIntentService --> onDestroy()");
  }

}
