package com.xiaoconcon.help;

import java.util.HashSet;
import java.util.Set;

import com.xiaoconcon.annotation.Controller;
import com.xiaoconcon.annotation.Service;
import com.xiaoconcon.util.ClassUtil;


/**
 * 
    * @ClassName: ClassHelper
    * @Description: 加载类的帮助类
    * @author mike x c Liu
    * @date 2016年10月29日
    *
 */
public final class ClassHelper {
   private static final Set<Class<?>> CLASS_SET;
   static {
	   String basePackage = ConfigHelper.getAppBasePackage();
	   CLASS_SET = ClassUtil.getClassSet(basePackage);
   }
   /**
    * 
       * @Title: getClassSet
       * @Description: 获取应用包下的所有类
       * @param @return    参数
       * @return Set<Class<?>>    返回类型
       * @throws
    */
   public static Set<Class<?>> getClassSet(){
	   return CLASS_SET;
   }
   /**
    * 
       * @Title: getServiceClassSet
       * @Description: 获取应用包下所有的service类
       * @param @return    参数
       * @return Set<Class<?>>    返回类型
       * @throws
    */
   public static Set<Class<?>> getServiceClassSet(){
	   Set<Class<?>> set = new HashSet<Class<?>>();
	   for (Class<?> clas : CLASS_SET) {
		if(clas.isAnnotationPresent(Service.class)){
			set.add(clas);
		}
	}
	   return set;
   }
   /**
    * 
       * @Title: getControllerClassSet
       * @Description: 获取应用包下所有的controller类 
       * @param @return    参数
       * @return Set<Class<?>>    返回类型
       * @throws
    */
   public static Set<Class<?>> getControllerClassSet(){
	   Set<Class<?>> set = new HashSet<Class<?>>();
	   for (Class<?> clas : CLASS_SET) {
		if(clas.isAnnotationPresent(Controller.class)){
			set.add(clas);
		}
	}
	   return set;
   }
   /**
    * 
       * @Title: getBeanClassSet
       * @Description: 获取应用包下所有bean
       * @param @return    参数
       * @return Set<Class<?>>    返回类型
       * @throws
    */
   public static Set<Class<?>> getBeanClassSet(){
	   Set<Class<?>> set = new HashSet<Class<?>>();
	   set.addAll(getControllerClassSet());
	   set.addAll(getServiceClassSet());
	   return set;
   }
}
