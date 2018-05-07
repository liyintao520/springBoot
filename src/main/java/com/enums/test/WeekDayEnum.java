package com.enums.test;


/**
 * Created by liyintao on 2018/5/7.
 */
public enum WeekDayEnum {
    WEEK_DAY_ENUM_01("1","星期一"),
    WEEK_DAY_ENUM_02("2","星期二"),
    WEEK_DAY_ENUM_03("3","星期三"),
    WEEK_DAY_ENUM_04("4","星期四"),
    WEEK_DAY_ENUM_05("5","星期五"),
    WEEK_DAY_ENUM_06("6","星期六"),
    WEEK_DAY_ENUM_07("7","星期日");
    WeekDayEnum(String code,String value) {
        this.code=code;
        this.value=value;
    }
    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
