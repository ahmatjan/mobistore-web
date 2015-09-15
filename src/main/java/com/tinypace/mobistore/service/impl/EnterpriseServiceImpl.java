package com.tinypace.mobistore.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinypace.mobistore.entity.Enterprise;
import com.tinypace.mobistore.entity.SysLoginAccount;
import com.tinypace.mobistore.entity.SysUser;
import com.tinypace.mobistore.entity.SysUser.UserType;
import com.tinypace.mobistore.service.EnterpriseService;
import com.tinypace.mobistore.service.LoginAccountService;
import com.tinypace.mobistore.util.IdGen;

@Service
public class EnterpriseServiceImpl extends CommonServiceImpl implements EnterpriseService {

	@Autowired
	private LoginAccountService loginaccountService;
	
    @Override
    @SuppressWarnings("unchecked")
    public Enterprise getEnterprise(String userId) {
        DetachedCriteria dc = DetachedCriteria.forClass(Enterprise.class);
        dc.add(Restrictions.eq("isDelete", Boolean.FALSE));
        dc.add(Restrictions.eq("userId", userId));
        List<Enterprise> list = (List<Enterprise>) findAllByCriteria(dc);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

	public void saveOrUpdate(Enterprise enterprise) {
		SysLoginAccount account = null;
		if(StringUtils.isNotBlank(enterprise.getId())){
			SysUser user = enterprise.getUser();
			user.setCustomerType(UserType.ENTERPRISE);
			getDao().save(user);
			enterprise.setUserId(user.getId());
			
			account=new SysLoginAccount();
			account.setUserId(user.getId());
			account.setEmail(user.getEmail());
			account.setActiveCode(IdGen.uuid());
			loginaccountService.saveOrUpdate(account);
		}
		enterprise.setUser(null);
		getDao().saveOrUpdate(enterprise);
	}
}
