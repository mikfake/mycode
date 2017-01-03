package com.xiaoconcon.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.xiaoconcon.help.BeanHelper;
import com.xiaoconcon.help.ClassHelper;
import com.xiaoconcon.help.ConfigHelper;
import com.xiaoconcon.help.ControllerHelper;
import com.xiaoconcon.help.HelperLoad;
import com.xiaoconcon.util.CodecUtil;
import com.xiaoconcon.util.JsonUtil;
import com.xiaoconcon.util.ReflectionUtil;
import com.xiaoconcon.util.StreamUtil;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		HelperLoad.inint();
		ServletContext servletContext = servletConfig.getServletContext();
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 String requestMethod = request.getMethod().toLowerCase();
	 String requestPath = request.getPathInfo();
	 Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
	 if(handler!=null){
		 Class<?> controllerClass = handler.getControllerClass();
		 Object controllerBean = BeanHelper.getBean(controllerClass);
		 //get param map
		 Map<String,Object> paraMap = new HashMap<String,Object>();
		 Enumeration<String> paraNames = request.getParameterNames();
		 while (paraNames.hasMoreElements()) {
			String paraName = (String) paraNames.nextElement();
			String paraValue = request.getParameter(paraName);
			paraMap.put(paraName, paraValue);
	     }
		 String body =CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream())) ;
		 if(StringUtils.isNoneEmpty(body)){
			 String [] params = body.split("&");
			 if(ArrayUtils.isNotEmpty(params)){
				 for (String param : params) {
					String [] arrays = param.split("=");
					if(ArrayUtils.isNotEmpty(arrays)&&arrays.length==2){
						String paramName=arrays[0];
						String paramValue=arrays[1];
						paraMap.put(paramName, paramValue);
					}
				}
			 }
		 }
		 
		 Param param = new Param(paraMap);
		 Method actionMethod = handler.getActionMethod();
		 Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
	     if(result instanceof View){
	    	 View view =(View)result;
	    	 String path =view.getPath();
	    	 if(StringUtils.isNotEmpty(path)){
	    		 if(path.startsWith("/")){
	    			 response.sendRedirect(request.getContextPath()+path);
	    		 }else{
	    			 Map<String,Object> model = view.getModel();
	    			 for (Map.Entry<String,Object> entry : model.entrySet()) {
						request.setAttribute(entry.getKey(), entry.getValue());
					}
	    			 request.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(request, response);
	    		 }
	    	 }
	     }else if (result instanceof Data){
	    	 Data data =(Data)result;
	    	 Object model = data.getModel();
	    	 if(model != null){
	    		 response.setCharacterEncoding("utf-8");
	    		 response.setContentType("application/json");
	    		 PrintWriter pw= response.getWriter();
	    		 String json = JsonUtil.toJson(model);
	    		 pw.write(json);
	    		 pw.flush();
	    		 pw.close();
	    	 }
	     }
	 }
 }
}
