package com.just.first.chapter09;

import com.just.first.utils.LogUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 9.3.2 SAX 解析方式
 *
 * @author JustDo23
 * @since 2017年08月01日
 */
public class SaxHandler extends DefaultHandler {

  private String nodeName;
  private StringBuilder id;
  private StringBuilder name;
  private StringBuilder version;

  /**
   * 开始解析文档
   *
   * @throws SAXException 异常
   */
  @Override
  public void startDocument() throws SAXException {
    super.startDocument();
    id = new StringBuilder();
    name = new StringBuilder();
    version = new StringBuilder();
  }

  /**
   * 开始解析节点
   *
   * @param uri        命名空间字符串[可能为空]
   * @param localName  节点名称[可能为空]
   * @param qName      限定名[可能为空]
   * @param attributes 属性
   * @throws SAXException 异常
   */
  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    super.startElement(uri, localName, qName, attributes);
    nodeName = localName;// 当前节点名称
  }

  /**
   * 获取节点内容[可能会调用多次，一些换行符也被当作内容解析出来]
   *
   * @param ch     字节数组
   * @param start  起始位置
   * @param length 有效长度
   * @throws SAXException
   */
  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    super.characters(ch, start, length);
    if ("id".equals(nodeName)) {
      id.append(ch, start, length);
    } else if ("name".equals(nodeName)) {
      name.append(ch, start, length);
    } else if ("version".equals(nodeName)) {
      version.append(ch, start, length);
    }
  }

  /**
   * 完成节点解析
   *
   * @param uri       命名空间字符串[可能为空]
   * @param localName 节点名称[可能为空]
   * @param qName     限定名[可能为空]
   * @throws SAXException 异常
   */
  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    super.endElement(uri, localName, qName);
    if ("app".equals(localName)) {
      LogUtils.e("id = " + id.toString().trim() + " name = " + name.toString().trim() + " version = " + version.toString().trim() + "\n");
      id.setLength(0);
      name.setLength(0);
      version.setLength(0);
    }
  }

  /**
   * 完成文档解析
   *
   * @throws SAXException 异常
   */
  @Override
  public void endDocument() throws SAXException {
    super.endDocument();
    LogUtils.e("SAX 解析结束");
  }

}
