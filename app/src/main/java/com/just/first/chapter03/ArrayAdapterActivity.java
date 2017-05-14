package com.just.first.chapter03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.just.first.R;

/**
 * 3.5.1 ListView 的简单用法
 *
 * @author JustDo23
 * @since 2017年05月14日
 */
public class ArrayAdapterActivity extends AppCompatActivity {

  private String[] data = {"Apple", "Banana", "Orange", "Watermelon"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_array_adapter);
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
    ListView lv_simple = (ListView) findViewById(R.id.lv_simple);
    lv_simple.setAdapter(arrayAdapter);
  }
}
