package com.just.first.chapter08;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;

import com.just.first.R;
import com.just.first.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 8.3.1 调用摄像头拍照
 *
 * @author JustDo23
 * @since 2017年05月12日
 */
public class CameraActivity extends BaseActivity {

  private ImageView iv_photo;
  private Uri imageUri;// 获取一个 URI 对象

  public static final int TAKE_PHOTO = 23;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_camera);
    iv_photo = (ImageView) findViewById(R.id.iv_photo);
  }

  /**
   * 调用摄像头拍照
   */
  public void takePhoto(View view) {
    File imageFile = new File(getExternalCacheDir(), "image.jpg");// 指定文件的路径及名称
    if (imageFile.exists()) {
      imageFile.delete();// 文件存在就删除
    }
    try {
      imageFile.createNewFile();// 创建新的文件
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 内容提供者
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {// Android 7.0 进行适配
      imageUri = FileProvider.getUriForFile(this, "com.just.first.fileprovider", imageFile);// [上下文][任意一个唯一字符串][File对象]
    } else {// 这个 URI 标识者图片的本地真是路径
      imageUri = Uri.fromFile(imageFile);
    }
    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");// 创建意图并指定 Action
    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);// 携带参数
    startActivityForResult(intent, TAKE_PHOTO);// 启动意图
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {// 此方式中指定了拍照图片位置因此 data 为 null
      case TAKE_PHOTO:
        try {// 手机拍照图片一般3M左右因此处理图片内存溢出需要注意
          Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));// 从内容提供者中获取数据
          iv_photo.setImageBitmap(bitmap);// 进行图片的显示
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        break;
    }
  }
}
