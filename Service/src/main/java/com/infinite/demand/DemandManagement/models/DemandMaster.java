package com.infinite.demand.DemandManagement.models;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "demandmaster", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "demandmasterid")
		})
public class DemandMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long demandmasterid;

	@NotBlank
	@Size(max = 100)
	private String demandkey;

	@NotBlank
	@Size(max = 100)
	private String demandvalue;

	@NotBlank
	@Size(max = 1)
	private String active;
	
	@NotNull
	
	private Integer displayOrder;
	
	public Integer getDisplayOrder() {
		return displayOrder;
	}


	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}



	@NotBlank
	@Size(max = 50)
	private String createdby;
	
	@NotBlank
	@Size(max = 50)
	private String createddate;
	
	@NotBlank
	@Size(max = 50)
	private String modifiedby;
	
	@NotBlank
	@Size(max = 50)
	private String modifieddate;
	

	
	public DemandMaster() {
	}


	





	public DemandMaster(@NotBlank @Size(max = 20) String demandkey, @NotBlank @Size(max = 50) String demandvalue,
			@NotBlank @Size(max = 1) String active, @NotNull Integer displayOrder,
			@NotBlank @Size(max = 50) String createdby, @NotBlank @Size(max = 50) String createddate,
			@NotBlank @Size(max = 50) String modifiedby, @NotBlank @Size(max = 50) String modifieddate) {
		super();
		this.demandkey = demandkey;
		this.demandvalue = demandvalue;
		this.active = active;
		this.displayOrder = displayOrder;
		this.createdby = createdby;
		this.createddate = createddate;
		this.modifiedby = modifiedby;
		this.modifieddate = modifieddate;
	}


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
