package com.wolai.platform.service;

import java.util.List;

import com.wolai.platform.bean.Page;
import com.wolai.platform.entity.License;
import com.wolai.platform.entity.ParkingLot;
import com.wolai.platform.entity.ParkingRecord;

public interface LicensePlateService extends CommonService {

	Page listByUser(String userId);

	void create(License po);
	void update(License po);
}