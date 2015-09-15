package com.tinypace.mobistore.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PayQueryResponseVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -56729641387809295L;

	
	private String code;
	
	/**应付费用，不考虑抵时券 */
	private BigDecimal expenses;
	
	/** 应付费用，考虑抵时券 */
	private BigDecimal accruedExpenses;
	
	// 计费时间
	private Long chargeTime;
	
	/** 限定离场时间 */
	private Long  remainingTime;
	
	private String msg;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getExpenses() {
		return expenses;
	}
	public void setExpenses(BigDecimal expenses) {
		this.expenses = expenses;
	}
	public BigDecimal getAccruedExpenses() {
		return accruedExpenses;
	}
	public void setAccruedExpenses(BigDecimal accruedExpenses) {
		this.accruedExpenses = accruedExpenses;
	}
	public Long getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(Long remainingTime) {
		this.remainingTime = remainingTime;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(Long chargeTime) {
		this.chargeTime = chargeTime;
	}

}
