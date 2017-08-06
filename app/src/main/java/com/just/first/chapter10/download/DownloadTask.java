package com.just.first.chapter10.download;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载任务
 *
 * @author JustDo23
 * @since 2017年08月05日
 */
public class DownloadTask extends AsyncTask<String, Integer, Integer> {

  public static final int TYPE_SUCCESS = 0;// 成功
  public static final int TYPE_FAILED = 1;// 失败
  public static final int TYPE_PAUSED = 2;// 暂停
  public static final int TYPE_CANCELED = 3;// 取消

  private DownloadListener downloadListener;
  private boolean isPaused;
  private boolean isCanceled;
  private int lastProgress;

  public DownloadTask(DownloadListener downloadListener) {
    this.downloadListener = downloadListener;
  }

  @Override
  protected Integer doInBackground(String... params) {
    File file = null;
    InputStream inputStream = null;
    RandomAccessFile saveFile = null;
    try {
      String downloadUrl = params[0];// 下载路径
      long contentLength = getContentLength(downloadUrl);// 获取文件总大小
      long downloadLength = 0;// 记录已经下载的大小
      String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));// 将下载文件的名字
      String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();// SD 卡上的下载路径
      file = new File(directory, fileName);// 根据路径和名字创建文件
      if (file.exists()) {// 文件存在
        downloadLength = file.length();// 获取当前下载文件大小
      }
      if (contentLength == 0) {// 将下载总大小为 0
        return TYPE_FAILED;// 返回失败
      }
      if (contentLength == downloadLength) {// 将下载总大小 == 已下载大小
        return TYPE_SUCCESS;// 返回成功
      }
      OkHttpClient okHttpClient = new OkHttpClient();// OkHttp 客户端
      Request request = new Request.Builder()
          .addHeader("RANGE", "bytes=" + downloadLength + "-")// 断点续传
          .url(downloadUrl)
          .build();
      Response response = okHttpClient.newCall(request).execute();// 执行网络请求
      if (response != null && response.isSuccessful()) {
        inputStream = response.body().byteStream();// 获取输入流
        saveFile = new RandomAccessFile(file, "rw");// 保存文件
        saveFile.seek(downloadLength);// 跳过已经下载的字节
        byte[] readBytes = new byte[1024];// 一次读取字节数
        int total = 0;// 已下载总大小
        int length;// 当前读取大小
        while ((length = inputStream.read(readBytes)) != -1) {// 还可以读取
          if (isPaused) {// 暂停
            return TYPE_PAUSED;// 返回暂停
          } else if (isCanceled) {// 取消
            return TYPE_CANCELED;// 返回取消
          } else {// 下载状态
            total += length;// 已下载总大小
            saveFile.write(readBytes, 0, length);// 写入文件
            int progress = (int) ((total + downloadLength) * 100.0f / contentLength);// 计算下载进度百分比
            publishProgress(progress);// 更新界面下载进度
          }
        }
        response.body().close();// 流关闭
        return TYPE_SUCCESS;// 返回下载成功
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (inputStream != null) {
          inputStream.close();
        }
        if (saveFile != null) {
          saveFile.close();
        }
        if (isCanceled && file != null) {// 取消下载
          file.delete();// 删除文件
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return TYPE_FAILED;
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    int progress = values[0];// 获取当前进度
    if (progress > lastProgress) {// 当前进度 > 上次进度
      lastProgress = progress;// 更新上次进度
      if (downloadListener != null) {
        downloadListener.onProgress(lastProgress);// 进行回调
      }
    }
  }

  @Override
  protected void onPostExecute(Integer status) {
    if (downloadListener != null) {
      switch (status) {// 后台任务执行结果
        case TYPE_SUCCESS:
          downloadListener.onSuccess();
          break;
        case TYPE_FAILED:
          downloadListener.onFailed();
          break;
        case TYPE_PAUSED:
          downloadListener.onPaused();
          break;
        case TYPE_CANCELED:
          downloadListener.onCanceled();
          break;
      }
    }
  }

  /**
   * 获取将下载文件的总大小
   *
   * @param downloadUrl
   * @return
   * @throws IOException
   */
  private long getContentLength(String downloadUrl) throws IOException {
    OkHttpClient okHttpClient = new OkHttpClient();// OkHttp 客户端
    Request request = new Request.Builder()
        .url(downloadUrl)
        .build();
    Response response = okHttpClient.newCall(request).execute();// 执行网络请求
    if (response != null && response.isSuccessful()) {// 请求成功
      long contentLength = response.body().contentLength();// 总大小
      response.body().close();// 将流关闭
      return contentLength;
    }
    return 0;
  }

  /**
   * 暂停下载
   */
  public void pauseDownload() {
    isPaused = true;
  }

  /**
   * 取消下载
   */
  public void cancelDownload() {
    isCanceled = true;
  }

}
