package com.story.storyadmin.constant.enumtype;

/**
 * 标志信息  【可以表示是否逻辑删除，是否是erp账号的判断】
 * @date 2014-10-16 下午02:51:21
 */
public enum YNFlagStatusEnum {

    /** */
	VALID("1","有效"),
	FAIL("0","无效");

	private YNFlagStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String desc;
	private String code;

	public String getDesc() {
		return desc;
	}

	public String getCode() {
		return code;
	}

	@Override
    public String toString(){
		return code+","+desc;
	}

	/**
	 * 通过key 查找描述 方法
	 * @param key
	 */
	public static String getValueByKey(String key) {
	    //枚举遍历
		for (YNFlagStatusEnum pm : YNFlagStatusEnum.values()) {
			if (pm.getCode().equals(key)) {
				return pm.getDesc();
			}
		}
		return null;
	}
}
