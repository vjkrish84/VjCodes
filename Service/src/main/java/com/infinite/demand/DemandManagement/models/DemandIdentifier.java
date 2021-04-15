package com.infinite.demand.DemandManagement.models;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class DemandIdentifier {

	@NotNull
	@Size(max = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long demandid;

	@NotNull
	@Size(max = 20)
	private String demandkey;

	public DemandIdentifier() {
	}

	public DemandIdentifier(Long demandid, String demandkey) {
		this.demandid = demandid;
		this.demandkey = demandkey;
	}

	public Long getDemandid() {
		return demandid;
	}

	public void setDemandid(Long demandid) {
		this.demandid = demandid;
	}

	public String getDemandkey() {
		return demandkey;
	}

	public void setDemandkey(String demandkey) {
		this.demandkey = demandkey;
	}

}
