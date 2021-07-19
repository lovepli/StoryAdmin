package com.story.storyadmin.constant.sysmgr;

/**
 * 用户状态
 * @author lipan
 */
public enum UserStatusEnum {

    /**状态 */
    NORMAL("0", "正常"),
    LOCK("1","禁用");

    private String code;
    private String val;

    UserStatusEnum(String code, String val) {
        this.code = code;
        this.val = val;
    }

    public String code() {
        return code;
    }

    public String val() {
        return val;
    }
}
