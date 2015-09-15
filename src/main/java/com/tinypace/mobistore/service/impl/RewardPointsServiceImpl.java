package com.tinypace.mobistore.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.tinypace.mobistore.entity.RewardPoints;
import com.tinypace.mobistore.service.RewardPointsService;

@Service
public class RewardPointsServiceImpl extends CommonServiceImpl implements RewardPointsService {

	@Override
	public RewardPoints getByUserPers(String userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(RewardPoints.class);
		dc.add(Restrictions.eq("userId", userId));
		List<RewardPoints> ls = (List<RewardPoints>) findAllByCriteria(dc);
		if (ls.size() > 0) {
			return ls.get(0);
		} else {
			RewardPoints rewardPoints = new RewardPoints();
			rewardPoints.setBalance(0);
			rewardPoints.setUserId(userId);
			saveOrUpdate(rewardPoints);
			return rewardPoints;
		}
	}

	@Override
	public void exchange(RewardPoints rewardPoints, String amount, String exchangePlanId) {
		
	}
}
