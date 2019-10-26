package com.shop;

/**
 * 常量
 * @className Constant 
 * @date 2014-9-26 下午12:13:09
 */
public interface SysConstant {
	
	
	public static final String ROW_COUNT_PRE = "select count(*) ";
	
	/**
	 * 查询前缀
	 */
	public static final String QUERY_PRE = "Qu";
	
	/**
	 * 查询分隔符 Qu_chanid_eq_s
	 */
	public static final String QUERY_SPIT = "_";
	
	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** 默认日期格式 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	
	
	/**
	 * 当前站点key
	 */
	public static final String SITE_KEY = "_site_key";
	
	public static final String SITE_COOKIE_KEY = "_site_cookie_key";
	
	public static final String REQUEST_SITE_ID = "_request_site_id";
	
	/**
	 * 模板列表默认条数10
	 */
	public static final Integer DEFAULT_COUNT = 10;
	
	/**
	 * 路径分隔符
	 */
	public static final String SPIT = "/";
	
	/**
	 * 图片存放
	 */
	public static final String IMAGE_STORE_PATH = "Image";
	
	/**
	 * 媒体文件存放
	 */
	public static final String MEDIA_STORE_PATH = "Media";
	
	/**
	 * flash文件存放
	 */
	public static final String FLASH_STORE_PATH = "Flash";
	
	/**
	 * file文件存放
	 */
	public static final String FILE_STORE_PATH = "other";
	

	
	
	//===================用户权限================
	
	/**
	 * 用户所属机构
	 */
	public static final String USER_OWN_ORGAN = "userOwnOrgan";
	
	/**
	 * 用户拥有的菜单
	 */
	public static final String GRANT_RESOURCE_MENUS = "grantResourcesMenu";
	
	/**
	 * 用户拥有所有资源（包含菜单和功能）
	 */
	public static final String GRANT_RESOURCE_ALL = "grantResourcesAll";
	
	/**
	 * 超级权限
	 */
	public static final String ROLE_SUPER = "roleSuper";
	public static final String ROLE_SUPER_Y = "1";
	public static final String ROLE_SUPER_N = "0";
	
	public static final String RES_TYPE_MENU = "0";
	public static final String RES_TYPE_PERMISSION = "1";
	
	//===================用户权限================
	
	//通用常量设置
	/**
	 * 成功、正常
	 */
	public static final String COMMON_STATUS_NORMAL = "00";
	public static final String COMMON_STATUS_DISABLED = "01";
	public static final String COMMON_STATUS_DEAL = "02";
	
	
	//状态 --01审核通过 02 审核不通过 
	public static final String MERCHANT_STATUS_SUCCESS = "01";
	public static final String MERCHANT_STATUS_FAIL = "02";
	//--申请状态  00 无 01 新增02 删除
	public static final String MERCHANT_APPLY_STATUS_NONE = "00";
	public static final String MERCHANT_APPLY_STATUS_ADD = "01";
	public static final String MERCHANT_APPLY_STATUS_DEL = "02";
	//-- 审核状态  01待审核 02已审核
	public static final String MERCHANT_EXAMINE_STATUS_WAIT = "01";
	public static final String MERCHANT_EXAMINE_STATUS_WAITED = "02";
	
	
	
	public static final String MERCHANT_DEFAULT_ENTERPWD = "12345678";
	
	
	
	
	
	
	
}
