package com.caizhixiang.util;

public enum DatePatternEnum {
    // @formatter:off
    yyyyMMddHHmmssSSS("yyyy-MM-dd HH:mm:ss.SSS"), 
    yyyyMMddHHmmss("yyyy-MM-dd HH:mm:ss"), 
    yyyyMMddHHmm("yyyy-MM-dd HH:mm"), 
    yyyyMMddHHmmssCon("yyyyMMddHHmmss"), 
    yyyyMMddHyphen("yyyy-MM-dd"), 
    yyyyMMdd("yyyyMMdd"),
    yyyyMMddX("yyyy/MM/dd"),
    yyyyMMddXHHmmss("yyyy/MM/dd HH:mm:ss"),
	HHmmss("HHmmss");
    // @formatter:on

    private DatePatternEnum(final String pattern) {
        this.pattern = pattern;
    }

    private String pattern;

    public String getPattern() {
        return pattern;
    }
}
