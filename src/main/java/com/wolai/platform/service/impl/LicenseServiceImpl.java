package com.wolai.platform.service.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.wolai.platform.bean.Page;
import com.wolai.platform.entity.License;
import com.wolai.platform.entity.SysMessageSend;
import com.wolai.platform.service.LicensePlateService;

@Service
public class LicenseServiceImpl extends CommonServiceImpl implements LicensePlateService {

	@Override
	public Page listByUser(String userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(License.class);
		dc.add(Restrictions.eq("userId", userId));
		dc.addOrder(Order.asc("carNo"));
		Page page = findPage(dc, 0, 1000);
		
		return page;
	}

	@Override
	public void create(License po) {
		getDao().saveOrUpdate(po);
		
	}

	@Override
	public void update(License po) {
		getDao().saveOrUpdate(po);
	}
}