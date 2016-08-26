package com.lhfeiyu.action.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhfeiyu.thirdparty.wx.util.SignUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-通用：微信相关操作 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Controller
@RequestMapping(value="/back/base")
public class WxAction {
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 微信Token验证
	 * @param request
	 * @return String echostr or "failure"
	 */
    @ResponseBody
    @RequestMapping(value="/wxToken")
    public String wxToken(HttpServletRequest request) {
    	try {
    		String signature = request.getParameter("signature");  // 时间戳  
            String timestamp = request.getParameter("timestamp");  // 随机数  
            String nonce = request.getParameter("nonce");  // 随机字符串  
            String echostr = request.getParameter("echostr");  
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
                return echostr;  
            }  
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("LH_ERROR_微信Token验证出现异常_"+e.getMessage());
		}
    	return "failure";
    }
    
    /**
     * 微信获取openId回调地址
     * @param modelMap
     * @param session
     * @param code
     * @return
     */
    /*@RequestMapping(value="/wxRedirectUri")
	public ModelAndView wxRedirectUri(ModelMap modelMap,HttpSession session,
			@RequestParam String code){
		String path = PagePath.index;
		try{
			//	public static String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
//			+ "appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		//	public static String oauth2Url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
		//	+ "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
			
			
          String openId ="";
          if (!"authdeny".equals(code)) {
                  //WeiXinOauth2Token weiXinOauth2Token = WxUtil.getOauth2AccessToken("wx0953bae287adfeee","8e81dbc44a84a3c290c0cc3759f85421", code);
                  //openId = weiXinOauth2Token.getOpenId();
          }
          session.setAttribute("openId", openId);
		}catch(Exception e){
			e.printStackTrace();
			path = PagePath.error;
			logger.error("LH_ERROR_微信获取openId回调地址出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}*/
    
}
