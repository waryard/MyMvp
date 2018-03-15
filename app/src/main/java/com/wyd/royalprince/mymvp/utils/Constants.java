package com.wyd.royalprince.mymvp.utils;


public interface Constants {
  public static final String HOME_TITLE = "MyApp";
  public static boolean DEBUG = true;
  public static String RELEASE_HOST = "http://gank.io/api";    //云服务地址 生产地址H
public static String DEBUG_HOST = "http://gank.io/api"; // 默认的测试服务器地址
  public static String HOST = DEBUG ? DEBUG_HOST : RELEASE_HOST;

}
