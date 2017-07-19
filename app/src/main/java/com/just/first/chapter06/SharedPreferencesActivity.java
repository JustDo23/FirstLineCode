package com.just.first.chapter06;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

/**
 * 6.3.1 SharedPreferences 存储
 *
 * @author JustDo23
 * @since 2017年07月19日
 */
public class SharedPreferencesActivity extends BaseActivity {

  private EditText et_content;
  private TextView tv_content;
  private String keyWord;
  private String fileName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shared_preferences);
    et_content = (EditText) findViewById(R.id.et_content);
    tv_content = (TextView) findViewById(R.id.tv_content);
    fileName = "data";
    keyWord = "just";
    String content = loadFromSharedPreferences(fileName, keyWord);
    if (!TextUtils.isEmpty(content)) {
      et_content.setText(content);
      et_content.setSelection(content.length());
      ToastUtil.showShortToast(this, "Load Success.");
    }
  }


  public void put(View view) {
    String content = et_content.getText().toString();
    if (!TextUtils.isEmpty(content)) {
      saveToSharedPreferences(fileName, keyWord, content);
    } else {
      ToastUtil.showShortToast(this, "please input some text.");
    }
  }

  public void get(View view) {
    String content = loadFromSharedPreferences(fileName, keyWord);
    if (!TextUtils.isEmpty(content)) {
      tv_content.setText(content);
    } else {
      ToastUtil.showShortToast(this, "There is nothing.");
    }
  }


  /**
   * 将数据保存到 SharedPreferences
   *
   * @param fileName 文件名
   * @param keyWord  键
   * @param saveData 值
   */
  private void saveToSharedPreferences(String fileName, String keyWord, String saveData) {
    // this.getPreferences(MODE_APPEND);
    // PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences sharedPreferences = this.getSharedPreferences(fileName, MODE_APPEND);
    SharedPreferences.Editor edit = sharedPreferences.edit();
    edit.putString(keyWord, saveData);
    edit.apply();
  }

  /**
   * 从 SharedPreferences 加载数据
   *
   * @param fileName 文件名
   * @param keyWord  键
   * @return 值
   */
  private String loadFromSharedPreferences(String fileName, String keyWord) {
    SharedPreferences sharedPreferences = this.getSharedPreferences(fileName, MODE_APPEND);
    return sharedPreferences.getString(keyWord, null);// 默认值
  }


}
