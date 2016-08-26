package org.apache.jsp.views.front.base.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class addressManage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/views/common/meta_info.htm");
    _jspx_dependants.add("/views/common/common_css.htm");
    _jspx_dependants.add("/views/common/common_js.htm");
    _jspx_dependants.add("/views/common/common_front_js.htm");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html id=\"htmlSize\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\"/>\r\n");
      out.write("<meta name=\"renderer\" content=\"webkit\"/>\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"/>\r\n");
      out.write("<meta content=\"always\" name=\"referrer\"/>\r\n");
      out.write("<meta name=\"author\" content=\"www.lhfeiyu.com\"/>\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\"/>\r\n");
      out.write("<meta name=\"apple-mobile-web-app-capable\" content=\"yes\"/>\r\n");
      out.write("<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\"/>\r\n");
      out.write("<meta name=\"format-detection\" content=\"telephone=no\"/>\r\n");
      out.write("<!--<meta name=\"viewport\" content=\"target-densitydpi=medium-dpi,width=device-width,height=device-height,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no\" />-->\r\n");
      out.write("<meta name=\"keywords\" content=\"成都蓝海飞鱼,软件定制开发,网站建设,微信公众号开发,APP开发\"/>\r\n");
      out.write("<meta name=\"description\" content=\"成都蓝海飞鱼-集软件定制开发,网站建设,微信公众号开发,APP开发于一身的软件公司\"/>\r\n");
      out.write("<link rel=\"shortcut icon\" href=\"/images/favicon.ico\" type=\"image/x-icon\"/>\r\n");
      out.write("<title>迈噜打造母婴健康管理第一品牌  Powered by 成都蓝海飞鱼科技有限公司</title>");
      out.write('\r');
      out.write('\n');
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/css/front/main.css\" title=\"v\"/>\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/third-party/bootstrap/css/bootstrap.min.css\"/>\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/third-party/font-awesome/v4.6.3/css/font-awesome.min.css\"/>\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/third-party/weui/weui.min.css\"/>\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/third-party/bootstrap-select-1.10.0/css/bootstrap-select.min.css\"/>\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/third-party/jquery-weui-0.8/css/jquery-weui.css\"/>\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/third-party/jquery-weui-0.8/css/jquery-weui.min.css\"/>\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/third-party/animate/animate.css\"/>\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/css/common.css\" title=\"v\"/>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("\t<script src=\"/third-party/compatible/html5shiv.min.js\"></script>\r\n");
      out.write("\t<script src=\"/third-party/compatible/respond.min.js\"></script>\r\n");
      out.write("\t<![endif]\r\n");
      out.write("-->\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/css/front/main.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/css/front/jquery-weui.min.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<input type=\"hidden\" id=\"userId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t\t<!--主体内容开始-->\r\n");
      out.write("\t\t<a onclick=\"lh.back()\" class=\"back\"> \r\n");
      out.write("\t\t\t<img src=\"images/front/back.png\" alt=\"\">\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t\t<div class=\"addBox\" id=\"data-container\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<input type=\"button\" value=\"新增地址\" class=\"xq-btn  newAdd\"\r\n");
      out.write("\t\t\tonclick=\"lh.jumpR('/addOrUpdateUserAddress');\">\r\n");
      out.write("\t\t<div class=\"spmc_bg\"></div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t");
      out.write("<!--\r\n");
      out.write("<script type=\"text/javascript\" src=\"http://apps.bdimg.com/libs/jquery/1.11.3/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">window.jQuery || document.write('<script src=\"/third-party/jquery/jquery-1.11.3.min.js\"><\\/script>');</script>\r\n");
      out.write("-->\r\n");
      out.write("<!-- <script type=\"text/javascript\" src=\"/third-party/jquery/jquery-1.11.3.min.js\"></script>  -->\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/jquery/jquery-3.0.0.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/lodash/lodash.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/json2/json2.js\"></script>\r\n");
      out.write("<!-- <script type=\"text/javascript\" src=\"/third-party/jquery.event.drag/jquery.event.drag-2.2.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/jquery.event.drag/jquery.event.drag.live-2.2.js\"></script> -->\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/clipboard/clipboard.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/mustache/mustache.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/is/is.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/moment/moment.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/moment/locale/zh-cn.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/jquery-ajax-cache/locache.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/base/js/common/common_base.js\" title=\"bv\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/base/js/common/common_tools.js\" title=\"bv\"></script>\r\n");
      out.write("<!--\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/jquery-weui-0.8/js/jquery-weui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/swiper/swiper-3.3.1.jquery.min.js\"></script>\r\n");
      out.write("-->\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/common/main.js\" title=\"v\"></script>");
      out.write("\r\n");
      out.write("\t");
      out.write("<script type=\"text/javascript\" src=\"/third-party/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"http://res.wx.qq.com/open/js/jweixin-1.0.0.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/jquery-weui-0.8/js/jquery-weui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/pagination/query.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/pagination/paging.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/swiper/swiper-3.3.1.jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/third-party/mustache/mustache.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"/base/js/common/common_front.js\" title=\"bv\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/base/js/common/common_front_ui_weui.js\" title=\"bv\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/front/common.js\" title=\"v\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/common/main.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--<script type=\"text/javascript\" src=\"http://res.wx.qq.com/open/js/jweixin-1.0.0.js\"></script>-->\r\n");
      out.write("<!--<script type=\"text/javascript\" src=\"/third-party/qrcode/utf.js\"></script>-->\r\n");
      out.write("<!--<script type=\"text/javascript\" src=\"/third-party/qrcode/jquery.qrcode.js\"></script>-->\r\n");
      out.write("<!--<script type=\"text/javascript\" src=\"/third-party/jquery.SuperSlide/jquery.SuperSlide.2.1.1.js\"></script>-->\r\n");
      out.write("<!--<script type=\"text/javascript\" src=\"/third-party/pzselect/pzselect.1.0.js\" ></script>-->\r\n");
      out.write("<!--<script type=\"text/javascript\" src=\"/base/js/common/common_front_ui_bootstrap.js\" title=\"bv\"></script>-->\r\n");
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tlh.param = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${paramJson}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<script type=\"text/javascript\"\r\n");
      out.write("\t\tsrc=\"/js/front/user/addReceiveAddress.js\" title=\"v\"></script>\r\n");
      out.write("\t<script id=\"template\" type=\"x-tmpl-mustache\">\r\n");
      out.write("\t{{#rows}}\r\n");
      out.write("  \t\t\t <div class=\"user-Address\" >\r\n");
      out.write("                <div class=\"add-top\" onclick=\"choiceAddress({{id}})\">\r\n");
      out.write("                    <span>{{receiverName}}</span>\r\n");
      out.write("                    <span class=\"rt\">{{phone}}</span>\r\n");
      out.write("                    <p>{{provinceName}}{{cityName}}{{addressDetail}}</p>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"add-bot\">\r\n");
      out.write("                    <span class=\"mrdzS\" onclick=\"setDefult({{id}})\">\r\n");
      out.write("                        {{&isDefult}}\r\n");
      out.write("                    \t\t    设为默认地址\r\n");
      out.write("                    </span>\r\n");
      out.write("                    <div class=\"rt\">\r\n");
      out.write("                        <span class=\"dzSpan\" onclick=\"lh.jumpR('/addOrUpdateAddress/{{id}}');\">编辑</span>\r\n");
      out.write("                        <span class=\"dzSpan\" onclick=\"deleteAddress({{id}});\">删除</span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\t{{/rows}}\t\r\n");
      out.write("    </script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
