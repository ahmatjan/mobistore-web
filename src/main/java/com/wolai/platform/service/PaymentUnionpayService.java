package com.wolai.platform.service;

import java.math.BigDecimal;
import java.util.Map;

import com.wolai.platform.entity.Bill;
import com.wolai.platform.entity.ParkingRecord;
import com.wolai.platform.entity.UnionpayCardBound;


public interface PaymentUnionpayService extends CommonService {

	Map<String, String> prepareTrans(String wolaiTradeNo, int intValue);

	Map<String, String> boundPers(String userId, String orderId, String accNo, String certifId, String cvn, String expired);

	Map<String, String> unboundPers(String userId, String orderId);

	UnionpayCardBound createBoundRecordPers(String userId, String accNo, String wolaiTradeNo);

	UnionpayCardBound boundQueryByCard(String accNo);

	UnionpayCardBound boundQueryByUser(String userId);

	Map<String, String> postPayConsume(String wolaiTradeNo, String accNo, BigDecimal amount);

	
}