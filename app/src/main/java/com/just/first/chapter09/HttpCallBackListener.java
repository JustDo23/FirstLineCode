package com.just.first.chapter09;

/**
 * 9.5.1 最佳实践-接口回调
 *
 * @author JustDo23
 * @since 2017年08月01日
 */
public interface HttpCallBackListener {

  /**
   * 网络请求完成时回调
   *
   * @param response 返回数据
   */
  void onFinish(String response);

  /**
   * 网络请求出现错误
   *
   * @param e 异常
   */
  void onError(Exception e);

}
