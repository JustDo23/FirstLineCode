package com.just.first.chapter06;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 6.2.1 文件存储
 *
 * @author JustDo23
 * @since 2017年07月19日
 */
public class FileStoreActivity extends BaseActivity {

  private EditText et_content;
  private TextView tv_content;
  private String fileName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_file_store);
    et_content = (EditText) findViewById(R.id.et_content);
    tv_content = (TextView) findViewById(R.id.tv_content);
    fileName = "data";
    String content = loadFromFile(fileName);
    if (!TextUtils.isEmpty(content)) {
      et_content.setText(content);
      et_content.setSelection(content.length());
      ToastUtil.showShortToast(this, "Load Success.");
    }
  }

  public void save(View view) {
    String content = et_content.getText().toString();
    if (!TextUtils.isEmpty(content)) {
      saveToFile(fileName, content);
    } else {
      ToastUtil.showShortToast(this, "please input some text.");
    }
  }

  public void load(View view) {
    String content = loadFromFile(fileName);
    if (!TextUtils.isEmpty(content)) {
      tv_content.setText(content);
    } else {
      ToastUtil.showShortToast(this, "There is nothing.");
    }
  }


  /**
   * 保存数据到内部存储文件
   *
   * @param fileName 文件名称
   * @param saveData 写入的数据
   */
  private void saveToFile(String fileName, String saveData) {
    FileOutputStream fileOutputStream = null;
    BufferedWriter bufferedWriter = null;
    try {
      fileOutputStream = this.openFileOutput(fileName, Context.MODE_APPEND);// [/data/data/com.just.first/files]
      bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
      bufferedWriter.write(saveData);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (bufferedWriter != null) {
          bufferedWriter.close();
        }
        if (fileOutputStream != null) {
          fileOutputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 从内部存储文件中读取数据
   *
   * @param fileName 文件名称
   * @return 文件内容
   */
  private String loadFromFile(String fileName) {
    FileInputStream fileInputStream = null;
    BufferedReader bufferedReader = null;
    StringBuilder dataContent = new StringBuilder();
    try {
      fileInputStream = this.openFileInput(fileName);
      bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
      String line = "";
      while ((line = bufferedReader.readLine()) != null) {
        dataContent.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (bufferedReader != null) {
          bufferedReader.close();
        }
        if (fileInputStream != null) {
          fileInputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return dataContent.toString();
  }

}
