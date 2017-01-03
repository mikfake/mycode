package com.xiaoconcon.help;

import com.xiaoconcon.util.ClassUtil;

public final class HelperLoad {
   public static void inint(){
	   Class<?> classList[] = {
			ClassHelper.class,
			BeanHelper.class,
			IocHelper.class,
			ControllerHelper.class
	   };
	   
	   for (Class<?> cls : classList) {
		  ClassUtil.loadClass(cls.getName(), false);
	   }
   }
}
