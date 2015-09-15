package com.tinypace.mobistore.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Service;

import com.tinypace.mobistore.bean.Page;
import com.tinypace.mobistore.constant.Constant;
import com.tinypace.mobistore.entity.SysLoginAccount;
import com.tinypace.mobistore.service.LoginAccountService;
import com.tinypace.mobistore.util.CustomizedPropertyConfigurer;
import com.tinypace.mobistore.util.SendMailUtil;

@Service
public class LoginAccountServiceImpl extends CommonServiceImpl implements LoginAccountService {

	@Override
	public SysLoginAccount authLoginAccount(String email, String password) {
		DetachedCriteria dc = DetachedCriteria.forClass(SysLoginAccount.class);
		dc.setFetchMode("user", FetchMode.JOIN);
		dc.add(Restrictions.eq("email", email));
		dc.add(Restrictions.eq("password", password));
		dc.add(Restrictions.eq("isDelete", Boolean.FALSE));
		dc.add(Restrictions.eq("isDisable", Boolean.FALSE));
		SysLoginAccount account = (SysLoginAccount) getDao().getByCriteria(dc);
		return account;
	}
	
	public Page<SysLoginAccount> findAllByPage(SysLoginAccount loginAccount,int start,int limit) {
		DetachedCriteria dc = DetachedCriteria.forClass(SysLoginAccount.class);
		dc.add(Restrictions.eq("isDelete", Boolean.FALSE));
		if(loginAccount!=null){
			// 是否查看禁用账户相关设置
			if(loginAccount.getIsDisable()!=null){
				dc.add(Restrictions.eq("isDisable",loginAccount.getIsDisable()));
			}
			
			// email模糊查询
			if(StringUtils.isNotEmpty(loginAccount.getEmail())){
				dc.add(Restrictions.like("email",loginAccount.getEmail(),MatchMode.ANYWHERE));
			}
			
			if(StringUtils.isNotBlank(loginAccount.getUserId())){
				dc.add(Restrictions.eq("userId",loginAccount.getUserId()));
			}
			// 企业名称
			if(loginAccount.getEnterprise()!=null){
				dc.createAlias("enterprise", "enterprise", JoinType.LEFT_OUTER_JOIN);
				dc.add(Restrictions.like("enterprise.name",loginAccount.getEnterprise().getName(),MatchMode.ANYWHERE));
			}
		}
		return getDao().findPage(dc, start, limit);
	}

	@Override
	public void saveOrUpdate(SysLoginAccount loginaccount) {
		getDao().saveOrUpdate(loginaccount);
		Map<String, Object> a = new HashMap<String, Object>();
		a.put("userName", loginaccount);
		a.put("activeUrl",Constant.WEB_PATH+"/loginAccount/active?"+loginaccount.getActiveCode());
		SendMailUtil.sendFtlMail(loginaccount.getEmail(), "账户激活", "mailtemplate/ActiveLogin.ftl",a);
	}

}
