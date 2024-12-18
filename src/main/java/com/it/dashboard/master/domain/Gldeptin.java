package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the GLDEPTIN database table.
 * 
 */
@Entity
@NamedQuery(name="Gldeptin.findAll", query="SELECT g FROM Gldeptin g")
public class Gldeptin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="AREA_CE_NO")
	private String areaCeNo;

	@Column(name="AREA_CODE")
	private String areaCode;

	@Column(name="AREA_EE_NO")
	private String areaEeNo;

	@Column(name="BANK_CODE")
	private String bankCode;

	@Column(name="BRANCH_CODE")
	private String branchCode;

	@Column(name="BULK_SUPPLIER_ADD")
	private String bulkSupplierAdd;

	@Column(name="BULK_SUPPLIER_NAME")
	private String bulkSupplierName;

	@Column(name="BULK_SUPPLIER_TEL")
	private BigDecimal bulkSupplierTel;

	@Column(name="DEPT_ADD")
	private String deptAdd;

	@Column(name="DEPT_AREA")
	private String deptArea;

	@Column(name="DEPT_CODE")
	private String deptCode;

	@Column(name="DEPT_FULL_NAME")
	private String deptFullName;

	@Column(name="DEPT_PROVINCE")
	private String deptProvince;

	@Column(name="DEPT_REGION")
	private String deptRegion;

	@Column(name="DEPT_TEL")
	private String deptTel;

	@Column(name="DEPT_TYPE")
	private String deptType;

	@Column(name="EMAIL_NO")
	private String emailNo;

	@Column(name="ES_MOBILE")
	private String esMobile;

	@Column(name="IS_PIV1_NEEDED_DEFAULT")
	private String isPiv1NeededDefault;

	@Column(name="POS_A")
	private String posA;

	@Column(name="POS_CENTER")
	private String posCenter;

	public Gldeptin() {
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getAreaCeNo() {
		return this.areaCeNo;
	}

	public void setAreaCeNo(String areaCeNo) {
		this.areaCeNo = areaCeNo;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaEeNo() {
		return this.areaEeNo;
	}

	public void setAreaEeNo(String areaEeNo) {
		this.areaEeNo = areaEeNo;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBulkSupplierAdd() {
		return this.bulkSupplierAdd;
	}

	public void setBulkSupplierAdd(String bulkSupplierAdd) {
		this.bulkSupplierAdd = bulkSupplierAdd;
	}

	public String getBulkSupplierName() {
		return this.bulkSupplierName;
	}

	public void setBulkSupplierName(String bulkSupplierName) {
		this.bulkSupplierName = bulkSupplierName;
	}

	public BigDecimal getBulkSupplierTel() {
		return this.bulkSupplierTel;
	}

	public void setBulkSupplierTel(BigDecimal bulkSupplierTel) {
		this.bulkSupplierTel = bulkSupplierTel;
	}

	public String getDeptAdd() {
		return this.deptAdd;
	}

	public void setDeptAdd(String deptAdd) {
		this.deptAdd = deptAdd;
	}

	public String getDeptArea() {
		return this.deptArea;
	}

	public void setDeptArea(String deptArea) {
		this.deptArea = deptArea;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptFullName() {
		return this.deptFullName;
	}

	public void setDeptFullName(String deptFullName) {
		this.deptFullName = deptFullName;
	}

	public String getDeptProvince() {
		return this.deptProvince;
	}

	public void setDeptProvince(String deptProvince) {
		this.deptProvince = deptProvince;
	}

	public String getDeptRegion() {
		return this.deptRegion;
	}

	public void setDeptRegion(String deptRegion) {
		this.deptRegion = deptRegion;
	}

	public String getDeptTel() {
		return this.deptTel;
	}

	public void setDeptTel(String deptTel) {
		this.deptTel = deptTel;
	}

	public String getDeptType() {
		return this.deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getEmailNo() {
		return this.emailNo;
	}

	public void setEmailNo(String emailNo) {
		this.emailNo = emailNo;
	}

	public String getEsMobile() {
		return this.esMobile;
	}

	public void setEsMobile(String esMobile) {
		this.esMobile = esMobile;
	}

	public String getIsPiv1NeededDefault() {
		return this.isPiv1NeededDefault;
	}

	public void setIsPiv1NeededDefault(String isPiv1NeededDefault) {
		this.isPiv1NeededDefault = isPiv1NeededDefault;
	}

	public String getPosA() {
		return this.posA;
	}

	public void setPosA(String posA) {
		this.posA = posA;
	}

	public String getPosCenter() {
		return this.posCenter;
	}

	public void setPosCenter(String posCenter) {
		this.posCenter = posCenter;
	}

}