package com.emergya.smc.tj.webapp.dto;

import java.io.Serializable;
import java.util.List;

import com.emergya.smc.tj.model.Issue;
import com.emergya.smc.tj.model.Stop;


/**
*
* @author marcos
*/
public class TrafficJamRoutingRequest implements Serializable {

	private Stop origin;
	private Stop target;
	private List<Issue> issues;
       
	
	public Stop getOrigin() {
		return origin;
	}
	public void setOrigin(Stop origin) {
		this.origin = origin;
	}
	public Stop getTarget() {
		return target;
	}
	public void setTarget(Stop target) {
		this.target = target;
	}
	public List<Issue> getIssues() {
		return issues;
	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

		
}
