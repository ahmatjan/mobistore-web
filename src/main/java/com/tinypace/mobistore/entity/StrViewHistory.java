package com.tinypace.mobistore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "str_view_history")
public class StrViewHistory extends IdEntity {
	private static final long serialVersionUID = -1521461393720914818L;

	private Date viewTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", insertable = false, updatable = false)
	private StrClient client;
	
	@Column(name="client_id")
	private String clientId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private StrProduct product;
	
	@Column(name="product_id")
	private String productId;

	public Date getViewTime() {
		return viewTime;
	}

	public void setViewTime(Date viewTime) {
		this.viewTime = viewTime;
	}

	public StrClient getClient() {
		return client;
	}

	public void setClient(StrClient client) {
		this.client = client;
	}

	public StrProduct getProduct() {
		return product;
	}

	public void setProduct(StrProduct product) {
		this.product = product;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
