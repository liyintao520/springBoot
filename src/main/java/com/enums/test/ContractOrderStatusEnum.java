package com.enums.test;

/**
 * 合同订单状态
 * Created by liyintao on 2018/5/7.
 */
public enum ContractOrderStatusEnum {
	NotActive("0", "未生效", ""),
	Repayment("1", "还款中",  "还款中"),
	Overdue("2", "逾期", "还款中"),
	Settle("3-1", "结清", "已结清"),
	ZqOverdue("3-3", "展期结清", "已结清"),
	Failure("4", "失效", ""),
	TolerancePeriod("M-0", "容忍期", "还款中"),
	RepaymentOver("5-1", "提前还款完成", "提前结清");

	private String type; // 类型
	private String status; // 对应状态
	private String view; // 页面对应展示状态

	private ContractOrderStatusEnum(String type, String status, String view) {
		this.type = type;
		this.status = status;
		this.view = view;
	}

	/**
	 * 根据合同订单状态类型，获取页面显示的字符串
	 * enum 的 values() 返回是 该对象的数组，这样写扩展性好。
	 * @param type
	 * @return
	 */
	public static ContractOrderStatusEnum getShowByType(String type) {
		for (ContractOrderStatusEnum statusEnum : ContractOrderStatusEnum.values()) {
			if (statusEnum.getType().equals(type)) {
				return statusEnum;
			}
		}
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public static void main(String[] args) {
		System.out.println(ContractOrderStatusEnum.TolerancePeriod.getType());
		System.out.println(ContractOrderStatusEnum.TolerancePeriod.getStatus());
		System.out.println(ContractOrderStatusEnum.TolerancePeriod.getView());
	}
}
