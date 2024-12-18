package com.it.dashboard.admin.repo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.domain.Sauserm;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;

@Repository

public class SecurityDaoImpl extends EmSelector implements SecurityDao {
	
	//@PersistenceContext(unitName = "persistenceUnitPIV")
    //private EntityManager getEntityManager(appName);
	private static final Log log = LogFactory.getLog(SecurityDaoImpl.class);
	
	/*
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	@Override
	public String getPassword(String userName, String appName) {
		String pwd = null;
		try
		{
		
		log.error("$$$$$$$$$ getPassword start");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitPIV");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		//entityManager.getTransaction().begin();
		
		String qryStr = "SELECT s.pswrd FROM Sauserm s WHERE TRIM(s.userId)=:userName";
		//System.out.println("em obj getPassword for "+userName+" "+getEntityManager(appName));
		//Query query = entityManager.createQuery(qryStr);
		Query query = entityManager.createQuery(qryStr);
		query.setParameter("userName", userName.toUpperCase());
		List<String> list = query.getResultList();
		
		System.out.println("getPassword value "+list.get(0));
		if (list.isEmpty())
			pwd =  null;
        else if (list.size() == 1)
        	pwd =  list.get(0);
		
		//entityManager.remove(emp);
		//entityManager.getTransaction().commit();

		// close the entity manager
		entityManager.close();
		entityManagerFactory.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pwd;
		
        
      
        

	}
	*/
	
	@SuppressWarnings("unchecked")
	@Override
	public String getPassword(String userName, String appName) {
		String pwd = null;
		log.error("$$$$$$$$$ getPassword start");
		
		String qryStr = "SELECT s.pswrd FROM Sauserm s WHERE TRIM(s.userId)=:userName";
		
		Query query = getEntityManager(appName).createQuery(qryStr);
		query.setParameter("userName", userName.toUpperCase());
		List<String> list = query.getResultList();
		
		if (list==null || list.size() == 0 || list.size() > 1)
			pwd =  null;
        else if (list.size() == 1)
        	pwd =  list.get(0);
		
		log.error("$$$$$$$$$ getPassword start end");
		return pwd;
		
        
      
        

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAuthorizedCostCenters(String userName, String appName) {
		log.error("$$$$$$$$$ getAuthorizedCostCenters start");
		
		//EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitPIV");
		//EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String qryStr = "SELECT TRIM(s.id.deptId) FROM Sausrdpm s WHERE TRIM(s.id.userId)=:userName and status = 2 ";
		Query query = getEntityManager(appName).createQuery(qryStr);
	//	Query query = entityManager.createQuery(qryStr);
		query.setParameter("userName", userName.toUpperCase());
		List<String> list = query.getResultList();
		
		//entityManager.close();
		//entityManagerFactory.close();
		log.error("$$$$$$$$$ end getAuthorizedCostCenters end");
		return list;
	}

	@Override
	public Boolean validateLogin(String userName, String password, String appName) {
		return validate(userName.trim().toUpperCase(),password, appName);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public String getCostCenter(String userName, String appName) {
		log.error("$$$$$$$$$ getCostCenter start");
		String costCenter = null;
		
		//EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitPIV");
		//EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String qryStr = "SELECT TRIM(rptUser) FROM Sauserm s WHERE TRIM(s.userId)=:userName";
		Query query = getEntityManager(appName).createQuery(qryStr);
		//Query query = entityManager.createQuery(qryStr);
		
		query.setParameter("userName", userName.toUpperCase());
		List<String> list = query.getResultList();
		if (list==null || list.size() == 0 || list.size() > 1)
			costCenter = null;
        else if (list.size() == 1)
        	costCenter = list.get(0).trim();
		
		/*entityManager.close();
		entityManagerFactory.close();*/
		log.error("$$$$$$$$$ end getCostCenter end");
		return costCenter ;
       
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAuthorizedLevel(String userName, String appName) {
		String level = null;
		log.error("$$$$$$$$$ getAuthorizedLevel start");
		
		/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitPIV");
		EntityManager entityManager = entityManagerFactory.createEntityManager();*/
		
		String qryStr = "SELECT userLevel FROM Sauserm s WHERE TRIM(s.userId)=:userName";
		Query query = getEntityManager(appName).createQuery(qryStr);
		//Query query = entityManager.createQuery(qryStr);
		query.setParameter("userName", userName.toUpperCase());
		List<String> list = query.getResultList();
		if (list==null || list.size() == 0 || list.size() > 1)
			level = null;
        else if (list.size() == 1)
        	level = list.get(0);
		
		/*entityManager.close();
		entityManagerFactory.close();
		*/
		log.error("$$$$$$$$$ end getAuthorizedLevel end");
        return level;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserList(String deptId, String userLevel, String appName) {
		log.error("$$$$$$$$$ getUserList start");
		
		/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitPIV");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		*/
		String qryStr = "SELECT TRIM(userId) FROM Sauserm s WHERE TRIM(s.rptUser)=:rptUser AND s.userLevel =:userLevel";
		Query query = getEntityManager(appName).createQuery(qryStr);
		//Query query = entityManager.createQuery(qryStr);
		query.setParameter("rptUser", deptId);
		query.setParameter("userLevel", userLevel);
		List<String> list = query.getResultList();
		
		/*entityManager.close();
		entityManagerFactory.close();*/
		log.error("$$$$$$$$$ end getUserList end");
		return list;
        
	}
	
	//@SuppressWarnings("unchecked")
	@Override
	public void updateUserLevel(String deptId, String userId, String userLevel, String appName) {
		log.error("$$$$$$$$$ updateUserLevel start");
		
		/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitPIV");
		EntityManager entityManager = entityManagerFactory.createEntityManager();*/
		
		String qryStr = "UPDATE Sauserm s SET s.userLevel =:userLevel WHERE TRIM(s.userId)=:userId AND TRIM(s.rptUser)=:rptUser";
		Query query = getEntityManager(appName).createQuery(qryStr);
		//Query query = entityManager.createQuery(qryStr);
		query.setParameter("rptUser", deptId);
		query.setParameter("userId", userId);
		query.setParameter("userLevel", userLevel);
		query.executeUpdate();
		
		/*entityManager.close();
		entityManagerFactory.close();*/
		log.error("$$$$$$$$$ end updateUserLevel end");
        
	}
	
	
	


	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextLogId(String logIdPrefix, String appName) {
		String nextId;
		log.error("$$$$$$$$$ getNextLogId start");
		
		/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitPIV");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		*/
		String sequence=null;
    	String like=logIdPrefix+"%";
    	String qryStr="select a.logId from LoginDetail a " +
    			"where a.logId like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(appName).createQuery(qryStr);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	//Query query = entityManager.createQuery(qryStr);
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		sequence=sequence.substring(14);//total 18 chars  year 12 
    		Integer i=Integer.parseInt(sequence)+1;
    		sequence=i.toString();
    		
    	}else{
    		sequence="0001";
    		
    	}
    	
    	if(sequence.length()==1)
    		nextId = logIdPrefix+"000"+sequence;
    	else if (sequence.length()==2)
    		nextId = logIdPrefix+"00"+sequence;
    	else if (sequence.length()==3)
    		nextId = logIdPrefix+"0"+sequence;
    	else 
    		nextId = logIdPrefix+sequence;
    	
    	/*entityManager.close();
		entityManagerFactory.close();*/
    	log.error("$$$$$$$$$ end getNextLogId end");
    	return nextId;
	}
	
	/*
	 * 
	 * encription
	 * 
	 * 
	 */
	/**
	 * This is the public interface to validated user name an password
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private  Boolean validate(String userName, String password, String appName) {

		Boolean valiity = false;

		Double M_PS = 0d;
		Double M_PU = 0d;
		Double M_P = 0d;

		String m_User = CheckPass(userName);

		if (!(m_User.trim().equals(""))) {
			String m_Pass = CheckPass(password);
			if (!(m_Pass.trim().equals(""))) {
				M_PS = Double.parseDouble(m_Pass);
				M_PU = Double.parseDouble(m_User);

				M_P = ((M_PU + M_PS) / 100005600000.9987);

				if (chkPassworEquality(M_P.toString(), userName, appName)) {
					valiity = true;
				}

			}
		}

		return valiity;
	}

	/**
	 * This will check the password an the encrypted value.
	 * 
	 * @param M_P
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	private boolean chkPassworEquality(String M_P, String userName, String appName)
			 {
		String password=getPassword(userName, appName);
		
		
		boolean result = false;
		
		if (password!=null) 
		{
			Double dbVal = Double.parseDouble(password);
			Double enteredVal = Double.parseDouble(M_P);

			
			Double roundUp = 0d;

			DecimalFormat twoDForm = new DecimalFormat("#.########");
			roundUp = Double.valueOf(twoDForm.format(enteredVal / dbVal));

			if (roundUp.equals(1.00000000)) {
				result = true;
				
			}

		}
		

		return result;
	}

	/**
	 * 
	 * @param literal
	 * @return
	 */
	private String CheckPass(String literal) {

		Integer PAS_LEN = 1;
		String M_PASS = "";
		String M_PASS1 = "";

		char[] cArray = literal.trim().toCharArray();

		ArrayList<String> strList = new ArrayList<String>();

		for (int i = 0; i < cArray.length; i++) {
			strList.add(Character.toString(cArray[i]));
		}

		int cArrayLength = cArray.length;

		Long LEN_TOT = 0l;

		while (PAS_LEN <= 10) {
			Long PAS_CHAR = 0l;
			Long POS_CHAR = 0l;

			if (cArrayLength < PAS_LEN) {
				if (PAS_LEN == 0) {
					PAS_CHAR = 9999l;
				} else {
					PAS_CHAR = LEN_TOT;
				}
			} else {
				PAS_CHAR = decrypt(strList.get(PAS_LEN - 1));
			}
			if (PAS_CHAR.equals(0)) {
				M_PASS = "";
				M_PASS1 = M_PASS;
				break;
			}

			POS_CHAR = decrypt(PAS_LEN.toString());

			Long ENC_CHAR = PAS_CHAR + POS_CHAR;
			LEN_TOT = LEN_TOT + ENC_CHAR;

			if (M_PASS.equals("")) {
				M_PASS = ENC_CHAR.toString();
			} else {
				M_PASS = M_PASS.concat(ENC_CHAR.toString());
			}
			PAS_LEN = PAS_LEN + 1;
		}

		M_PASS1 = M_PASS;

		return M_PASS1;

	}

	/**
	 * Actual decrypting happens here. !! Values are directly taken from MITFIN
	 * Service.
	 * 
	 * @param IN_STR
	 * @return
	 */
	private Long decrypt(String IN_STR) {

		Long OUT_CHAR = 0l;

		if (IN_STR.equals("A"))
			OUT_CHAR = 2457l;
		else if (IN_STR.equals("B"))
			OUT_CHAR = 2459l;
		else if (IN_STR.equals("C"))
			OUT_CHAR = 2460l;
		else if (IN_STR.equals("D"))
			OUT_CHAR = 2461l;
		else if (IN_STR.equals("E"))
			OUT_CHAR = 2462l;
		else if (IN_STR.equals("F"))
			OUT_CHAR = 2463l;
		else if (IN_STR.equals("G"))
			OUT_CHAR = 2464l;
		else if (IN_STR.equals("H"))
			OUT_CHAR = 2465l;
		else if (IN_STR.equals("I"))
			OUT_CHAR = 2466l;
		else if (IN_STR.equals("J"))
			OUT_CHAR = 2468l;
		else if (IN_STR.equals("K"))
			OUT_CHAR = 2469l;
		else if (IN_STR.equals("L"))
			OUT_CHAR = 2470l;
		else if (IN_STR.equals("M"))
			OUT_CHAR = 2471l;
		else if (IN_STR.equals("N"))
			OUT_CHAR = 2472l;
		else if (IN_STR.equals("O"))
			OUT_CHAR = 2472l;
		else if (IN_STR.equals("P"))
			OUT_CHAR = 2473l;
		else if (IN_STR.equals("Q"))
			OUT_CHAR = 2473l;
		else if (IN_STR.equals("R"))
			OUT_CHAR = 2474l;
		else if (IN_STR.equals("S"))
			OUT_CHAR = 2475l;
		else if (IN_STR.equals("T"))
			OUT_CHAR = 2476l;
		else if (IN_STR.equals("U"))
			OUT_CHAR = 2478l;
		else if (IN_STR.equals("V"))
			OUT_CHAR = 2478l;
		else if (IN_STR.equals("W"))
			OUT_CHAR = 2479l;
		else if (IN_STR.equals("X"))
			OUT_CHAR = 2481l;
		else if (IN_STR.equals("Y"))
			OUT_CHAR = 2482l;
		else if (IN_STR.equals("Z"))
			OUT_CHAR = 2483l;
		else if (IN_STR.equals("a"))
			OUT_CHAR = 4055l;
		else if (IN_STR.equals("b"))
			OUT_CHAR = 4056l;
		else if (IN_STR.equals("c"))
			OUT_CHAR = 4057l;
		else if (IN_STR.equals("d"))
			OUT_CHAR = 4060l;
		else if (IN_STR.equals("e"))
			OUT_CHAR = 4061l;
		else if (IN_STR.equals("f"))
			OUT_CHAR = 4063l;
		else if (IN_STR.equals("g"))
			OUT_CHAR = 4064l;
		else if (IN_STR.equals("h"))
			OUT_CHAR = 4065l;
		else if (IN_STR.equals("i"))
			OUT_CHAR = 4066l;
		else if (IN_STR.equals("j"))
			OUT_CHAR = 4068l;
		else if (IN_STR.equals("k"))
			OUT_CHAR = 4069l;
		else if (IN_STR.equals("l"))
			OUT_CHAR = 5470l;
		else if (IN_STR.equals("m"))
			OUT_CHAR = 5471l;
		else if (IN_STR.equals("n"))
			OUT_CHAR = 5472l;
		else if (IN_STR.equals("o"))
			OUT_CHAR = 5472l;
		else if (IN_STR.equals("p"))
			OUT_CHAR = 5473l;
		else if (IN_STR.equals("q"))
			OUT_CHAR = 5473l;
		else if (IN_STR.equals("r"))
			OUT_CHAR = 5474l;
		else if (IN_STR.equals("s"))
			OUT_CHAR = 5475l;
		else if (IN_STR.equals("t"))
			OUT_CHAR = 5476l;
		else if (IN_STR.equals("u"))
			OUT_CHAR = 5478l;
		else if (IN_STR.equals("v"))
			OUT_CHAR = 5478l;
		else if (IN_STR.equals("w"))
			OUT_CHAR = 5479l;
		else if (IN_STR.equals("x"))
			OUT_CHAR = 5481l;
		else if (IN_STR.equals("y"))
			OUT_CHAR = 5482l;
		else if (IN_STR.equals("z"))
			OUT_CHAR = 9999l;
		else if (IN_STR.equals("0"))
			OUT_CHAR = 2502l;
		else if (IN_STR.equals("1"))
			OUT_CHAR = 2503l;
		else if (IN_STR.equals("2"))
			OUT_CHAR = 2504l;
		else if (IN_STR.equals("3"))
			OUT_CHAR = 2505l;
		else if (IN_STR.equals("4"))
			OUT_CHAR = 2506l;
		else if (IN_STR.equals("5"))
			OUT_CHAR = 2507l;
		else if (IN_STR.equals("6"))
			OUT_CHAR = 2508l;
		else if (IN_STR.equals("7"))
			OUT_CHAR = 2509l;
		else if (IN_STR.equals("8"))
			OUT_CHAR = 2561l;
		else if (IN_STR.equals("9"))
			OUT_CHAR = 2562l;
		else if (IN_STR.equals("10"))
			OUT_CHAR = 2563l;
		else
			OUT_CHAR = 0l;

		return OUT_CHAR;

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public Sauserm getSauserm(String userName, String appName) {
		log.error("$$$$$$$$$ getSauserm start");
		Sauserm sauserm = null;
		/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitPIV");
		EntityManager entityManager = entityManagerFactory.createEntityManager();*/
		
		String qryStr = "SELECT s FROM Sauserm s WHERE TRIM(s.userId)=:userName";
		Query query = getEntityManager(appName).createQuery(qryStr);
		//Query query = entityManager.createQuery(qryStr);
		
		query.setParameter("userName", userName.toUpperCase());
		List<Sauserm> list = query.getResultList();
		
		if (list==null || list.size() == 0 || list.size() > 1)
			sauserm = null;
        else if (list.size() == 1)
        	sauserm = list.get(0);
		
		/*entityManager.close();
		entityManagerFactory.close();*/
		log.error("$$$$$$$$$ end getSauserm end");
		return sauserm;
	}
	
	
	
}
