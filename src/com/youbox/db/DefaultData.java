package com.youbox.db;


/**
 * ȫ������
 * @author zhongdaxia
 *
 */

public class DefaultData {
	
	//HTTP
	public static final String HTTP_URLADDR_LOGIN = "http://service.24box.cn:21198/YouBoxAdmin/login";    // ��¼ - URL��ַ
	public static final String HTTP_URLADDR_PHONESEARCH = "http://service.24box.cn:21198/YouBoxAdmin/packageDetail";    // �����ֻ��Ų�ѯ�������� - URL��ַ
	public static final String HTTP_URLADDR_packageDetailByParcelID = "http://service.24box.cn:21198/YouBoxAdmin/packageDetailByParcelID";    // �����ֻ��Ų�ѯ�������� - URL��ַ
	
	//FuncID
	public static final String HTTP_FUNCID_login = "1002";             // ��¼ - ���ܽӿ�
	public static final String HTTP_FUNCID_VERSION = "1001";             // �汾 - ���ܽӿ�
	public static final String HTTP_FUNCID_packageDetail = "2001";     // �����ֻ��Ų�ѯ��������  - ���ܽӿ�
	public static final String HTTP_FUNCID_packageDetailByParcelID = "2002";     // ���ݿ���Ų�ѯ��������  - ���ܽӿ�
	
	
	public static final int HTTP_TIMEOUT = 10000;    //http����ʱ

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
