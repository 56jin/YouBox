package com.youbox.db;


/**
 * 全局数据
 * @author zhongdaxia
 *
 */

public class DefaultData {
	
	//HTTP
	public static final String HTTP_URLADDR_LOGIN = "http://service.24box.cn:21198/YouBoxAdmin/login";    // 登录 - URL地址
	public static final String HTTP_URLADDR_PHONESEARCH = "http://service.24box.cn:21198/YouBoxAdmin/packageDetail";    // 根据手机号查询包裹详情 - URL地址
	public static final String HTTP_URLADDR_packageDetailByParcelID = "http://service.24box.cn:21198/YouBoxAdmin/packageDetailByParcelID";    // 根据手机号查询包裹详情 - URL地址
	
	//FuncID
	public static final String HTTP_FUNCID_login = "1002";             // 登录 - 功能接口
	public static final String HTTP_FUNCID_VERSION = "1001";             // 版本 - 功能接口
	public static final String HTTP_FUNCID_packageDetail = "2001";     // 根据手机号查询包裹详情  - 功能接口
	public static final String HTTP_FUNCID_packageDetailByParcelID = "2002";     // 根据快件号查询包裹详情  - 功能接口
	
	
	public static final int HTTP_TIMEOUT = 10000;    //http请求超时

	//Log
	public static final String LOG_TYPE_ERROR = "error - ";  
	

	public static final String FILENAME = "filename"; 
	
	public static String STRING_USERNAME = "";
	
	public static String STRING_CURRENT_VERSION = "";
	
	public static String STRING_versioninfo_version = "";
	public static String STRING_versioninfo_action = "";
	public static String STRING_versioninfo_url = "";
	public static String STRING_versioninfo_description = "";
}
