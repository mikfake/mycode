package com.xiaoconcon.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

public class ProxyChain {
  private final Class<?> targetClass;
  private final Object targetObject;
  private final Method targetMethod;
  private final MethodProxy methodProxy;
  private final Object[] methodParams;
  private List<Proxy> proxyList = new ArrayList<Proxy>();
  private int proxyIndex = 0;
public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy,
		Object[] methodParams, List<Proxy> proxyList, int proxyIndex) {
	super();
	this.targetClass = targetClass;
	this.targetObject = targetObject;
	this.targetMethod = targetMethod;
	this.methodProxy = methodProxy;
	this.methodParams = methodParams;
	this.proxyList = proxyList;
	this.proxyIndex = proxyIndex;
}
  
  
}
