package com.lhfeiyu.tools;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhfeiyu.util.ExportExcel;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具包：导入导出 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.tools.ImportExportActionUtil <p>
 */
public class ImportExportActionUtil {
	
	/**
	 * Action通用导出方法，导出为xls文件</p>
	 * response.setContentType("application/vnd.ms-excel;charset=utf-8");
	 * response.setHeader("Content-Disposition", "attachment;filename="+ new String((filename + ".xls").getBytes(), "iso-8859-1"));
	 * String path = request.getSession().getServletContext().getRealPath("/file/template/");
	 * InputStream excelStream = ee.exportExcelTest(dataList, columnsStr, path, templateFileName, labelFileName,0,0);
	 * @param request
	 * @param response
	 * @param filename 生成文件的文件名称
	 * @param columnsStr 字段串，以逗号分隔
	 * @param templateFileName 模板文件名称，指定路径：/file/template/
	 * @param labelFileName label名称
	 * @param dataList 数据列表list
	 * @return null 直接写数据流写入response
	 */
	public static String exportConsumableExcel(HttpServletRequest request,HttpServletResponse response,
			String filename,String columnsStr,String templateFileName,String labelFileName,List<?> dataList){
		try {
			response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String((filename + ".xls").getBytes(), "iso-8859-1"));
			response.setBufferSize(1024);
			String path = request.getSession().getServletContext().getRealPath("/file/template/");
			ExportExcel ee = new ExportExcel();
			InputStream excelStream = ee.exportExcelTest(dataList, columnsStr, path, templateFileName, labelFileName,0,0);
			
			int i=excelStream.available(); 
	        byte data[]=new byte[i]; 
	        excelStream.read(data);  //读数据   
	        OutputStream ops = response.getOutputStream();
	        ops.write(data);
	        ops.flush();  
	        ops.close();   
	        excelStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
