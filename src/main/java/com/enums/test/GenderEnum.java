package com.enums.test;

/**
 * 实现带有构造器的枚举
 * Created by liyintao on 2018/5/7.
 */
public enum GenderEnum {
	//通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
    //赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
    YT(1,"圆通"),
    ST(2,"申通"),
    YD(3,"韵达"),
    ZT(4,"中通"),
    YZ(5,"邮政"),
    EMS(6,"EMS"),
    BSHT(7,"百世汇通"),
    TTKD(8,"天天快递"),
    YSWL(9,"优速物流"),
    SFSD(10,"顺丰速递"),
    JD(11,"京东"),
    PJ(12,"品骏"),
    DB(13,"德邦"),
    AN(14,"安能"),
    QT(15,"其他");
    
    public final int code;
    
    public final String desc;

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
    private GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getDesc() {
        return desc;
    }
    
    /**
     * 通过枚举<code>code</code>获得枚举
     * 
     * @param code
     * @return
     */
    public static GenderEnum getByCode(int code) {
        for (GenderEnum genum : values()) {
            if (genum.getCode()==(code)) {
                return genum;
            }
        }
        return null;
    }
    
    public static GenderEnum nameOf(String name){
    	GenderEnum genum = null;
		if (name != null){
			for (GenderEnum type : GenderEnum.values()) {
				if (type.name().equalsIgnoreCase(name)){
                    genum = type;
                }
			}
		}
		return genum;
	}
    
    public static GenderEnum descOf(String desc){
    	GenderEnum genum = null;
		if (desc != null){
			for (GenderEnum type : GenderEnum.values()) {
				if (type.desc.equalsIgnoreCase(desc)){
                    genum = type;
                }
			}
		}
		return genum;
	}
}
