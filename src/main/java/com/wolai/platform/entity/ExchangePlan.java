package com.wolai.platform.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * 兑换计划
 * @author Ethan
 *
 */
@Entity
@ Table(name="wo_exchange_plan")
public class ExchangePlan extends idEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 780639903449593380L;

	/**
	 * 交换类型
	 * @author Ethan
	 *
	 */
	public static enum ExchangeType{
		
		/**
  		 * money(抵用券)
  		 */
  		TOCOUPON("TOCOUPON"),
  		
  		/**
  		 * time(抵时券)
  		 */
  		TOGOODS("TOGOODS");
  		
  		private ExchangeType(String textVal){
  			this.textVal=textVal;
  		}
  		private String textVal;
  		
  		public String toString(){
  			return textVal;
  		}
	}
	
	/**
	 * 货币类型
	 * @author Ethan
	 *
	 */
	public static enum CurrencyType{
		/**
  		 * TNTEGRAL(积分)
  		 */
  		REWRRDPOINTS("REWRRDPOINTS"),
  		
  		/**
  		 * TIME(现金)
  		 */
  		MONEY("MONEY");
  		
  		private CurrencyType(String textVal){
  			this.textVal=textVal;
  		}
  		private String textVal;
  		
  		public String toString(){
  			return textVal;
  		}
	}
	
	/**
	 * 兑换类型
	 */
	@Enumerated(EnumType.STRING)
	private ExchangeType targetType;
	
	
	/**
	 * 兑换介质
	 */
	@Enumerated(EnumType.STRING)
	private CurrencyType sourceType;
	
	/**
	 * 单价
	 */
	private BigDecimal price;
	
	/**
	 * 总数量
	 */
	private Integer number;
	
	/**
	 * 活动有效期：开始时间
	 */
	private Date startTime;
	
	/**
	 * 活动有效期：结束时间
	 */
	private Date endTime;
	
	/**
	 * 是否已失效
	 */
	private Boolean disabled=Boolean.FALSE;

	public ExchangeType getTargetType() {
		return targetType;
	}

	public void setTargetType(ExchangeType targetType) {
		this.targetType = targetType;
	}

	public CurrencyType getSourceType() {
		return sourceType;
	}

	public void setSourceType(CurrencyType sourceType) {
		this.sourceType = sourceType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	
}
