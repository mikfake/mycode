package com.xiaoconcon.help;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.xiaoconcon.annotation.AutoInject;
import com.xiaoconcon.util.ReflectionUtil;



public final class IocHelper {
  static {
	  Map<Class<?>,Object> beanMap =BeanHelper.getBeanMap();
	  if(null!=beanMap&&beanMap.size()>0){
		  for (Map.Entry<Class<?>, Object> entrys : beanMap.entrySet()) {
			  Class<?> beanClass = entrys.getKey();
			  Object baenInstance = entrys.getValue();
			  Field [] fields = beanClass.getFields();
			  if(fields!=null&&fields.length>0){
				  for (Field field : fields) {
					if (field.isAnnotationPresent(AutoInject.class)) {
						Class<?> injectBeanClass = field.getType();
						Object injectBean = beanMap.get(injectBeanClass);
						if (injectBean!=null) {
							ReflectionUtil.setField(baenInstance, field, injectBean);
						}
					}
				}
			  }
		}
	  }
	  
	  
	  
	  
	  
	  
	  
  }
}
