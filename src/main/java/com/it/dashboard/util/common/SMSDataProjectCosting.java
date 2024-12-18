package com.it.dashboard.util.common;

import java.io.Serializable;


public class SMSDataProjectCosting implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int recno ;
	private String tele_no  ;
	private String acct_number  ;
	private String ref_id  ;
	private String message  ;
	private String proc_date  ;
	private String shdl_date  ;
	private String expr_date  ;
	private String send_date  ;
	private String appkey   ;
	private String app_ref_key   ;
	private String area_code   ;
	private String province   ;
	private String division   ;
	private String proc_flag   ;
	private String error_flag   ;
	private int sms_len    ;
	private int no_sms    ;
	private String gate_id    ;
	private int pr_key    ;
	private String cost_cnter    ;
	private int bill_cycle    ;
	private String alias    ;
	
	public int getRecno() {
		return recno;
	}
	public void setRecno(int recno) {
		this.recno = recno;
	}
	public String getTele_no() {
		return tele_no;
	}
	public void setTele_no(String tele_no) {
		this.tele_no = tele_no;
	}
	public String getAcct_number() {
		return acct_number;
	}
	public void setAcct_number(String acct_number) {
		this.acct_number = acct_number;
	}
	public String getRef_id() {
		return ref_id;
	}
	public void setRef_id(String ref_id) {
		this.ref_id = ref_id;
	}
	
	public String getProc_date() {
		return proc_date;
	}
	public void setProc_date(String proc_date) {
		this.proc_date = proc_date;
	}
	public String getShdl_date() {
		return shdl_date;
	}
	public void setShdl_date(String shdl_date) {
		this.shdl_date = shdl_date;
	}
	public String getExpr_date() {
		return expr_date;
	}
	public void setExpr_date(String expr_date) {
		this.expr_date = expr_date;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	
	public String getApp_ref_key() {
		return app_ref_key;
	}
	public void setApp_ref_key(String app_ref_key) {
		this.app_ref_key = app_ref_key;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	
	
	public String getProc_flag() {
		return proc_flag;
	}
	public void setProc_flag(String proc_flag) {
		this.proc_flag = proc_flag;
	}
	public int getPr_key() {
		return pr_key;
	}
	public void setPr_key(int pr_key) {
		this.pr_key = pr_key;
	}
	public String getCost_cnter() {
		return cost_cnter;
	}
	public void setCost_cnter(String cost_cnter) {
		this.cost_cnter = cost_cnter;
	}
	public int getBill_cycle() {
		return bill_cycle;
	}
	public void setBill_cycle(int bill_cycle) {
		this.bill_cycle = bill_cycle;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getError_flag() {
		return error_flag;
	}
	public void setError_flag(String error_flag) {
		this.error_flag = error_flag;
	}
	public int getSms_len() {
		return sms_len;
	}
	public void setSms_len(int sms_len) {
		this.sms_len = sms_len;
	}
	public int getNo_sms() {
		return no_sms;
	}
	public void setNo_sms(int no_sms) {
		this.no_sms = no_sms;
	}
	public String getGate_id() {
		return gate_id;
	}
	public void setGate_id(String gate_id) {
		this.gate_id = gate_id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Override
	public String toString() {
		return "SmsDetailsData [recno=" + recno + ", tele_no=" + tele_no
				+ ", acct_number=" + acct_number + ", ref_id=" + ref_id
				+ ", Message=" + message + ", proc_date=" + proc_date
				+ ", shdl_date=" + shdl_date + ", expr_date=" + expr_date
				+ ", send_date=" + send_date + ", Appkey=" + appkey
				+ ", app_ref_key=" + app_ref_key + ", area_code=" + area_code
				+ ", Province=" + province + ", Division=" + division
				+ ", proc_flag=" + proc_flag + ", pr_key=" + pr_key
				+ ", cost_cnter=" + cost_cnter + ", bill_cycle=" + bill_cycle
				+ ", Alias=" + alias + "]";
	}
	
	
	

}