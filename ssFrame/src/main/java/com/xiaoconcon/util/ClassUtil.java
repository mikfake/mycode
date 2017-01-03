package com.xiaoconcon.util;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
    * @ClassName: ClassUtil
    * @Description: 类加载器
    * @author mike x c Liu
    * @date 2016年10月29日
    *
 */
public final class ClassUtil {
   private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);
   /**
    * 
       * @Title: getClassLoader
       * @Description: 获取类加载器
       * @param @return    参数
       * @return ClassLoader    返回类型
       * @throws
    */
   public static ClassLoader getClassLoader(){
	   return Thread.currentThread().getContextClassLoader();
   }
   /**
    * 
       * @Title: loadClass
       * @Description: 根据指定类名去加载类
       * @param @param className
       * @param @param isInint
       * @param @return    参数
       * @return Class<?>    返回类型
       * @throws
    */
   public static Class<?> loadClass(String className,boolean isInint){
	   Class<?> cls = null;
	   try {
		cls = Class.forName(className, isInint, getClassLoader());
	} catch (ClassNotFoundException e) {
		log.error("load class failure",e);
	}
	   return cls;
   }
   /**
    * 
       * @Title: getClassSet
       * @Description: 加载指定包名下的所有类
       * @param @param packageName
       * @param @return    参数
       * @return Set<Class<?>>    返回类型
       * @throws
    */
   public static Set<Class<?>> getClassSet(String packageName){
	   Set<Class<?>> set = new HashSet<Class<?>>();
	   try {
		Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
	    while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			if (url != null) {
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					String packagePath = url.getPath().replaceAll("%20", "");
					addClass(set, packagePath, packageName);
				} else if ("jar".equals(protocol)) {
					JarURLConnection jarurlConn = (JarURLConnection) url.openConnection();
					if (jarurlConn != null) {
						JarFile jarFile = jarurlConn.getJarFile();
						if(jarFile!=null){
							Enumeration<JarEntry>  jarEntrys = jarFile.entries();
							while (jarEntrys.hasMoreElements()) {
								JarEntry jarEntry = (JarEntry) jarEntrys.nextElement();
								String jarEntryName = jarEntry.getName();
								if (jarEntryName.endsWith(".class")){
									String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
									doAddClass(set, className);
								}
							}
						}
					}
				}
			}
		}
	   
	   
	   } catch (Exception e) {
		log.error("load class set failure",e);
		throw new RuntimeException(e);
	}
	   return set;
   }
   
   public static void doAddClass (Set<Class<?>> set, String className){
	   Class<?> cls =loadClass(className, false);
	   
	   set.add(cls);
   }
   
   public static void addClass (Set<Class<?>> set, String packagePath, String packageName){
	   File [] files = new File(packagePath).listFiles(new FileFilter() {
		public boolean accept(File file) {
			return (file.isFile()&&file.getName().endsWith(".class"))||file.isDirectory();
		}
	});
	   for (File file : files) {
		String fileName= file.getName();
		if(file.isFile()){
			String className = fileName.substring(0,fileName.lastIndexOf("."));
			if (StringUtils.isNotEmpty(packageName)) {
				className = packageName + "." + className;
			}
			doAddClass(set, className);
		} else{
			String subPackagePath = fileName;
			if (StringUtils.isNotEmpty(packagePath)) {
				subPackagePath = packagePath +"/" + subPackagePath;
			}
			String subPackageName = fileName;
			if (StringUtils.isNotEmpty(packageName)) {
				subPackageName = packageName + "." + subPackageName;
			}
			addClass(set, subPackagePath, subPackageName);
		}
	}
   }
}
