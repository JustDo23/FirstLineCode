package com.just.first.chapter14.weather;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.chapter14.db.City;
import com.just.first.chapter14.db.County;
import com.just.first.chapter14.db.Province;
import com.just.first.chapter14.net.HttpUtils;
import com.just.first.chapter14.utils.JsonParseUtil;
import com.just.first.utils.ToastUtil;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 选择地区的 Fragment
 *
 * @author JustDo23
 * @since 2017年08月25日
 */
public class ChooseAreaFragment extends Fragment {

  public static final int LEVEL_PROVINCE = 0;// 省
  public static final int LEVEL_CITY = 1;// 市
  public static final int LEVEL_COUNTY = 2;// 县

  private View rootView;// 根布局
  private ProgressDialog progressDialog;// 加载框
  private TextView tv_show_title;// 标题
  private ImageView iv_show_back;// 返回按钮
  private ListView lv_area;// 地区列表

  private ArrayAdapter<String> adapter;// 适配器
  private List<String> areaList = new ArrayList<>();// 地区集合

  private List<Province> provinceList;// 省份集合
  private List<City> cityList;// 城市集合
  private List<County> countyList;// 县区集合

  private Province selectProvince;// 选中的省份
  private City selectCity;// 选中的城市
  private County selectCounty;// 选中的县区

  private int currentLevel;// 当前的级别

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.weather_choose_area_fragment, container, false);
    findViews();
    initAdapter();
    return rootView;
  }

  private void findViews() {
    tv_show_title = (TextView) rootView.findViewById(R.id.tv_show_title);
    iv_show_back = (ImageView) rootView.findViewById(R.id.iv_show_back);
    lv_area = (ListView) rootView.findViewById(R.id.lv_area);
  }

  private void initAdapter() {
    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, areaList);
    lv_area.setAdapter(adapter);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    lv_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (currentLevel) {
          case LEVEL_PROVINCE:
            selectProvince = provinceList.get(position);
            queryCities();
            break;
          case LEVEL_CITY:
            selectCity = cityList.get(position);
            queryCounties();
            break;
          case LEVEL_COUNTY:
            selectCounty = countyList.get(position);
            break;
        }
      }
    });
    iv_show_back.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        switch (currentLevel) {
          case LEVEL_PROVINCE:
            break;
          case LEVEL_CITY:
            queryProvinces();
            break;
          case LEVEL_COUNTY:
            queryCities();
            break;
        }
      }
    });
    queryProvinces();
  }

  /**
   * 查询省-先数据库-后网络
   */
  private void queryProvinces() {
    tv_show_title.setText("中国");
    iv_show_back.setVisibility(View.GONE);
    provinceList = DataSupport.findAll(Province.class);
    if (provinceList.size() > 0) {// 本地缓存
      areaList.clear();
      for (Province province : provinceList) {
        areaList.add(province.getProvinceName());
      }
      adapter.notifyDataSetChanged();
      lv_area.setSelection(0);
      currentLevel = LEVEL_PROVINCE;
    } else {// 网络获取
      String address = "http://guolin.tech/api/china";
      queryFromServer(address, "province");
    }
  }

  /**
   * 查询城市-先数据库-后网络
   */
  private void queryCities() {
    tv_show_title.setText(selectProvince.getProvinceName());
    iv_show_back.setVisibility(View.VISIBLE);
    cityList = DataSupport.where("provinceId = ?", String.valueOf(selectProvince.getId())).find(City.class);
    if (cityList.size() > 0) {// 本地缓存
      areaList.clear();
      for (City city : cityList) {
        areaList.add(city.getCityName());
      }
      adapter.notifyDataSetChanged();
      lv_area.setSelection(0);
      currentLevel = LEVEL_CITY;
    } else {// 网络获取
      int provinceCode = selectProvince.getProvinceCode();
      String address = "http://guolin.tech/api/china" + "/" + provinceCode;
      queryFromServer(address, "city");
    }
  }

  /**
   * 查询城市-先数据库-后网络
   */
  private void queryCounties() {
    tv_show_title.setText(selectCity.getCityName());
    iv_show_back.setVisibility(View.VISIBLE);
    countyList = DataSupport.where("cityId = ?", String.valueOf(selectCity.getId())).find(County.class);
    if (countyList.size() > 0) {// 本地缓存
      areaList.clear();
      for (County county : countyList) {
        areaList.add(county.getCountyName());
      }
      adapter.notifyDataSetChanged();
      lv_area.setSelection(0);
      currentLevel = LEVEL_COUNTY;
    } else {// 网络获取
      int provinceCode = selectProvince.getProvinceCode();
      int cityCode = selectCity.getCityCode();
      String address = "http://guolin.tech/api/china" + "/" + provinceCode + "/" + cityCode;
      queryFromServer(address, "county");
    }
  }

  /**
   * 网络获取数据
   *
   * @param address 网络地址
   * @param type    类型
   */
  private void queryFromServer(String address, String type) {
    showProgressDialog();
    HttpUtils.sendOkHttpRequest(address, new Callback() {

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        String responseText = response.body().string();
        boolean result = false;
        switch (type) {
          case "province":
            result = JsonParseUtil.handleProvinceResponse(responseText);
            break;
          case "city":
            result = JsonParseUtil.handleCityResponse(responseText, selectProvince.getId());
            break;
          case "county":
            result = JsonParseUtil.handleCountyResponse(responseText, selectCity.getId());
            break;
        }
        if (result) {
          getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
              closeProgressDialog();
              switch (type) {
                case "province":
                  queryProvinces();
                  break;
                case "city":
                  queryCities();
                  break;
                case "county":
                  queryCounties();
                  break;
              }
            }
          });
        }
      }

      @Override
      public void onFailure(Call call, IOException e) {
        getActivity().runOnUiThread(new Runnable() {

          @Override
          public void run() {
            closeProgressDialog();
            ToastUtil.showShortToast(getActivity(), "加载失败");
          }
        });
      }
    });
  }

  /**
   * 显示加载弹窗
   */
  private void showProgressDialog() {
    if (progressDialog == null) {
      progressDialog = new ProgressDialog(getActivity());
      progressDialog.setMessage("正在加载...");
      progressDialog.setCancelable(false);
    }
    progressDialog.show();
  }

  /**
   * 关闭加载弹窗
   */
  private void closeProgressDialog() {
    progressDialog.dismiss();
  }

}
