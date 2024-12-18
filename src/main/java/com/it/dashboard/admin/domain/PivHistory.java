package com.it.dashboard.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PIV_HISTORY database table.
 * 
 */
@Entity
@Table(name="PIV_HISTORY")
@NamedQuery(name="PivHistory.findAll", query="SELECT p FROM PivHistory p")
public class PivHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGenHist")
	@SequenceGenerator(name = "SeqGenHist", sequenceName = "PIV_HISTORY_SEQ", allocationSize = 1)
	@Column(name="HISTORY_CODE")
	private long historyCode;

	@Column(name="DEPT_ID")
	private String deptId;

	private String description;

	@Column(name="PIV_NO")
	private String pivNo;

	private String status;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	@Column(name="USER_ID")
	private String userId;

	public PivHistory() {
	}

	public long getHistoryCode() {
		return this.historyCode;
	}

	public void setHistoryCode(long historyCode) {
		this.historyCode = historyCode;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPivNo() {
		return this.pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}