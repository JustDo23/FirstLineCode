package com.just.first.chapter07;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 7.3.2 读取系统联系人
 *
 * @author JustDo23
 * @since 2017年07月23日
 */
public class ContactsActivity extends BaseActivity {

  private ListView lv_contacts;
  private ArrayAdapter<String> arrayAdapter;// 适配器
  private List<String> contactList = new ArrayList<>();// 联系人集合

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contacts);
    lv_contacts = (ListView) findViewById(R.id.lv_contacts);
    arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);
    lv_contacts.setAdapter(arrayAdapter);
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {// 判断权限
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 23);
    } else {
      readContacts();
    }
  }

  private void readContacts() {
    Cursor cursor = null;// 游标对象
    try {
      cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
      if (cursor != null) {
        while (cursor.moveToNext()) {// 循环读取数据
          String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));// 姓名
          String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));// 手机号
          contactList.add(displayName + "\n" + number);
        }
        arrayAdapter.notifyDataSetChanged();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (cursor != null) {
        cursor.close();// 关闭游标
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case 23:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          readContacts();
        } else {
          ToastUtil.showShortToast(this, "You denied the permission");
        }
        break;
    }
  }

}
