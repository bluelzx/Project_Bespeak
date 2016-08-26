package com.lhfeiyu.action.back.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.OperationLog;
import com.lhfeiyu.service.domain.OperationLogService;
import com.lhfeiyu.tools.ImportExportActionUtil;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：操作日志 OperationLog <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.sys.BackOperationLogAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackOperationLogAction {
	
	@Autowired
	private OperationLogService  operationLogService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台操作日志页面
	 * @param modelMap
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/operationLog")
	public ModelAndView  operationLog(ModelMap modelMap, @RequestParam(required=false) Integer typeId){
		String path = LhPage.back_operationLog;
		try{
			modelMap.put("typeId", typeId);
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-OperationLog-PAGE-/back/page/operationLog-加载操作日志页面出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载数据字典列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getOperationLogList",method=RequestMethod.POST)
	public JSONObject getOperationLogList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			operationLogService.getOperationLogList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-OperationLog-AJAX-/back/getOperationLogList-加载操作日志列表出现异常", json);
		}
		return json;
	}
	
	/**
	 * 导出操作日志 
	 * @param request
	 * @param response
	 * @return null 直接写入response
	 */
	@ResponseBody
	@RequestMapping(value = "/exportOperationLog")
	public JSONObject exportOperationLog(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, Object> map = RequestUtil.getRequestParam(request);
			List<OperationLog> operationLogList =  operationLogService.selectListByCondition(map);
			String filename="操作日志导出数据";
			String columnsStr="id,username";
			String templateFileName="操作日志导出模板.xls";
			String labelFileName="操作日志导出数据";
	        ImportExportActionUtil.exportConsumableExcel(request, response, filename, columnsStr, templateFileName, labelFileName, operationLogList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/exportOperationLogTest",method=RequestMethod.GET)
	public JSONObject exportOperationLogTest(HttpServletRequest request,HttpServletResponse response){
		try {
			HashMap<String, Object> map = RequestUtil.getRequestParam(request);
			List<OperationLog> operationLogList =  operationLogService.selectListByCondition(map);
			String filename="操作日志导出数据";
			response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String((filename + ".xls").getBytes(), "iso-8859-1"));
			response.setBufferSize(1024);
			String columnsStr="id,username";
			String templateFileName="操作日志导出模板.xls";
			String labelFileName="操作日志导出数据";
			String path = request.getSession().getServletContext().getRealPath("/file/template/");
			ExportExcel ee = new ExportExcel();
			InputStream excelStream = ee.exportExcelTest(operationLogList, columnsStr, path, templateFileName, labelFileName,0,0);
			
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
	*/
}
