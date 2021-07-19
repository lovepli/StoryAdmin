package com.story.storyadmin.constant;

/**
 * 全局常量类
 */
public final class Constants {


	/**********************通用代码 start***********************/

	//获取条线的角色标识
	public static final String ERP_ROLE_BL = "jr_scm_bl";
	//获取组织的角色标识
	public static final String ERP_ROLE_ORG = "jr_scm_org";


	/**********************通用代码 end***********************/

	/**
	 * 过期时间 数值单位s
     * 静态内部类
	 */
	public static class ExpireTime {
		// 私有构造函数
		private ExpireTime() {
		}
		public static final int TEN_SEC =  10;//10s
		public static final int THIRTY_SEC =  30;//30s
		public static final int ONE_MINUTE =  60;//一分钟
		public static final int THIRTY_MINUTES =  60 * 30;//30分钟
		public static final int ONE_HOUR = 60 * 60;//一小时
		public static final int THREE_HOURS = 60 * 60 * 3;//三小时
		public static final int TWELVE_HOURS =  60 * 60 * 12;//十二小时，单位s
		public static final int ONE_DAY = 60 * 60 * 24;//二十四小时
	}






}
