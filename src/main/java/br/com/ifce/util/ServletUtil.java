package br.com.ifce.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ServletUtil {
	public static void writeXML(HttpServletResponse resp, String xml) throws IOException{
		if(xml !=null){
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/xml;charset=UTF-8");
			writer.write(xml);
			writer.close();	
		}
	}
	
	public static void writeJson(HttpServletResponse resp, String json) throws IOException{
		if(json !=null){
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json;charset=UTF-8");
			writer.write(json);
			writer.close();	
		}
	}
}
