package com.it.dashboard.clientService.domain;

import java.util.List;

public class PIVOfflineUpdateResponse{
	
    public List<PIVOfflineUpdateSummery> olnUpdt; 
    public boolean success;
	
    public List<PIVOfflineUpdateSummery> getOlnUpdt() {
		return olnUpdt;
	}
	public void setOlnUpdt(List<PIVOfflineUpdateSummery> olnUpdt) {
		this.olnUpdt = olnUpdt;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}	
    
}
