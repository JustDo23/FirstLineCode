package com.just.first.base;

/**
 * [接口][回调]针对于经常使用到的回调,抽象出一个公共的接口,方便回调的实现
 *
 * @author JustDo23
 * @since 2017年05月06日
 */
public interface OnBaseOperationListener {

  /**
   * 抽象一个可参的通用方法
   */
  void onBaseOperationListener(Object... obj);

}
