package com.it.dashboard.master.repo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.master.domain.Agent;
import com.it.dashboard.master.domain.AgentBranch;
import com.it.dashboard.master.domain.AppSubtypeCharge;
import com.it.dashboard.master.domain.AppSubtypeChargePK;
import com.it.dashboard.master.domain.Gltitlm;
import com.it.dashboard.master.domain.KeyValue;
import com.it.dashboard.master.domain.PivActivity;
import com.it.dashboard.master.domain.Spstdrat;
import com.it.dashboard.master.domain.TaxMaster;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class MasterDaoImpl extends EmSelector implements MasterDao {
	//@PersistenceContext(unitName = "persistenceUnitPIV")
//	private EntityManager getEntityManager(appName);
	private static final Log log = LogFactory.getLog(MasterDaoImpl.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> getPivTypes(String appName) {
		log.error("$$$$$$$$$ getPivTypes");
		try {
			/**
			 * select substr(title_cd,5,7) , title_nm from gltitlm where
			 * status=2 and title_cd like 'PIV%' order by title_cd ;
			 */

			String qryStr = "SELECT new Map(trim(a.titleCd),a.titleNm) FROM Gltitlm a WHERE a.status=:status and a.titleCd LIKE :like";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("status", new BigDecimal(2));
			query.setParameter("like", "PIV" + "%");
			List<Map> list = query.getResultList();
			
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Gltitlm> getPivTypeObjList(String appName) {
		log.error("$$$$$$$$$ getPivTypeObjList");
		try {
			String qryStr = "SELECT a FROM Gltitlm a WHERE a.status=:status "+
							" and a.titleCd LIKE :like"+
							" and a.sysType = 'PIV' order by titleNm";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("status", new BigDecimal(2));
			query.setParameter("like", "PIV" + "%");
			List<Gltitlm> list = query.getResultList();
			
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Gltitlm> getPivTypeObjList(String deptId, String appName) {
		log.error("$$$$$$$$$ getPivTypeObjList");
		try {
			String qryStr = "SELECT a FROM Gltitlm a WHERE a.status=:status "+
							" and a.titleCd LIKE :like"+
							" and a.sysType = 'PIV' "+
							" and a.titleCd not in "+
							" (select a.id.titleCd from PivTypeAuth a where a.id.deptId = :deptId)"+
							" order by titleNm";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("status", new BigDecimal(2));
			query.setParameter("like", "PIV" + "%");
			query.setParameter("deptId", deptId);
			List<Gltitlm> list = query.getResultList();
			
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> getPivTypesToDeptId(String deptId, String appName) {
		log.error("$$$$$$$$$ getPivTypesToDeptId");
		try {
			/**
			 * select substr(title_cd,5,7) , title_nm from gltitlm where
			 * status=2 and title_cd like 'PIV%' order by title_cd ;
			 */

			String qryStr = "SELECT new Map(a.titleCd,a.titleNm) FROM Gltitlm a, PivTypeAuth p "+
							" WHERE a.status=:status and a.titleCd LIKE :like"+
							" and a.titleCd = p.id.titleCd "	+
							" and p.id.deptId = :deptId"+
							" order by a.titleNm ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("status", new BigDecimal(2));
			query.setParameter("like", "PIV" + "%");
			query.setParameter("deptId", deptId);
			List<Map> list = query.getResultList();
			
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String,String> getPivTypeKeyValue(String deptId, String appName) {
		log.error("$$$$$$$$$ getPivTypeKeyValue");
		try {
			/**
			 * select substr(title_cd,5,7) , title_nm from gltitlm where
			 * status=2 and title_cd like 'PIV%' order by title_cd ;
			 */
			Map<String,String> pivTypeList = new LinkedHashMap<String,String>();

			String qryStr = "SELECT a.titleCd,a.titleNm FROM Gltitlm a, PivTypeAuth p "+
							" WHERE a.status=:status and a.titleCd LIKE :like"+
							" and a.titleCd = p.id.titleCd "	+
							" and p.id.deptId = :deptId"+
							" order by a.titleNm ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("status", new BigDecimal(2));
			query.setParameter("like", "PIV" + "%");
			query.setParameter("deptId", deptId);
			//List<Map> list = query.getResultList();
			List<Object[]> list = query.getResultList();
			for (Object[] obj : list) {
			     pivTypeList.put(obj[0].toString(),obj[1].toString().trim());
			}
			
			return pivTypeList;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<PivAmountGrid> getAcctCodesByPivType(String pivType, String appName) {
		log.error("$$$$$$$$$ getAcctCodesByPivType");
		try {
			String qryStr = " SELECT new com.it.dashboard.issue.domain.PivAmountGrid(c.id.acCd, trim(c.acNm), c.isTax, c.isRefund , b.sortKey) " +
					" FROM Gltitlm a , Glacgrpm b , Glacctm c " +
					" WHERE a.titleCd = :pivType " +
					" AND a.titleCd = b.id.titleCd " +
					" AND trim(b.id.acCd)=trim(c.acCd) " +
					" ORDER BY b.sortKey";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("pivType", pivType);
			List<PivAmountGrid> list = query.getResultList();
			for(int i=0;i<list.size();i++)
			{
				PivAmountGrid grid = list.get(i);
				//PivAmountGrid grid = new PivAmountGrid();
				
				if(grid.getIsTax()!=null && grid.getIsTax().equals("Y"))
				{
					TaxMaster taxMaster = getTaxMasterById(grid.getAcCd(), appName);
					if(taxMaster!=null)
						grid.setTaxValue(taxMaster.getTaxVal().doubleValue());
					//m.setAcNm("xxxxxx");
					//taxAcctList.add(m);
				}
				//
				//Iterator s1 = m.values().iterator();
				//list.add(grid);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<PivAmountGrid> getAcctCodeGridByPivType(String pivType) {
		//new com.it.dashboard.issue.domain.(c.id.acCd, trim(c.acNm), c.isTax, c.isRefund , b.sortKey) " +
		try {
			String qryStr = " SELECT new com.it.dashboard.issue.domain.PivAmountGrid(c.id.acCd, trim(c.acNm), c.isTax, c.isRefund , b.sortKey) " +
					" FROM Gltitlm a , Glacgrpm b , Glacctm c " +
					" WHERE a.titleCd = :pivType " +
					" AND a.titleCd = b.id.titleCd " +
					" AND trim(b.id.acCd)=trim(c.acCd) " +
					" ORDER BY b.sortKey";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("pivType", pivType);
			List<PivAmountGrid> list = query.getResultList();
			//List<PivAmountGrid> list = new ArrayList<PivAmountGrid>();
			for(int i=0;i<list.size();i++)
			{
				PivAmountGrid grid = list.get(i);
				//PivAmountGrid grid = new PivAmountGrid();
				
				if(grid.getIsTax()!=null && grid.getIsTax().equals("Y"))
				{
					TaxMaster taxMaster = getTaxMasterById(grid.getAcCd());
					if(taxMaster!=null)
						grid.setTaxValue(taxMaster.getTaxVal().doubleValue());
					//m.setAcNm("xxxxxx");
					//taxAcctList.add(m);
				}
				//
				//Iterator s1 = m.values().iterator();
				//list.add(grid);
			}
			
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.it.dashboard.master.repo.MasterDao#getStatus(java.lang.String)
	 */
	@Override
	public String getStatusDescription(String status, String appName) {
		log.error("$$$$$$$$$ getStatusDescription");
		String description = "";
		try {
			String qryStr = "SELECT p.description1 FROM PivActivity p WHERE p.activityCode = :status ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("status", status);
			//String description = (String) query.getSingleResult();
			List list = query.getResultList();
			
			if (list==null || list.size() == 0 || list.size() > 1)
				description =  null;
	        else if (list.size() == 1)
	        	description =  (String)list.get(0);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.it.dashboard.master.repo.MasterDao#getPivTypeDescription(java.lang.String)
	 */
	@Override
	public String getPivTypeDescription(String pivType, String appName) {
		log.error("$$$$$$$$$ getPivTypeDescription");
		try {
			String qryStr = "SELECT g.titleNm FROM Gltitlm g WHERE trim(g.titleCd) = :pivType and g.status=:status ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("pivType", pivType);
			query.setParameter("status", new BigDecimal(2));
			String description = (String) query.getSingleResult();
			return description;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", })
	@Override
	public String getPivTypeDigit(String titleCd, String appName) {
		log.error("$$$$$$$$$ getPivTypeDigit");
		try {
			String qryStr = "SELECT a.pivCd FROM Gltitlm a WHERE a.titleCd = :titleCd";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("titleCd", titleCd);
			List<String> list = query.getResultList();
			if (list.isEmpty())
				return null;
			else if (list.size() == 1)
				return list.get(0);
			throw new NonUniqueResultException();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.it.dashboard.master.repo.MasterDao#getActivity()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PivActivity> getActivity(String appName) {
		log.error("$$$$$$$$$ findByDeptId");
		try {
			String qryStr = "SELECT p FROM PivActivity p where description2 is not null order by sortKey";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			List<PivActivity> list = query.getResultList();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<KeyValue> getBankList(String appName) {
		log.error("$$$$$$$$$ getBankList");
		try {
			String qryStr = "SELECT new com.it.dashboard.master.domain.KeyValue(bankCode,bankName) FROM Bank where isBank = 'Y' ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			List<KeyValue> list = query.getResultList();
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		    

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<KeyValue> getAgentList(String appName) {
		log.error("$$$$$$$$$ getAgentList");
		try {
			String qryStr = "SELECT new com.it.dashboard.master.domain.KeyValue(agentCode,agentName) FROM Agent  ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//query.setParameter("provinceCode", provinceCode);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			List<KeyValue> list = query.getResultList();
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		    

	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getAgentListMap(String appName) 
	{
		log.error("$$$$$$$$$ getAgentListMap");
	   // create empty map to store results in. If we don't find results, an empty hashmap will be returned
	   Map<String, String> results = new LinkedHashMap<String, String>();

	   
	   // Construct and run query
	   String jpaQuery = "select agentCode,agentName from Agent where status = 'Y' order by sortKey, agentName ";
	   List<Object[]> resultList = getEntityManager(appName).createQuery(jpaQuery).getResultList();
	   
	   //System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));

	   // Place results in map
	   for (Object[] agents: resultList) {
	      results.put((String)agents[0], (String)agents[1]);
	   }

	   return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getBankListMap(String appName) 
	{
		log.error("$$$$$$$$$ getBankListMap");
	   // create empty map to store results in. If we don't find results, an empty hashmap will be returned
	   Map<String, String> results = new LinkedHashMap<String, String>();

	   
	   // Construct and run query
	   String jpaQuery = "SELECT bankCode, bankName FROM Bank where isBank = 'Y'  ";
	   List<Object[]> resultList = getEntityManager(appName).createQuery(jpaQuery).getResultList();
	   
	   //System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));

	   // Place results in map
	   for (Object[] agents: resultList) {
	      results.put((String)agents[0], (String)agents[1]);
	   }

	   return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getBankBranchListMap(String bankCode, String appName) 
	{
		log.error("$$$$$$$$$ getBankBranchListMap");
	   // create empty map to store results in. If we don't find results, an empty hashmap will be returned
		Map<String, String> results = new LinkedHashMap<String, String>();

	   
	   // Construct and run query
	   String qryStr = "SELECT m.id.branchCode,branchName FROM BankBranch  m WHERE m.id.bankCode = :bankCode order by m.branchName";
	   Query query = getEntityManager(appName).createQuery(qryStr);
	   //System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
		query.setParameter("bankCode", bankCode);
	   List<Object[]> resultList = query.getResultList();

	   // Place results in map
	   for (Object[] agents: resultList) {
		   
	      results.put((String)agents[0], (String)agents[1]);
	   }
	   
	   
	   return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getAgentBranchListMap(String agentCode, String appName) 
	{
		log.error("$$$$$$$$$ getAgentBranchListMap");
	   // create empty map to store results in. If we don't find results, an empty hashmap will be returned
	   Map<String, String> results = new LinkedHashMap<String, String>();

	   
	   // Construct and run query
	   String jpaQuery = "SELECT m.id.agentBranchCode||'###'||deptId,agentBranchName||'('||deptId||')' FROM AgentBranch  m WHERE m.agentCode = :agentCode and status = 'Y' order by m.agentBranchName ";
	   Query query = getEntityManager(appName).createQuery(jpaQuery);
	  // System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
	   query.setParameter("agentCode", agentCode);
	   List<Object[]> resultList = query.getResultList();
		
	   // Place results in map
	   for (Object[] agents: resultList) {
	      results.put((String)agents[0], (String)agents[1]);
	   }

	   return results;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<KeyValue> getAgentBranchList(String agentCode, String appName) {
		log.error("$$$$$$$$$ getAgentBranchList");
		try {
			String qryStr = "SELECT new com.it.dashboard.master.domain.KeyValue(m.id.agentBranchCode,agentBranchName) FROM AgentBranch  m WHERE m.agentCode = :agentCode order by m.agentBranchName";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("agentCode", agentCode);
			List<KeyValue> list = query.getResultList();
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		    

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<KeyValue> getBankBranchList(String bankCode, String appName) {
		log.error("$$$$$$$$$ getBankBranchList");
		try {
			String qryStr = "SELECT new com.it.dashboard.master.domain.KeyValue(m.id.branchCode,branchName) FROM BankBranch  m WHERE m.bankCode = :bankCode order by m.branchName";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("bankCode", bankCode);
			List<KeyValue> list = query.getResultList();
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		    

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Agent getAgentById(String agentCode, String appName) {
		log.error("$$$$$$$$$ getAgentById");
		Agent agent = null;
		try {
			String qryStr = "SELECT p FROM Agent p where p.agentCode = :agentCode ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("agentCode", agentCode);
			//Agent agent = (Agent)query.getSingleResult();
			List<Agent> list = query.getResultList();
			if (list==null || list.size() == 0 || list.size() > 1)
				agent =  null;
	        else if (list.size() == 1)
	        	agent =  list.get(0);
			return agent;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AgentBranch getAgentBranchById(String agentCode,String agentBranchCode, String appName) {
		log.error("$$$$$$$$$ getAgentBranchById");
		AgentBranch agentBranch = null;
		try {
			String qryStr = "SELECT p FROM AgentBranch p where p.agentCode = :agentCode and p.agentBranchCode = :agentBranchCode ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("agentCode", agentCode);
			query.setParameter("agentBranchCode", agentBranchCode);
			//AgentBranch agent = (AgentBranch)query.getSingleResult();
			List<AgentBranch> list = query.getResultList();
			if (list==null || list.size() == 0 || list.size() > 1)
				agentBranch =  null;
	        else if (list.size() == 1)
	        	agentBranch =  (AgentBranch)list.get(0);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			//return null;
		}
		return agentBranch;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TaxMaster getTaxMasterById(String accountCode, String appName) {
		log.error("$$$$$$$$$ getTaxMasterById");
		TaxMaster taxMaster = null;
		try {
			String qryStr = "select t from TaxMaster t "+
							"where t.acCd = :accountCode "+
							"and CURRENT_DATE > t.effectFrom "+
							"and t.effectTo is null ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("accountCode", accountCode);
			List results = query.getResultList();
			if (results.isEmpty()) {
			    return null; // handle no-results case
			} else {
			    return (TaxMaster)results.get(0);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getCompIdToAgent(String agentCode, String appName) {
		log.error("$$$$$$$$$ getCompIdToAgent");
		try {
			String qryStr = "SELECT a.compId FROM Agent a WHERE a.agentCode = :agentCode ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("agentCode", agentCode);
			String compId = (String) query.getSingleResult();
			return compId;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AgentBranch getAgentBranchByDeptId(List deptIdList, String appName) {
		log.error("$$$$$$$$$ getAgentBranchByDeptId");
		AgentBranch agentBranch = null;
		try {
			String qryStr = "SELECT p FROM AgentBranch p where p.deptId in (:agentCode) order by deptId ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("deptIdList", deptIdList);
			//AgentBranch agent = (AgentBranch)query.getSingleResult();
			List<AgentBranch> list = query.getResultList();
			if (list==null || list.size() == 0 || list.size() > 1)
				agentBranch =  null;
	        else if (list.size() == 1)
	        	agentBranch =  (AgentBranch)list.get(0);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return agentBranch;
	}
	
	@Override
	public boolean isVatLiablePIV(String pivType, String appName) {
		log.error("$$$$$$$$$ isVatLiablePIV");
		boolean isVatLiable = false;
		try {
			String qryStr = "SELECT g.id.acCd FROM Glacgrpm g WHERE g.id.titleCd = :pivType ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("pivType", pivType);
			List results = query.getResultList();
			for(int i=0;i<results.size();i++)
			{
				
				String acCd = (String)results.get(i);
				if(acCd.trim().equals("L5225"))
				{
					isVatLiable = true;
					break;
				}
			}
			return isVatLiable;
		} catch (Exception ex) {
			ex.printStackTrace();
			return isVatLiable;
		}
	}
	
	
	@SuppressWarnings({ "unchecked", })
	@Override
	public String getCostCenterToDepotCode(String areaCode, String depotCode, String appName) {
		log.error("$$$$$$$$$ getCostCenterToDepotCode");
		try {
			String qryStr = " Select g.deptId From Gldeptin g, Gldeptm m "+
							" Where g.areaCode = :areaCode And g.deptCode = :depotCode "+
							" and g.deptId = m.deptId order by m.entDt desc";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("areaCode", areaCode);
			query.setParameter("depotCode", depotCode);
			List<String> list = query.getResultList();
			
			if (list.isEmpty())
				return "260.20";
			else 
				return list.get(0);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", })
	@Override
	public String getCostCenterToDepotCode1(String areaCode, String depotCode, String appName) {
		log.error("$$$$$$$$$ getCostCenterToDepotCode1");
		try {
			String qryStr = " Select g.deptId From Gldeptin g "+
							" Where g.areaCode = :areaCode And g.deptCode = :depotCode "+
							" order by g.deptId desc";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
			query.setParameter("areaCode", areaCode);
			query.setParameter("depotCode", depotCode);
			List<String> list = query.getResultList();
			
			if (list.isEmpty())
				return "260.20";
			else 
				return list.get(0);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public AppSubtypeCharge getAppSubtypeCharge(AppSubtypeChargePK id, String appName) {
		log.error("$$$$$$$$$ getAppSubtypeCharge");
		//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
		return getEntityManager(appName).find(AppSubtypeCharge.class, id);
		

	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Spstdrat getSpstdrat(String rateCode,
			String year, String appName)
			 {
		log.error("$$$$$$$$$ getSpstdrat");
		String qryStr = "SELECT c FROM Spstdrat c WHERE c.id.rateCode=:rateCode AND c.id.year=:year  "; 
		Query query = getEntityManager(appName).createQuery(qryStr);
		//System.out.println("em obj MasterDaoImpl addPivServiceReq "+getEntityManager(appName));
		query.setParameter("rateCode", rateCode);
		query.setParameter("year", year);
		List<Spstdrat> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
}
