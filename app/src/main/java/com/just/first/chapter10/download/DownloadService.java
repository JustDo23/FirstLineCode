package com.just.first.chapter10.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.just.first.R;
import com.just.first.main.MainActivity;
import com.just.first.utils.ToastUtil;

import java.io.File;

/**
 * 下载服务
 *
 * @author JustDo23
 * @since 2017年08月05日
 */
public class DownloadService extends Service {

  private int notificationID = 12;// 通知的 ID

  private String downloadUrl;// 下载路径

  private DownloadTask downloadTask;// 下载后台任务

  private DownloadListener downloadListener = new DownloadListener() {// 下载状态的监听

    @Override
    public void onProgress(int progress) {
      getNotificationManager().notify(notificationID, getNotification("Download...", progress));// 更新通知栏进度
    }

    @Override
    public void onSuccess() {
      downloadTask = null;// 置空
      stopForeground(true);// 停止前台服务取消通知栏
      getNotificationManager().notify(notificationID, getNotification("Download Success", -1));// 通知栏提示成功
      ToastUtil.showShortToast(DownloadService.this, "Download Success");
    }

    @Override
    public void onFailed() {
      downloadTask = null;
      stopForeground(true);// 停止前台服务取消通知栏
      getNotificationManager().notify(notificationID, getNotification("Download Failed", -1));// 通知栏提示失败
      ToastUtil.showShortToast(DownloadService.this, "Download Failed");
    }

    @Override
    public void onPaused() {
      downloadTask = null;
      ToastUtil.showShortToast(DownloadService.this, "Download Paused");
    }

    @Override
    public void onCanceled() {
      downloadTask = null;
      stopForeground(true);// 停止前台服务取消通知栏
      ToastUtil.showShortToast(DownloadService.this, "Download Canceled");
    }
  };

  private DownloadBinder downloadBinder = new DownloadBinder();

  @Override
  public IBinder onBind(Intent intent) {
    return downloadBinder;
  }

  /**
   * 绑定对象
   *
   * @since 2017年08月05日
   */
  class DownloadBinder extends Binder {

    /**
     * 开始下载
     *
     * @param url 下载地址
     */
    public void startDownload(String url) {
      if (downloadTask == null) {
        downloadUrl = url;// 下载地址
        downloadTask = new DownloadTask(downloadListener);// 实例化任务
        downloadTask.execute(downloadUrl);// 进行下载
        startForeground(notificationID, getNotification("Downloading...", 0));// 设置前台
        ToastUtil.showShortToast(DownloadService.this, "Start Download");
      }
    }

    /**
     * 暂停下载
     */
    public void pauseDownload() {
      if (downloadTask != null) {
        downloadTask.pauseDownload();// 任务暂停
      }
    }

    /**
     * 取消下载
     */
    public void cancelDownload() {
      if (downloadTask != null) {
        downloadTask.cancelDownload();// 任务取消
      } else {
        if (downloadUrl != null) {
          String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));// 将下载文件的名字
          String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();// SD 卡上的下载路径
          File file = new File(directory, fileName);// 根据路径和名字创建文件
          if (file.exists()) {// 文件存在
            file.delete();// 删除文件
          }
        }
        getNotificationManager().cancel(notificationID);// 取消通知
        stopForeground(true);// 停止前台
        ToastUtil.showShortToast(DownloadService.this, "Canceled Download");
      }
    }

  }

  /**
   * 获取通知管理对象
   *
   * @return 通知管理对象
   */
  private NotificationManager getNotificationManager() {
    return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
  }

  /**
   * 获取通知对象
   *
   * @param title    通知标题
   * @param progress 下载进度
   * @return 通知对象
   */
  private Notification getNotification(String title, int progress) {
    Intent intent = new Intent(this, MainActivity.class);// 意图指定界面
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);// 延迟意图
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);// 构建器对象
    builder.setSmallIcon(R.mipmap.ic_launcher);// 小图标
    builder.setContentIntent(pendingIntent);// 延时意图
    builder.setContentTitle(title);// 标题
    if (progress > 0) {// 有进度
      builder.setContentText(progress + "%");// 显示内容
      builder.setProgress(100, progress, false);// 进度[最后参数是否使用模糊进度条]
    }
    Notification notification = builder.build();// 通知对象
    return notification;
  }

}
