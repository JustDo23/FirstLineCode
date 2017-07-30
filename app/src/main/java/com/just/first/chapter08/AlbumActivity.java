package com.just.first.chapter08;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

/**
 * 8.3.2 调用手机相册选取图片
 *
 * @author JustDo23
 * @since 2017年07月30日
 */
public class AlbumActivity extends BaseActivity {

  private ImageView iv_photo;
  public static final int CHOOSE_ALBUM = 24;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_album);
    iv_photo = (ImageView) findViewById(R.id.iv_photo);
  }

  /**
   * 点击按钮从手机相册中选取
   */
  public void chooseAlbum(View view) {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {// 检查是否有权限
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 25);// 没有权限进行申请权限
    } else {
      openAlbum();// 有权限则打开相册
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case 25:
        if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          openAlbum();// 有权限则打开相册
        } else {
          ToastUtil.showShortToast(this, "You denied the permission.");
        }
        break;
    }
  }

  private void openAlbum() {
    Intent intent = new Intent("android.intent.action.GET_CONTENT");// 指定 action
    intent.setType("image/*");// 指定类型
    startActivityForResult(intent, CHOOSE_ALBUM);// 通过 Intent 打开相册
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case CHOOSE_ALBUM:
        if (RESULT_OK == resultCode) {// 正常的返回码
          if (Build.VERSION.SDK_INT >= 19) {// Android 4.4 及以上版本
            HandleImageOnKitKat(data);
          } else {// Android 4.4 以下版本
            HandleImageBeforeKitKat(data);
          }
        }
        break;
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  private void HandleImageOnKitKat(Intent data) {
    String imagePath = null;// 图片路径
    Uri uri = data.getData();// 获取 Uri 对象
    if (DocumentsContract.isDocumentUri(this, uri)) {//  如果是 Document 类型的 Uri
      String documentId = DocumentsContract.getDocumentId(uri);
      if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
        String id = documentId.split(":")[1];// 解析出数字格式的 id
        String selection = MediaStore.Images.Media._ID + "=" + id;
        imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
      } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
        Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
        imagePath = getImagePath(contentUri, null);
      }
    } else if ("content".equalsIgnoreCase(uri.getScheme())) {//  如果是 content 类型的 Uri
      imagePath = getImagePath(uri, null);
    } else if ("file".equalsIgnoreCase(uri.getScheme())) {//  如果是 file 类型的 Uri
      imagePath = uri.getPath();
    }
    displayImage(imagePath);// 文件路径进行图片展示
  }

  private void HandleImageBeforeKitKat(Intent data) {
    Uri uri = data.getData();
    String imagePath = getImagePath(uri, null);
    displayImage(imagePath);
  }

  private String getImagePath(Uri uri, String selection) {
    String path = null;// 通过内容提供者获取图片路径
    Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
    if (cursor != null) {
      if (cursor.moveToFirst()) {// 拿出第一条数据
        path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
      }
      cursor.close();// 游标用完要关闭
    }
    return path;
  }

  private void displayImage(String imagePath) {
    if (!TextUtils.isEmpty(imagePath)) {// 注意内存溢出
      Bitmap bitmap = BitmapFactory.decodeFile(imagePath);// 利用工厂类从路径加载出图片
      iv_photo.setImageBitmap(bitmap);// 显示图片
    } else {
      ToastUtil.showShortToast(this, "Failed to get image.");
    }
  }
}
