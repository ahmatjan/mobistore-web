package com.wolai.platform.service;

import com.wolai.platform.entity.ParkingLot;
import com.wolai.platform.entity.ParkingRecord;

public interface ParkingService extends CommonService {
	
	ParkingRecord packInfo(String id);

}
