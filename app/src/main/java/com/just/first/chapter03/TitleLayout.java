package com.just.first.chapter03;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.first.R;
import com.just.first.utils.ToastUtil;

/**
 * 3.4.2 创建自定义控件
 *
 * @author JustDo23
 * @since 2017年05月14日
 */
public class TitleLayout extends LinearLayout {

  private TextView tv_back;
  private TextView tv_title;
  private TextView tv_more;

  public TitleLayout(Context context) {
    this(context, null);
  }

  public TitleLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  /**
   * 进行初始化
   */
  private void init(Context context) {
    LayoutInflater.from(context).inflate(R.layout.inclue_title, this);// 注意：第二个参数
    tv_back = (TextView) findViewById(R.id.tv_back);
    tv_title = (TextView) findViewById(R.id.tv_title);
    tv_more = (TextView) findViewById(R.id.tv_more);
    tv_back.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        ((Activity) getContext()).finish();// 注意获取上并进行强制转换
      }
    });
    tv_more.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        ToastUtil.showShortToast(getContext(), "There is no more information.");
      }
    });
  }


}
