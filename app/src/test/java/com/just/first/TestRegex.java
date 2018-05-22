package com.just.first;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试类
 *
 * @author JustDo23
 */
public class TestRegex {

  @Test
  public void preRegex() throws Exception {
    String preRegex = "(加|微|Q|折|课|)";
    boolean result = isMatch(preRegex, "加微信");
    String realString = "测试替换".replace("<span>", "");
    Assert.assertEquals(true, result);
  }

  @Test
  public void testRegex() throws Exception {
    List<String> regexList = new ArrayList<>();
    regexList.add("((加|力.*口).*(d.*|w.*))|[①②③④⑤⑥⑦⑧]");
    regexList.add("QQ");
    regexList.add("微.*信");
    regexList.add("QQ");
    regexList.add("折|扌.*斤");

    for (int i = 0; i < regexList.size(); i++) {
      boolean match = isMatch(regexList.get(i), "咨询课程请添加微信号 Wx2355dss");
      System.out.println("---> " + match);
      int x = 6 + i;
      x += i;
    }
    // Assert.assertEquals(true, isMatch(regexList.get(0), "加微信"));
  }


  /**
   * 判断是否匹配正则
   *
   * @param regex 正则表达式
   * @param input 要匹配的字符串
   * @return {@code true}: 匹配<br>{@code false}: 不匹配
   */
  private boolean isMatch(String regex, CharSequence input) {
    if (input != null && input.length() > 0) {
      Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
      Matcher m = p.matcher(input);
      return m.find();
    }
    return false;
  }

}
