package com.it.dashboard.master.repo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.master.domain.Agent;
import com.it.dashboard.master.domain.AgentBranch;
import com.it.dashboard.master.domain.AppSubtypeCharge;
import com.it.dashboard.master.domain.AppSubtypeChargePK;
import com.it.dashboard.master.domain.Gltitlm;
import com.it.dashboard.master.domain.KeyValue;
import com.it.dashboard.master.domain.PivActivity;
import com.it.dashboard.master.domain.PivTypeAuth;
import com.it.dashboard.master.domain.Spstdrat;
import com.it.dashboard.master.domain.TaxMaster;

public interface MasterDao {
	@SuppressWarnings("rawtypes")
	public List<Map> getPivTypes(String appName);
	public List<PivAmountGrid>  getAcctCodesByPivType(String pivType, String appName);
	public String getStatusDescription(String status, String appName);
	public String getPivTypeDescription(String pivType, String appName);
	public String getPivTypeDigit(String pivCd, String appName);
	public List<PivActivity> getActivity(String appName);
	List<Map> getPivTypesToDeptId(String deptId, String appName);
	List<KeyValue> getBankList(String appName);
	List<KeyValue> getBankBranchList(String bankCode, String appName);
	List<KeyValue> getAgentList(String appName);
	List<KeyValue> getAgentBranchList(String agentCode, String appName);
	Map<String, String> getAgentListMap(String appName);
	Map<String, String> getAgentBranchListMap(String agentCode, String appName);
	Agent getAgentById(String agentCode, String appName);
	//AgentBranch getAgentBranchById(String agentBranchCode);
	TaxMaster getTaxMasterById(String accountCode, String appName);
	Map<String, String> getBankListMap(String appName);
	Map<String, String> getBankBranchListMap(String bankCode, String appName);
	AgentBranch getAgentBranchById(String agentCode, String agentBranchCode, String appName);
	List<Gltitlm> getPivTypeObjList(String appName);
	List<Gltitlm> getPivTypeObjList(String deptId, String appName);
	//List<PivAmountGrid> getAcctCodeGridByPivType(String pivType); 
	String getCompIdToAgent(String agentCode, String appName);
	AgentBranch getAgentBranchByDeptId(List deptIdList, String appName);
	boolean isVatLiablePIV(String pivType, String appName);
	String getCostCenterToDepotCode(String areaCode, String depotCode, String appName);
	AppSubtypeCharge getAppSubtypeCharge(AppSubtypeChargePK id, String appName);
	Spstdrat getSpstdrat(String rateCode, String year, String appName);
	String getCostCenterToDepotCode1(String areaCode, String depotCode, String appName);
	Map<String, String> getPivTypeKeyValue(String deptId, String appName);
			
}
