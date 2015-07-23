package com.wolai.platform.constant;

import java.math.BigDecimal;


/**
 * 系统常量设置类
 * 
 * @author xuxiang
 * @version $Id$
 * @since
 * @see
 */

public final class Constant {
	// webClient 访问地址
	public static String WEB_CLIENT_URL = "http://localhost:9000";
	
	/**
	 * appClient访问接口前缀
	 */
	public static final String API_MOBI = "api/mobi/";
	
	/**
	 * webClint访问接口前缀
	 */
	public static final String API_WEB = "api/web/";
	
	/**
	 * 
	 */
	public static final String API_OUT_PACKAGE_CLIENT="com.wolai.platform.controller.api";
	
	public static final  String payment_productionName = "停车费";
	public static final  String payment_productionDesrcription = "喔来停车费";
	public static final  String alipay_partnerId = "2088021183682236";
	public static final  String alipay_partnerPrivKey = "MIICWwIBAAKBgQC2QclvVe6hggoS/O0zkExy8VWqc+xCKl89OhU68Lf12ZJErxZXAX98G/vkmXQi7tgD7F77eXxQNRqkzcMIxdPCoZFoF77idC2l4L0jo6nX7LiibaDkEOrn41zH1hO+PuoTOYGs4cie6mdkVivakn1FLQU1BspYq0l6qZjIZ3PRHQIDAQABAoGAbtEBUZjGR51x0qnG8BtE4H6sv79MX86zqd6vGe9Ta0CnFuv+gVh/006QkiZb3wGqhngqKG1tkLc5RAt4EtV2M1Jn8qdUmCaFvKtjUKKDaDgUzLZtyMx0LOsAQQ3h8W2Ov6XPnT7u2GistXaTeGNEt8QK9zAbK1Uv84UHUbf7+mECQQDiA6wBwGVazrmXqSQlH3W+KByRV8P99lC6UKWqfv66LoFlQQVHh0wCRhsRfZGa6WGzTpa57v/K+xhKyGBnbip3AkEAzm/xv0uIl3UAoXpnLC3n49rccNp55g4tMELKwkC4on1L5vcWoDDUh18GH/qCy3/GBWNje/ie0qpTbwlpZGlyCwJAHlS10apbL7uMZpD5W84EBBfn9Y1crQlQPiCyitXuI1g4XzlBlx7OEYQM/+TlFG/7f4+tZJhxVXErCmYnodnD8wJALsoEmvz9uoZUXEUn+UV2V5iVeiaqllAEQfoBhsGwl3OaroC2ahxnLz+EXd8joggV08bRF5okNU9RlcsmThHwPQJANDEP8MbzBT6u5+lFpBaaiye7BtJEcsmQRNgJmtPyvGAJlRFk3ghsW1U/Tgz/5YevkZujao0yHYCwPUgfaaV+yQ==";	
	public static final  String alipay_sellerAccount = "wolai@wolaitech.com";
	public static final  String alipay_notifyUrl = "http://101.200.189.57:9090/wolai-web/api/mobi/payment/alipayCallback";
	
    /**
     * 管理员权限编码
     */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    
    /**
     * 登录跳转页
     */
    public static final String LOGIN_URL = "/index.jsp";
    
    /**
     * 前台首页
     */
    public static final String APP_FRONT_INDEX = "index";
    
    /**
     * 分页默认大小
     */
    public static final int PAGE_SIZE = 15;
    
    /**
     * 用户登录信息
     */
    public static final String SESSION_LOGINFO = "session_loginfo";
    
    /**
     * app端用户获取key
     */
    public static final String REQUEST_USER = "request_user";
    
    /**
     * 排序方向-升序
     */
    public static final String ORDER_BY_ASC = "asc"; 
    
    /**
     * 排序方向-降序
     */
    public static final String ORDER_BY_DESC = "desc";

    public static final Long LONG_ZERO=0L;
    
    public static final String STRING_TRUE="true";
    
    public static final String STRING_FALSE="false";
    
    
  	public static enum RespCode{
  		SUCCESS(1), BIZ_FAIL(-1), INTERFACE_FAIL(-10), NOT_LOGIN(-100);
  		
  		private RespCode(int code){
  			this.code = code;
  		}
  		private int code;
  		
  		public int Code(){
  			return code;
  		}
  	}
  
}