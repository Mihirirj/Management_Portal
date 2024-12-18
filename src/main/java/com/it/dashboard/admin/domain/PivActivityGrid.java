/**
 * 
 */
package com.it.dashboard.admin.domain;

/**
 * @author Anobiya
 *
 */
public class PivActivityGrid {
	private String activity;
	private String action = "Deny";
	private String description;
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
