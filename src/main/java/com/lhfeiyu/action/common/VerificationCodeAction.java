package com.lhfeiyu.action.common;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lhfeiyu.util.loadingVerificationCodeUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-通用：验证码相关操作 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Controller
@RequestMapping(value="/common")
public class VerificationCodeAction {
	
	private static Logger logger = Logger.getLogger("R");
	
	/** 加载通用图形验证码 */
	@RequestMapping(value = "/loadVerificationCode", method = RequestMethod.GET)
	public String loadVerificationCode(HttpServletResponse response,HttpSession session) {
		BufferedImage bais = loadingVerificationCodeUtil.createImage();
	    response.setHeader("Pragma", "No-cache");//禁止缓存
	    response.setHeader("Cache-Control", "No-cache");
	    response.setDateHeader("Expires", 0);
	    response.setContentType("image/jpeg");//指定生成的响应是图片
	    session.removeAttribute("randomCode");//销毁验证码
	    session.setAttribute("randomCode",loadingVerificationCodeUtil.strCode);//把验证码存到session
	    try {
			ImageIO.write(bais,"JPEG",response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("LH_ERROR-加载验证码异常-VerificationCodeAction-/common/loadVerificationCode-"+e.getMessage());
		} 
	    return null;
	}
	
}
