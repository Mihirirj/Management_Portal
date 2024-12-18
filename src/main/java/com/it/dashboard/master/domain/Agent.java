package com.it.dashboard.master.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the AGENT database table.
 * 
 */
@Entity
@NamedQuery(name="Agent.findAll", query="SELECT a FROM Agent a")
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AGENT_CODE")
	private String agentCode;

	@Column(name="AGENT_NAME")
	private String agentName;
	
	@Column(name="COMP_ID")
	private String compId;

	@Column(name="SORT_KEY")
	private BigDecimal sortKey;
	
	private String status;

	//bi-directional many-to-one association to AgentBranch
	/*@OneToMany(mappedBy="agent")
	@JsonIgnore 
	private List<AgentBranch> agentBranches;*/

	public Agent() {
	}

	public String getAgentCode() {
		return this.agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return this.agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public List<AgentBranch> getAgentBranches() {
		return this.agentBranches;
	}

	public void setAgentBranches(List<AgentBranch> agentBranches) {
		this.agentBranches = agentBranches;
	}

	public AgentBranch addAgentBranch(AgentBranch agentBranch) {
		getAgentBranches().add(agentBranch);
		agentBranch.setAgent(this);

		return agentBranch;
	}

	public AgentBranch removeAgentBranch(AgentBranch agentBranch) {
		getAgentBranches().remove(agentBranch);
		agentBranch.setAgent(null);

		return agentBranch;
	}*/

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public BigDecimal getSortKey() {
		return sortKey;
	}

	public void setSortKey(BigDecimal sortKey) {
		this.sortKey = sortKey;
	}

}