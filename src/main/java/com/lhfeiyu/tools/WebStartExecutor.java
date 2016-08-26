package com.lhfeiyu.tools;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具包：Spring容器启动监听器 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.tools.WebStartExecutor <p>
 */
public class WebStartExecutor implements ApplicationListener<ContextRefreshedEvent> {
	
	/** Spring启动完成后执行此方法  */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
       if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
           //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
    	   //AuthAccess.setWxDataToProperty("access_token", "");
    	   //AuthAccess.setWxDataToProperty("ticket", "");
    	   //new SysJob().wxTokenRefresh();//获取微信的认证数据
    	    /*PropertiesConfiguration config;
			try {
				config = new PropertiesConfiguration("wx.properties");
				config.setProperty("wx.access_token", "");
				config.setProperty("wx.ticket", "");
				config.save();
			} catch (ConfigurationException e) {
				e.printStackTrace();
				System.out.println("初始化微信认证信息失败");
			}*/
       }
    }
    
}