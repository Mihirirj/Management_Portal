package com.it.dashboard.master.repo;

import com.it.dashboard.master.domain.Pcesthtt;

public interface PcesthttDao 
{

	void update(Pcesthtt pcesthtt, String appName);

	Pcesthtt findByEstimateNo(String estimateNo, String appName);

}
