package com.xiaoconcon.help;

import java.util.HashSet;
import java.util.Set;

import com.xiaoconcon.annotation.Controller;
import com.xiaoconcon.annotation.Service;
import com.xiaoconcon.util.ClassUtil;


/**
 * 
    * @ClassName: ClassHelper
    * @Description: ������İ�����
    * @author mike x c Liu
    * @date 2016��10��29��
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
       * @Description: ��ȡӦ�ð��µ�������
       * @param @return    ����
       * @return Set<Class<?>>    ��������
       * @throws
    */
   public static Set<Class<?>> getClassSet(){
	   return CLASS_SET;
   }
   /**
    * 
       * @Title: getServiceClassSet
       * @Description: ��ȡӦ�ð������е�service��
       * @param @return    ����
       * @return Set<Class<?>>    ��������
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
       * @Description: ��ȡӦ�ð������е�controller�� 
       * @param @return    ����
       * @return Set<Class<?>>    ��������
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
       * @Description: ��ȡӦ�ð�������bean
       * @param @return    ����
       * @return Set<Class<?>>    ��������
       * @throws
    */
   public static Set<Class<?>> getBeanClassSet(){
	   Set<Class<?>> set = new HashSet<Class<?>>();
	   set.addAll(getControllerClassSet());
	   set.addAll(getServiceClassSet());
	   return set;
   }
}
