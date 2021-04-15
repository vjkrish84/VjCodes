package com.infinite.demand.DemandManagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "demanddetails", uniqueConstraints = { @UniqueConstraint(columnNames = "demandidentifier") })
public class DemandData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long demandidentifier;

	@Size(max = 50)
	@Column(name = "businessUnit")
	private String businessUnit;

	@Size(max = 50)
	private String account;

	@Size(max = 50)
	private String engagement;

	@Size(max = 50)
	private String role;

	@Size(max = 50)
	@Column(name = "total")
	private String total;

	@Size(max = 50)
	@Column(name = "country")
	private String country;

	@Size(max = 50)
	private String probability;

	@Size(max = 50)
	private String priority;

	@Size(max = 50)
	private String spoc;

	@Size(max = 50)
	@Column(name = "fullFilled")
	private String fullFilled;

	@Size(max = 50)
	@Column(name = "demandRequestDate")
	private String demandRequestDate;

	@Size(max = 50)
	@Column(name = "requestedBy")
	private String requestedBy;

	@Size(max = 50)
	@Column(name = "conduentHiringManager")
	private String conduentHiringManager;

	@Size(max = 50)
	@Column(name = "workLocation")
	private String workLocation;

	@Size(max = 50)
	@Column(name = "level")
	private String level;

	@Size(max = 50)
	@Column(name = "billRate")
	private String billRate;

	@Size(max = 50)
	@Column(name = "duration")
	private String duration;

	@Size(max = 50)
	@Column(name = "profilesCount")
	private String profilesCount;

	@Size(max = 50)
	@Column(name = "tocrid")
	private String tocrid;

	@Size(max = 50)
	private String type;

	@Size(max = 50)
	@Column(name = "positionTitle")
	private String positionTitle;
	
	@Size(max = 50)
	@Column(name = "sourceType")
	private String sourceType;
	
	@Size(max = 50)
	@Column(name = "sourceName")
	private String sourceName;

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

	

	public DemandData(@Size(max = 50) String businessUnit, @Size(max = 50) String account,
			@Size(max = 50) String engagement, @Size(max = 50) String role, @Size(max = 50) String total,
			@Size(max = 50) String country, @Size(max = 50) String probability, @Size(max = 50) String priority,
			@Size(max = 50) String spoc, @Size(max = 50) String fullFilled, @Size(max = 50) String demandRequestDate,
			@Size(max = 50) String requestedBy, @Size(max = 50) String conduentHiringManager,
			@Size(max = 50) String workLocation, @Size(max = 50) String level, @Size(max = 50) String billRate,
			@Size(max = 50) String duration, @Size(max = 50) String profilesCount, @Size(max = 50) String tocrid,
			@Size(max = 50) String type, @Size(max = 50) String positionTitle, @Size(max = 50) String sourceType,
			@Size(max = 50) String sourceName, @Size(max = 50) String eta, @Size(max = 50) String status,
			@Size(max = 50) String createdby, @Size(max = 50) String createddate, @Size(max = 50) String modifiedby,
			@Size(max = 50) String modifieddate, @Size(max = 50) String location,
			@Size(max = 1500) String jobDescription) {
		super();
		this.businessUnit = businessUnit;
		this.account = account;
		this.engagement = engagement;
		this.role = role;
		this.total = total;
		this.country = country;
		this.probability = probability;
		this.priority = priority;
		this.spoc = spoc;
		this.fullFilled = fullFilled;
		this.demandRequestDate = demandRequestDate;
		this.requestedBy = requestedBy;
		this.conduentHiringManager = conduentHiringManager;
		this.workLocation = workLocation;
		this.level = level;
		this.billRate = billRate;
		this.duration = duration;
		this.profilesCount = profilesCount;
		this.tocrid = tocrid;
		this.type = type;
		this.positionTitle = positionTitle;
		this.sourceType = sourceType;
		this.sourceName = sourceName;
		this.eta = eta;
		this.status = status;
		this.createdby = createdby;
		this.createddate = createddate;
		this.modifiedby = modifiedby;
		this.modifieddate = modifieddate;
		this.location = location;
		this.jobDescription = jobDescription;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFullFilled() {
		return fullFilled;
	}

	public void setFullFilled(String fullFilled) {
		this.fullFilled = fullFilled;
	}

	public String getDemandRequestDate() {
		return demandRequestDate;
	}

	public void setDemandRequestDate(String demandRequestDate) {
		this.demandRequestDate = demandRequestDate;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getConduentHiringManager() {
		return conduentHiringManager;
	}

	public void setConduentHiringManager(String conduentHiringManager) {
		this.conduentHiringManager = conduentHiringManager;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}



	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getBillRate() {
		return billRate;
	}

	public void setBillRate(String billRate) {
		this.billRate = billRate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getProfilesCount() {
		return profilesCount;
	}

	public void setProfilesCount(String profilesCount) {
		this.profilesCount = profilesCount;
	}

	public String getTocrid() {
		return tocrid;
	}

	public void setTocrid(String tocrid) {
		this.tocrid = tocrid;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	@Size(max = 50)
	private String eta;

	@Size(max = 50)
	private String status;

	@Size(max = 50)
	private String createdby;

	@Size(max = 50)
	private String createddate;

	@Size(max = 50)
	private String modifiedby;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobdescription) {
		this.jobDescription = jobdescription;
	}

	@Size(max = 50)
	private String modifieddate;

	@Size(max = 50)
	private String location;

	@Size(max = 4500)
	@Column(name = "jobDescription")
	private String jobDescription;

	public Long getDemandidentifier() {
		return demandidentifier;
	}

	public void setDemandidentifier(Long demandidentifier) {
		this.demandidentifier = demandidentifier;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEngagement() {
		return engagement;
	}

	public void setEngagement(String engagement) {
		this.engagement = engagement;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSpoc() {
		return spoc;
	}

	public void setSpoc(String spoc) {
		this.spoc = spoc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		this.createddate = createddate;
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
		this.modifieddate = modifieddate;
	}

	public DemandData() {

	}

}
