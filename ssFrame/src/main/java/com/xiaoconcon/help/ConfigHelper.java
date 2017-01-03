package com.xiaoconcon.help;

import java.util.Properties;

import com.xiaoconcon.constant.ConfigConstant;
import com.xiaoconcon.util.PropsUtil;
/**
 * 
    * @ClassName: ConfigHelp
    * @Description: �����ļ���ȡ������
    * @author mike x c Liu
    * @date 2016��10��29��
    *
 */
public final class ConfigHelper {
  private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
  
  public static String getJdbcUrl(){
	  return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
  }
  
  public static String getJdbcDriver(){
	  return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
  }
  
  public static String getJdbcUsername(){
	  return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNSME);
  }
  
  public static String getJdbcPassword(){
	  return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
  }
  
  public static String getAppBasePackage(){
	  return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
  }
  
  public static String getAppJspPath(){
	  return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH);
  }
  
  public static String getAppAssetPath(){
	  return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH);
  }
}
