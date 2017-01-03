package com.xiaoconcon.help;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.xiaoconcon.annotation.Action;
import com.xiaoconcon.http.Handler;
import com.xiaoconcon.http.Request;

public final class ControllerHelper {
  private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();
  static {
	  Set <Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
	  if (controllerClassSet!=null&& controllerClassSet.size()>0) {
		  for (Class<?> cls : controllerClassSet) {
			Method [] methods = cls.getMethods();
			if(methods!=null&&methods.length>0){
				for (Method method : methods) {
					if (method.isAnnotationPresent(Action.class)) {
						Action action = method.getAnnotation(Action.class);
						String mapping = action.value();
						if (mapping.matches("\\w+:/\\w*")) {
							String [] array = mapping.split(":");
							if(array!=null&&array.length==2){
								String requestMethod = array[0];
								String requestPath = array[1];
								Request request = new Request(requestMethod, requestPath);
								Handler handler = new Handler(cls, method);
								ACTION_MAP.put(request, handler);
							}
						}
					}
				}
			}
		}
	  }
	  
  }
  
  public static Handler getHandler(String requestMethod,String requestPath){
	  Request request = new Request(requestMethod, requestPath);
	  return ACTION_MAP.get(request);
  }
}
