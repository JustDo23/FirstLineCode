package com.just.first.chapter14.weather;

import android.os.Bundle;

import com.just.first.R;
import com.just.first.base.BaseActivity;
import com.just.first.chapter14.db.AreaDao;

/**
 * 选择地区的 Activity
 *
 * @author JustDo23
 * @since 2017年08月26日
 */
public class ChooseAreaActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.weather_choose_area_activity);
    new AreaDao().createDB();
  }

}
