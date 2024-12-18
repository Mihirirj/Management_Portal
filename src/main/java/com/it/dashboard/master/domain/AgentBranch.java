package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AGENT_BRANCH database table.
 * 
 */
@Entity
@Table(name="AGENT_BRANCH")
@NamedQuery(name="AgentBranch.findAll", query="SELECT a FROM AgentBranch a")
public class AgentBranch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AGENT_BRANCH_CODE")
	private String agentBranchCode;

	@Column(name="AGENT_CODE")
	private String agentCode;
	
	@Column(name="AGENT_BRANCH_NAME")
	private String agentBranchName;
	
	@Column(name="DEPT_ID")
	private String deptId;

	private String status;

	//bi-directional many-to-one association to Agent
	/*@ManyToOne
	@JoinColumn(name="AGENT_CODE")
	private Agent agent;*/

	public AgentBranch() {
	}

	public String getAgentBranchCode() {
		return this.agentBranchCode;
	}

	public void setAgentBranchCode(String agentBranchCode) {
		this.agentBranchCode = agentBranchCode;
	}

	public String getAgentBranchName() {
		return this.agentBranchName;
	}

	public void setAgentBranchName(String agentBranchName) {
		this.agentBranchName = agentBranchName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
/*
	public Agent getAgent() {
		return this.agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
*/
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	
	

}