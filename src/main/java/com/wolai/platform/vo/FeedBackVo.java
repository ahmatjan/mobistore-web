package com.wolai.platform.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.wolai.platform.entity.Coupon.CouponType;

public class FeedBackVo {
	private String id;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
