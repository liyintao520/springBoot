package com.enums.test;

/**
 * Created by liyintao on 2018/5/7.
 */
public enum LevelOneDeptEnum {
	Ceo(1, "总经理室", "10", "company", "zongjls"),
	Resource(2, "综合资源部", "3", "company", "zonghzyb"),
	Risk(3, "风险与合规部", "6", "dept", "fengxyhgb"),
	IT(4, "信息技术部", "1", "dept", "xinxjsb"),
	Ops(5, "运营管理部", "2", "dept", "yunyglb"),
	FinancialAsset(6, "金融资产交易部", "8", "dept", "jinrzcjyb"),
	WealthManage(8, "财富管理部", "4", "dept", "caifglb"),
	AssetManage(40, "资产管理部", "9", "dept", "caicglb"),
	RiskAsset(50, "风险资产交易部", "7", "dept", "fengxzcjyb"),
	Innovate(51, "创新发展部", "5", "dept", "cxfzb");

	// OA一级部门ID
	private int deptId;

	// 部门名称
	private String deptName;

	// 接收考勤报表的群组Tag
	private String reportReceiversTag;

	// 报表内容范围: company/dept
	private String reportScope;
	
	//部门英文名称
	private String deptEname;

	private LevelOneDeptEnum(int deptId, String deptName, String reportReceiversTag, String reportScope, String ename) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.reportReceiversTag = reportReceiversTag;
		this.reportScope = reportScope;
		this.deptEname = ename;
	}

	public static LevelOneDeptEnum getDeptById(int deptId) {
		for (LevelOneDeptEnum dept : LevelOneDeptEnum.values()) {
			if (dept.getDeptId() == deptId) {
				return dept;
			}
		}
		return null;
	}

	public static LevelOneDeptEnum getDeptByName(String deptName) {
		for (LevelOneDeptEnum dept : LevelOneDeptEnum.values()) {
			if (dept.getDeptName().equals(deptName)) {
				return dept;
			}
		}
		return null;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getReportReceiversTag() {
		return reportReceiversTag;
	}

	public void setReportReceiversTag(String reportReceiversTag) {
		this.reportReceiversTag = reportReceiversTag;
	}

	public String getReportScope() {
		return reportScope;
	}

	public void setReportScope(String reportScope) {
		this.reportScope = reportScope;
	}
	
	public String getDeptEname() {
		return deptEname;
	}

	public void setDeptEname(String deptEname) {
		this.deptEname = deptEname;
	}

}
