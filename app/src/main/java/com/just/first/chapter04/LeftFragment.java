package com.just.first.chapter04;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.just.first.R;

/**
 * 4.2.1 Fragment 的简单用法
 *
 * @author JustDo23
 * @since 2017年05月16日
 */
public class LeftFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_simple_left, container, false);
    return rootView;
  }
}
