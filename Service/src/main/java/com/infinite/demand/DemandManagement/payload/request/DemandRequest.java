package com.infinite.demand.DemandManagement.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DemandRequest {
	
	private Long demandmasterid;

	
	private String demandkey;

	
	private String demandvalue;

	

	private String active;
	
	
	private String createdby;
	

	private String createddate;
	
	
	private String modifiedby;
	

	private String modifieddate;
	
	public Long getDemandmasterid() {
		return demandmasterid;
	}

	public void setDemandmasterid(Long demandmasterid) {
		this.demandmasterid = demandmasterid;
	}

	public String getDemandkey() {
		return demandkey;
	}

	public void setDemandkey(String demandkey) {
		this.demandkey = demandkey;
	}

	public String getDemandvalue() {
		return demandvalue;
	}

	public void setDemandvalue(String demandvalue) {
		this.demandvalue = demandvalue;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {		
		if (createddate != null)
			this.createddate = createddate;
		else
			this.createddate = new java.util.Date().toString();
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(String modifieddate) {
		if (modifieddate != null)
			this.modifieddate = modifieddate;
		else
			this.modifieddate = new java.util.Date().toString();
	}

	
}
