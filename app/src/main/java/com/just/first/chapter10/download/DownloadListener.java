package com.just.first.chapter10.download;

/**
 * 下载状态监听回调
 *
 * @author JustDo23
 * @since 2017年08月05日
 */
public interface DownloadListener {

  /**
   * 下载进度
   *
   * @param progress 进度
   */
  void onProgress(int progress);

  /**
   * 下载成功
   */
  void onSuccess();

  /**
   * 下载失败
   */
  void onFailed();

  /**
   * 下载暂停
   */
  void onPaused();

  /**
   * 下载取消
   */
  void onCanceled();

}
