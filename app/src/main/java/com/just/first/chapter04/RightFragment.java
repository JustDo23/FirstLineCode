package com.just.first.chapter04;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.just.first.R;
import com.just.first.utils.LogUtils;

/**
 * 4.2.1 Fragment 的简单用法
 *
 * @author JustDo23
 * @since 2017年05月16日
 */
public class RightFragment extends Fragment {

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    LogUtils.e("RightFragment --> onAttach()");
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogUtils.e("RightFragment --> onCreate()");
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    LogUtils.e("RightFragment --> onCreateView()");
    View rootView = inflater.inflate(R.layout.fragment_simple_right, container, false);
    return rootView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    LogUtils.e("RightFragment --> onActivityCreated()");
  }

  @Override
  public void onStart() {
    super.onStart();
    LogUtils.e("RightFragment --> onStart()");
  }

  @Override
  public void onResume() {
    super.onResume();
    LogUtils.e("RightFragment --> onResume()");
  }

  @Override
  public void onPause() {
    super.onPause();
    LogUtils.e("RightFragment --> onPause()");
  }

  @Override
  public void onStop() {
    super.onStop();
    LogUtils.e("RightFragment --> onStop()");
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    LogUtils.e("RightFragment --> onDestroyView()");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    LogUtils.e("RightFragment --> onDestroy()");
  }

  @Override
  public void onDetach() {
    super.onDetach();
    LogUtils.e("RightFragment --> onDetach()");
  }
}
