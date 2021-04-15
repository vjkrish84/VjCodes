package com.infinite.demand.DemandManagement.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.demand.DemandManagement.models.DemandData;
import com.infinite.demand.DemandManagement.models.DemandMaster;
import com.infinite.demand.DemandManagement.payload.request.DemandRequest;
import com.infinite.demand.DemandManagement.payload.response.MessageResponse;
import com.infinite.demand.DemandManagement.repository.DemandDataRepository;
import com.infinite.demand.DemandManagement.repository.DemandMasterRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/demand")
public class DemandController {

	@Autowired
	DemandMasterRepository demandmasterrepository;

	@Autowired
	DemandDataRepository demanddatarepository;

	@PostMapping("/createOrUpdate")
	public ResponseEntity<?> createOrUpdateDemandMaster(@Valid @RequestBody DemandMaster demandrequest) {
		Optional<DemandMaster> objMasterOpt = null;
		DemandMaster objMaster = null;
		if (demandrequest.getDemandmasterid() != null
				&& demandmasterrepository.existsById(demandrequest.getDemandmasterid())) {
			if (demandrequest.getModifiedby() == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("ModifiedBy is Required"));
			}
			objMasterOpt = demandmasterrepository.findById(demandrequest.getDemandmasterid());
			if (objMasterOpt.isPresent()) {
				objMaster = objMasterOpt.get();
				objMaster.setActive(demandrequest.getActive());
				objMaster.setModifiedby(demandrequest.getModifiedby());
			}

		} else {
			objMaster = new DemandMaster(demandrequest.getDemandkey(), demandrequest.getDemandvalue(),
					demandrequest.getActive(), demandrequest.getDisplayOrder(), demandrequest.getCreatedby(),
					demandrequest.getCreateddate(), demandrequest.getModifiedby(), demandrequest.getModifieddate());
		}
		if (objMaster != null)
			demandmasterrepository.save(objMaster);
		return ResponseEntity.ok(new MessageResponse("Demand registered successfully!"));
	}

	private void CreateAndUpdateDemandMaster(String demandkey, String demandvalue) {
		if (demandkey != null && demandvalue != null
				&& demandmasterrepository.findByDemandkeyAndDemandvalue(demandkey, demandvalue).size() == 0) {
			List<DemandMaster> objMasterOpt = demandmasterrepository.findByDemandkey(demandkey);
			if (!objMasterOpt.isEmpty()) {
				Integer dispvalue = null;
				for (DemandMaster objtempmaster : objMasterOpt) {
					dispvalue = new Integer(objtempmaster.getDisplayOrder());
					System.out.println("dispvalue " + dispvalue);
					break;
				}
				DemandMaster objMaster = new DemandMaster();
				objMaster.setDemandkey(demandkey);
				objMaster.setDemandvalue(demandvalue);
				objMaster.setActive("Y");
				objMaster.setCreatedby("System");
				objMaster.setDisplayOrder(dispvalue);
				objMaster.setCreateddate(new java.util.Date().toString());
				objMaster.setModifiedby("System");
				objMaster.setModifieddate(new java.util.Date().toString());
				createOrUpdateDemandMaster(objMaster);
			}
		}

	}

	@PostMapping("/createOrUpdateDemandData")
	public ResponseEntity<?> createOrUpdateDemandData(@Valid @RequestBody DemandData demanddatarequest) {

		String businessunit = demanddatarequest.getBusinessUnit();
		String account = demanddatarequest.getAccount();
		String engagement = demanddatarequest.getEngagement();
		String role = demanddatarequest.getRole();
		String positionTitle = demanddatarequest.getPositionTitle();
		String country = demanddatarequest.getCountry();
		String location = demanddatarequest.getLocation();
		String total = demanddatarequest.getTotal();
		String probability = demanddatarequest.getProbability();
		String priority = demanddatarequest.getPriority();
		String spoc = demanddatarequest.getSpoc();
		String fullfilled = demanddatarequest.getFullFilled();
		String requestedby = demanddatarequest.getRequestedBy();
		String demandrequestdate = demanddatarequest.getDemandRequestDate();
		String conduenthiringmanager = demanddatarequest.getConduentHiringManager();
		String worklocation = demanddatarequest.getWorkLocation();
		String level = demanddatarequest.getLevel();
		String billrate = demanddatarequest.getBillRate();
		String duration = demanddatarequest.getDuration();
		String profilesCount = demanddatarequest.getProfilesCount();
		String tocrid = demanddatarequest.getTocrid();
		String type = demanddatarequest.getType();
		String eta = demanddatarequest.getEta();
		String sourcetype = demanddatarequest.getSourceType();
		String sourcename = demanddatarequest.getSourceName();
		String status = demanddatarequest.getStatus();

		CreateAndUpdateDemandMaster("BusinessUnit", businessunit);
		CreateAndUpdateDemandMaster("Account", account);
		CreateAndUpdateDemandMaster("Engagement", engagement);
		CreateAndUpdateDemandMaster("Role", role);
		CreateAndUpdateDemandMaster("PositionTitle", positionTitle);
		CreateAndUpdateDemandMaster("Country", country);
		CreateAndUpdateDemandMaster("Location", location);
		CreateAndUpdateDemandMaster("Total", total);
		CreateAndUpdateDemandMaster("Probability", probability);
		CreateAndUpdateDemandMaster("Priority", priority);
		CreateAndUpdateDemandMaster("Spoc", spoc);
		CreateAndUpdateDemandMaster("FullFilled", fullfilled);
		//CreateAndUpdateDemandMaster("DemandRequestDate", demandrequestdate);
		CreateAndUpdateDemandMaster("Requestedby", requestedby);
		CreateAndUpdateDemandMaster("ConduentHiringManager", conduenthiringmanager);
		CreateAndUpdateDemandMaster("WorkLocation", worklocation);
		CreateAndUpdateDemandMaster("Level", level);
		CreateAndUpdateDemandMaster("BillRate", billrate);
		CreateAndUpdateDemandMaster("Duration", duration);
		CreateAndUpdateDemandMaster("ProfilesCount", profilesCount);
		CreateAndUpdateDemandMaster("Tocrid", tocrid);
		CreateAndUpdateDemandMaster("Type", type);
		//CreateAndUpdateDemandMaster("ETA", eta);
		CreateAndUpdateDemandMaster("SourceType", sourcetype);
		CreateAndUpdateDemandMaster("SourceName", sourcename);
		CreateAndUpdateDemandMaster("Status", status);
		// CreateAndUpdateDemandMaster("JobDescription",
		// demanddatarequest.getJobDescription());

		if (demanddatarequest.getDemandidentifier() != null) {
			Optional<DemandData> objMasterOpt = demanddatarepository.findById(demanddatarequest.getDemandidentifier());
			if (objMasterOpt.isPresent()) {
				System.out.println("inside here");
				DemandData objMaster = objMasterOpt.get();
				Long demandidentifer = objMaster.getDemandidentifier();
				try {
					BeanUtils.copyProperties(objMaster, demanddatarequest);
					objMaster.setDemandidentifier(demandidentifer);
					objMaster.setModifieddate(
							demanddatarequest.getModifieddate() == null || demanddatarequest.getModifieddate() == ""
									? new java.util.Date().toString()
									: demanddatarequest.getModifieddate());
					System.out.println("inside saving");
					demanddatarepository.save(objMaster);
				} catch (Exception e) {
					return ResponseEntity.ok(new MessageResponse("Demand not registered successfully!"));
				}
			} else {
				savedemanddata(demanddatarequest);
			}
		} else {
			savedemanddata(demanddatarequest);
		}

		return ResponseEntity.ok(new MessageResponse("Demand registered successfully!"));
	}

	private void savedemanddata(DemandData demanddatarequest) {
		DemandData objNew = new DemandData(demanddatarequest.getBusinessUnit(), demanddatarequest.getAccount(),
				demanddatarequest.getEngagement(), demanddatarequest.getRole(), demanddatarequest.getTotal(),
				demanddatarequest.getCountry(), demanddatarequest.getProbability(), demanddatarequest.getPriority(),
				demanddatarequest.getSpoc(), demanddatarequest.getFullFilled(),
				demanddatarequest.getDemandRequestDate(), demanddatarequest.getRequestedBy(),
				demanddatarequest.getConduentHiringManager(), demanddatarequest.getWorkLocation(),
				demanddatarequest.getLevel(), demanddatarequest.getBillRate(), demanddatarequest.getDuration(),
				demanddatarequest.getProfilesCount(), demanddatarequest.getTocrid(), demanddatarequest.getType(),
				demanddatarequest.getPositionTitle(), demanddatarequest.getSourceType(),
				demanddatarequest.getSourceName(), demanddatarequest.getEta(), demanddatarequest.getStatus(),
				demanddatarequest.getCreatedby(),
				demanddatarequest.getCreateddate() == null || demanddatarequest.getCreateddate() == ""
						? new java.util.Date().toString()
						: demanddatarequest.getCreateddate(),
				demanddatarequest.getModifiedby(),
				demanddatarequest.getModifieddate() == null || demanddatarequest.getModifieddate() == ""
						? new java.util.Date().toString()
						: demanddatarequest.getModifieddate(),
				demanddatarequest.getLocation(), demanddatarequest.getJobDescription());
		demanddatarepository.save(objNew);
	}

	@PostMapping("/delete")
	public ResponseEntity<?> deleteDemandMaster(@Valid @RequestBody DemandRequest demandrequest) {
		Optional<DemandMaster> objMasterOpt = null;
		DemandMaster objMaster = null;

		if (demandrequest.getDemandmasterid() != null
				&& demandmasterrepository.existsById(demandrequest.getDemandmasterid())) {
			objMasterOpt = demandmasterrepository.findById(demandrequest.getDemandmasterid());
			if (objMasterOpt.isPresent()) {
				objMaster = objMasterOpt.get();
				objMaster.setActive("N");
				demandmasterrepository.save(objMaster);
			}
		}

		return ResponseEntity.ok(new MessageResponse("Demand deleted successfully!"));
	}

	@PostMapping("/findDemandDataAdmin")
	public List findDemandDataAdmin(@Valid @RequestBody DemandData demanddatarequest) {
		List finalmasterlist = new ArrayList();
		List returnlist = new ArrayList();
		int valuecheck = 0;
		String vertical = demanddatarequest.getBusinessUnit() == null ? "" : demanddatarequest.getBusinessUnit();
		if (vertical != "")
			valuecheck = valuecheck + 1;
		String account = demanddatarequest.getAccount() == null ? "" : demanddatarequest.getAccount();
		if (account != "")
			valuecheck = valuecheck + 1;
		String engagement = demanddatarequest.getEngagement() == null ? "" : demanddatarequest.getEngagement();
		if (engagement != "")
			valuecheck = valuecheck + 1;
		String role = demanddatarequest.getRole() == null ? "" : demanddatarequest.getRole();
		if (role != "")
			valuecheck = valuecheck + 1;
		String country = demanddatarequest.getCountry() == null ? "" : demanddatarequest.getCountry();
		if (country != "")
			valuecheck = valuecheck + 1;
		String total = demanddatarequest.getTotal() == null ? "" : demanddatarequest.getTotal();
		if (total != "")
			valuecheck = valuecheck + 1;
		String probability = demanddatarequest.getProbability() == null ? "" : demanddatarequest.getProbability();
		if (probability != "")
			valuecheck = valuecheck + 1;
		String priority = demanddatarequest.getPriority() == null ? "" : demanddatarequest.getPriority();
		if (priority != "")
			valuecheck = valuecheck + 1;
		String spoc = demanddatarequest.getSpoc() == null ? "" : demanddatarequest.getSpoc();
		if (spoc != "")
			valuecheck = valuecheck + 1;
		String fullfilled = demanddatarequest.getFullFilled() == null ? "" : demanddatarequest.getFullFilled();
		if (fullfilled != "")
			valuecheck = valuecheck + 1;
		String demandrequestdate = demanddatarequest.getDemandRequestDate() == null ? ""
				: demanddatarequest.getDemandRequestDate();
		if (demandrequestdate != "")
			valuecheck = valuecheck + 1;
		String type = demanddatarequest.getType() == null ? "" : demanddatarequest.getType();
		if (type != "")
			valuecheck = valuecheck + 1;
		String eta = demanddatarequest.getEta() == null ? "" : demanddatarequest.getEta();
		if (eta != "")
			valuecheck = valuecheck + 1;
		String status = demanddatarequest.getStatus() == null ? "" : demanddatarequest.getStatus();
		if (status != "")
			valuecheck = valuecheck + 1;
		String location = demanddatarequest.getLocation() == null ? "" : demanddatarequest.getLocation();
		if (location != "")
			valuecheck = valuecheck + 1;
		String positiontitle = demanddatarequest.getPositionTitle() == null ? "" : demanddatarequest.getPositionTitle();
		if (positiontitle != "")
			valuecheck = valuecheck + 1;
		String worklocation = demanddatarequest.getWorkLocation() == null ? "" : demanddatarequest.getWorkLocation();
		if (worklocation != "")
			valuecheck = valuecheck + 1;
		String level = demanddatarequest.getLevel() == null ? "" : demanddatarequest.getLevel();
		if (level != "")
			valuecheck = valuecheck + 1;
		String billrate = demanddatarequest.getBillRate() == null ? "" : demanddatarequest.getBillRate();
		if (billrate != "")
			valuecheck = valuecheck + 1;
		String duration = demanddatarequest.getDuration() == null ? "" : demanddatarequest.getDuration();
		if (duration != "")
			valuecheck = valuecheck + 1;
		String profilescount = demanddatarequest.getProfilesCount() == null ? "" : demanddatarequest.getProfilesCount();
		if (profilescount != "")
			valuecheck = valuecheck + 1;
		String tocrid = demanddatarequest.getTocrid() == null ? "" : demanddatarequest.getTocrid();
		if (tocrid != "")
			valuecheck = valuecheck + 1;
		String sourcetype = demanddatarequest.getSourceType() == null ? "" : demanddatarequest.getSourceType();
		if (sourcetype != "")
			valuecheck = valuecheck + 1;
		String sourcename = demanddatarequest.getSourceName() == null ? "" : demanddatarequest.getSourceName();
		if (sourcename != "")
			valuecheck = valuecheck + 1;
		String requestedby = demanddatarequest.getRequestedBy() == null ? "" : demanddatarequest.getRequestedBy();
		if (requestedby != "")
			valuecheck = valuecheck + 1;
		String conduenthiringmanager = demanddatarequest.getConduentHiringManager() == null ? ""
				: demanddatarequest.getConduentHiringManager();
		if (conduenthiringmanager != "")
			valuecheck = valuecheck + 1;

		System.out.println("the incoming value " + valuecheck);
		int dbcheck = 0;
		finalmasterlist = demanddatarepository
				.findByBusinessUnitOrAccountOrEngagementOrRoleOrTotalOrCountryOrProbabilityOrPriorityOrSpocOrFullFilledOrDemandRequestDateOrRequestedByOrConduentHiringManagerOrWorkLocationOrLevelOrBillRateOrDurationOrProfilesCountOrTocridOrTypeOrEtaOrStatusOrLocationOrPositionTitleOrSourceTypeOrSourceName(
						vertical, account, engagement, role, total, country, probability, priority, spoc, fullfilled,
						demandrequestdate, requestedby, conduenthiringmanager, worklocation, level, billrate, duration,
						profilescount, tocrid, type, eta, status, location, positiontitle, sourcetype, sourcename);
		System.out.println("the finalmasterlist value " + finalmasterlist);
		for (int i = 0; i < finalmasterlist.size(); i++) {
			DemandData objMaster = (DemandData) finalmasterlist.get(i);

			if (vertical != "" && objMaster.getBusinessUnit() != null && objMaster.getBusinessUnit().equals(vertical)) {
				dbcheck = dbcheck + 1;
			}
			if (account != "" && objMaster.getAccount() != null && objMaster.getAccount().equals(account)) {
				dbcheck = dbcheck + 1;
			}
			if (engagement != "" && objMaster.getEngagement() != null && objMaster.getEngagement().equals(engagement)) {
				dbcheck = dbcheck + 1;
			}
			if (role != "" && objMaster.getRole() != null && objMaster.getRole().equals(role)) {
				dbcheck = dbcheck + 1;
			}

			if (probability != "" && objMaster.getProbability() != null
					&& objMaster.getProbability().equals(probability)) {
				dbcheck = dbcheck + 1;
			}
			if (priority != "" && objMaster.getPriority() != null && objMaster.getPriority().equals(priority)) {
				dbcheck = dbcheck + 1;
			}
			if (spoc != "" && objMaster.getSpoc() != null && objMaster.getSpoc().equals(spoc)) {
				dbcheck = dbcheck + 1;
			}

			if (type != "" && objMaster.getType() != null && objMaster.getType().equals(type)) {
				dbcheck = dbcheck + 1;
			}
			if (eta != "" && objMaster.getEta() != null && objMaster.getEta().equals(eta)) {
				dbcheck = dbcheck + 1;
			}
			if (status != "" && objMaster.getStatus() != null && objMaster.getStatus().equals(status)) {
				dbcheck = dbcheck + 1;
			}

			if (location != "" && objMaster.getLocation() != null && objMaster.getLocation().equals(location)) {
				dbcheck = dbcheck + 1;
			}

			if (positiontitle != "" && objMaster.getPositionTitle() != null
					&& objMaster.getPositionTitle().equals(positiontitle)) {
				dbcheck = dbcheck + 1;
			}

			if (total != "" && objMaster.getTotal() != null && objMaster.getTotal().equals(total)) {
				dbcheck = dbcheck + 1;
			}

			if (country != "" && objMaster.getCountry() != null && objMaster.getCountry().equals(country)) {
				dbcheck = dbcheck + 1;
			}

			if (fullfilled != "" && objMaster.getFullFilled() != null && objMaster.getFullFilled().equals(fullfilled)) {
				dbcheck = dbcheck + 1;
			}

			if (demandrequestdate != "" && objMaster.getDemandRequestDate() != null
					&& objMaster.getDemandRequestDate().equals(demandrequestdate)) {
				dbcheck = dbcheck + 1;
			}

			if (requestedby != "" && objMaster.getRequestedBy() != null
					&& objMaster.getRequestedBy().equals(requestedby)) {
				dbcheck = dbcheck + 1;
			}

			if (conduenthiringmanager != "" && objMaster.getConduentHiringManager() != null
					&& objMaster.getConduentHiringManager().equals(conduenthiringmanager)) {
				dbcheck = dbcheck + 1;
			}

			if (worklocation != "" && objMaster.getWorkLocation() != null
					&& objMaster.getWorkLocation().equals(worklocation)) {
				dbcheck = dbcheck + 1;
			}

			if (level != "" && objMaster.getLevel() != null && objMaster.getLevel().equals(level)) {
				dbcheck = dbcheck + 1;
			}

			if (billrate != "" && objMaster.getBillRate() != null && objMaster.getBillRate().equals(billrate)) {
				dbcheck = dbcheck + 1;
			}

			if (duration != "" && objMaster.getDuration() != null && objMaster.getDuration().equals(duration)) {
				dbcheck = dbcheck + 1;
			}

			if (tocrid != "" && objMaster.getTocrid() != null && objMaster.getTocrid().equals(tocrid)) {
				dbcheck = dbcheck + 1;
			}

			if (sourcetype != "" && objMaster.getSourceType() != null && objMaster.getSourceType().equals(sourcetype)) {
				dbcheck = dbcheck + 1;
			}

			if (sourcename != "" && objMaster.getSourceName() != null && objMaster.getSourceName().equals(sourcename)) {
				dbcheck = dbcheck + 1;
			}

			System.out.println("the dbcheck value " + dbcheck);
			System.out.println("the status value " + status);
			System.out.println("the objMaster.getStatus() value " + objMaster.getStatus());
			if (dbcheck == valuecheck) {
				if (((status == null) || (status == "")) && (objMaster.getStatus() != null)
						&& (objMaster.getStatus().equalsIgnoreCase("Closed")
								|| objMaster.getStatus().equalsIgnoreCase("Deleted"))) {
					System.out.println("Dont add this as it is Closed/Deleted!");
				} else {
					returnlist.add(objMaster);
				}
			}
			dbcheck = 0;

		}

		return returnlist;
	}

	@PostMapping("/findDemandDataUser")
	public List findDemandDataUser(@Valid @RequestBody DemandData demanddatarequest) {
		List finalmasterlist = new ArrayList();
		List returnlist = new ArrayList();
		int valuecheck = 0;
		String vertical = demanddatarequest.getBusinessUnit() == null ? "" : demanddatarequest.getBusinessUnit();
		if (vertical != "")
			valuecheck = valuecheck + 1;
		String account = demanddatarequest.getAccount() == null ? "" : demanddatarequest.getAccount();
		if (account != "")
			valuecheck = valuecheck + 1;
		String engagement = demanddatarequest.getEngagement() == null ? "" : demanddatarequest.getEngagement();
		if (engagement != "")
			valuecheck = valuecheck + 1;
		String role = demanddatarequest.getRole() == null ? "" : demanddatarequest.getRole();
		if (role != "")
			valuecheck = valuecheck + 1;
		String country = demanddatarequest.getCountry() == null ? "" : demanddatarequest.getCountry();
		if (country != "")
			valuecheck = valuecheck + 1;
		String total = demanddatarequest.getTotal() == null ? "" : demanddatarequest.getTotal();
		if (total != "")
			valuecheck = valuecheck + 1;
		String probability = demanddatarequest.getProbability() == null ? "" : demanddatarequest.getProbability();
		if (probability != "")
			valuecheck = valuecheck + 1;
		String priority = demanddatarequest.getPriority() == null ? "" : demanddatarequest.getPriority();
		if (priority != "")
			valuecheck = valuecheck + 1;
		String spoc = demanddatarequest.getSpoc() == null ? "" : demanddatarequest.getSpoc();
		if (spoc != "")
			valuecheck = valuecheck + 1;
		String fullfilled = demanddatarequest.getFullFilled() == null ? "" : demanddatarequest.getFullFilled();
		if (fullfilled != "")
			valuecheck = valuecheck + 1;
		String demandrequestdate = demanddatarequest.getDemandRequestDate() == null ? ""
				: demanddatarequest.getDemandRequestDate();
		if (demandrequestdate != "")
			valuecheck = valuecheck + 1;
		String type = demanddatarequest.getType() == null ? "" : demanddatarequest.getType();
		if (type != "")
			valuecheck = valuecheck + 1;
		String eta = demanddatarequest.getEta() == null ? "" : demanddatarequest.getEta();
		if (eta != "")
			valuecheck = valuecheck + 1;
		String status = demanddatarequest.getStatus() == null ? "" : demanddatarequest.getStatus();
		if (status != "")
			valuecheck = valuecheck + 1;
		String location = demanddatarequest.getLocation() == null ? "" : demanddatarequest.getLocation();
		if (location != "")
			valuecheck = valuecheck + 1;
		String positiontitle = demanddatarequest.getPositionTitle() == null ? "" : demanddatarequest.getPositionTitle();
		if (positiontitle != "")
			valuecheck = valuecheck + 1;
		String worklocation = demanddatarequest.getWorkLocation() == null ? "" : demanddatarequest.getWorkLocation();
		if (worklocation != "")
			valuecheck = valuecheck + 1;
		String level = demanddatarequest.getLevel() == null ? "" : demanddatarequest.getLevel();
		if (level != "")
			valuecheck = valuecheck + 1;
		String billrate = demanddatarequest.getBillRate() == null ? "" : demanddatarequest.getBillRate();
		if (billrate != "")
			valuecheck = valuecheck + 1;
		String duration = demanddatarequest.getDuration() == null ? "" : demanddatarequest.getDuration();
		if (duration != "")
			valuecheck = valuecheck + 1;
		String profilescount = demanddatarequest.getProfilesCount() == null ? "" : demanddatarequest.getProfilesCount();
		if (profilescount != "")
			valuecheck = valuecheck + 1;
		String tocrid = demanddatarequest.getTocrid() == null ? "" : demanddatarequest.getTocrid();
		if (tocrid != "")
			valuecheck = valuecheck + 1;
		String sourcetype = demanddatarequest.getSourceType() == null ? "" : demanddatarequest.getSourceType();
		if (sourcetype != "")
			valuecheck = valuecheck + 1;
		String sourcename = demanddatarequest.getSourceName() == null ? "" : demanddatarequest.getSourceName();
		if (sourcename != "")
			valuecheck = valuecheck + 1;
		String requestedby = demanddatarequest.getRequestedBy() == null ? "" : demanddatarequest.getRequestedBy();
		if (requestedby != "")
			valuecheck = valuecheck + 1;
		String conduenthiringmanager = demanddatarequest.getConduentHiringManager() == null ? ""
				: demanddatarequest.getConduentHiringManager();
		if (conduenthiringmanager != "")
			valuecheck = valuecheck + 1;

		System.out.println("the incoming value " + valuecheck);
		int dbcheck = 0;
		finalmasterlist = demanddatarepository
				.findByBusinessUnitOrAccountOrEngagementOrRoleOrTotalOrCountryOrProbabilityOrPriorityOrSpocOrFullFilledOrDemandRequestDateOrRequestedByOrConduentHiringManagerOrWorkLocationOrLevelOrBillRateOrDurationOrProfilesCountOrTocridOrTypeOrEtaOrStatusOrLocationOrPositionTitleOrSourceTypeOrSourceName(
						vertical, account, engagement, role, total, country, probability, priority, spoc, fullfilled,
						demandrequestdate, requestedby, conduenthiringmanager, worklocation, level, billrate, duration,
						profilescount, tocrid, type, eta, status, location, positiontitle, sourcetype, sourcename);
		System.out.println("the finalmasterlist value " + finalmasterlist);
		for (int i = 0; i < finalmasterlist.size(); i++) {
			DemandData objMaster = (DemandData) finalmasterlist.get(i);

			if (vertical != "" && objMaster.getBusinessUnit() != null && objMaster.getBusinessUnit().equals(vertical)) {
				dbcheck = dbcheck + 1;
			}
			if (account != "" && objMaster.getAccount() != null && objMaster.getAccount().equals(account)) {
				dbcheck = dbcheck + 1;
			}
			if (engagement != "" && objMaster.getEngagement() != null && objMaster.getEngagement().equals(engagement)) {
				dbcheck = dbcheck + 1;
			}
			if (role != "" && objMaster.getRole() != null && objMaster.getRole().equals(role)) {
				dbcheck = dbcheck + 1;
			}

			if (probability != "" && objMaster.getProbability() != null
					&& objMaster.getProbability().equals(probability)) {
				dbcheck = dbcheck + 1;
			}
			if (priority != "" && objMaster.getPriority() != null && objMaster.getPriority().equals(priority)) {
				dbcheck = dbcheck + 1;
			}
			if (spoc != "" && objMaster.getSpoc() != null && objMaster.getSpoc().equals(spoc)) {
				dbcheck = dbcheck + 1;
			}

			if (type != "" && objMaster.getType() != null && objMaster.getType().equals(type)) {
				dbcheck = dbcheck + 1;
			}
			if (eta != "" && objMaster.getEta() != null && objMaster.getEta().equals(eta)) {
				dbcheck = dbcheck + 1;
			}
			if (status != "" && objMaster.getStatus() != null && objMaster.getStatus().equals(status)) {
				dbcheck = dbcheck + 1;
			}

			if (location != "" && objMaster.getLocation() != null && objMaster.getLocation().equals(location)) {
				dbcheck = dbcheck + 1;
			}

			if (positiontitle != "" && objMaster.getPositionTitle() != null
					&& objMaster.getPositionTitle().equals(positiontitle)) {
				dbcheck = dbcheck + 1;
			}

			if (total != "" && objMaster.getTotal() != null && objMaster.getTotal().equals(total)) {
				dbcheck = dbcheck + 1;
			}

			if (country != "" && objMaster.getCountry() != null && objMaster.getCountry().equals(country)) {
				dbcheck = dbcheck + 1;
			}

			if (fullfilled != "" && objMaster.getFullFilled() != null && objMaster.getFullFilled().equals(fullfilled)) {
				dbcheck = dbcheck + 1;
			}

			if (demandrequestdate != "" && objMaster.getDemandRequestDate() != null
					&& objMaster.getDemandRequestDate().equals(demandrequestdate)) {
				dbcheck = dbcheck + 1;
			}

			if (requestedby != "" && objMaster.getRequestedBy() != null
					&& objMaster.getRequestedBy().equals(requestedby)) {
				dbcheck = dbcheck + 1;
			}

			if (conduenthiringmanager != "" && objMaster.getConduentHiringManager() != null
					&& objMaster.getConduentHiringManager().equals(conduenthiringmanager)) {
				dbcheck = dbcheck + 1;
			}

			if (worklocation != "" && objMaster.getWorkLocation() != null
					&& objMaster.getWorkLocation().equals(worklocation)) {
				dbcheck = dbcheck + 1;
			}

			if (level != "" && objMaster.getLevel() != null && objMaster.getLevel().equals(level)) {
				dbcheck = dbcheck + 1;
			}

			if (billrate != "" && objMaster.getBillRate() != null && objMaster.getBillRate().equals(billrate)) {
				dbcheck = dbcheck + 1;
			}

			if (duration != "" && objMaster.getDuration() != null && objMaster.getDuration().equals(duration)) {
				dbcheck = dbcheck + 1;
			}

			if (tocrid != "" && objMaster.getTocrid() != null && objMaster.getTocrid().equals(tocrid)) {
				dbcheck = dbcheck + 1;
			}

			if (sourcetype != "" && objMaster.getSourceType() != null && objMaster.getSourceType().equals(sourcetype)) {
				dbcheck = dbcheck + 1;
			}

			if (sourcename != "" && objMaster.getSourceName() != null && objMaster.getSourceName().equals(sourcename)) {
				dbcheck = dbcheck + 1;
			}

			System.out.println("the dbcheck value " + dbcheck);
			if ((dbcheck == valuecheck))
				if (objMaster.getStatus() == null) {
					returnlist.add(objMaster);
				}
			if ((objMaster.getStatus() != null) && (!objMaster.getStatus().equalsIgnoreCase("Closed")
					&& !objMaster.getStatus().equalsIgnoreCase("Deleted"))) {
				returnlist.add(objMaster);
			}
			dbcheck = 0;

		}

		return returnlist;
	}

	@PostMapping("/find")
	public List findDemandMaster(@Valid @RequestBody DemandRequest demandrequest) {
		List finalmasterlist = new ArrayList();
		Optional<DemandMaster> objMasterOpt = null;
		// Map objMap = new HashMap();
		DemandMaster objMaster = null;

		if (demandrequest.getDemandmasterid() != null
				&& demandmasterrepository.existsById(demandrequest.getDemandmasterid())) {
			objMasterOpt = demandmasterrepository.findById(demandrequest.getDemandmasterid());
			Map objMap = new HashMap();
			List objMasterList = new ArrayList();
			if (objMasterOpt.isPresent()) {
				objMaster = objMasterOpt.get();
				// objMasterList.add(objMaster.getDemandkey());
				objMap.put("name", objMaster.getDemandkey());
				objMasterList = new ArrayList<String>();
				objMasterList.add(objMaster.getDemandvalue());
				objMap.put("options", objMasterList);
				finalmasterlist.add(objMap);
				return finalmasterlist;
			}
		}

		if (demandrequest.getActive() != null) {
			List<DemandMaster> objMasterList_ = demandmasterrepository
					.findByActiveOrderByDisplayOrderAsc(demandrequest.getActive());
			List checklist = new ArrayList();

			for (DemandMaster tempobj_ : objMasterList_) {
				System.out.println("inside first for loop:" + tempobj_.getDemandkey());
				Map objMap = new HashMap();
				objMap.put("name", tempobj_.getDemandkey());
				Map tempMap = new HashMap();
				List objMasterList = new ArrayList();
				tempMap.put("label", tempobj_.getDemandvalue());
				tempMap.put("value", tempobj_.getDemandvalue());
				objMasterList.add(tempMap);
				objMap.put("options", objMasterList);
				finalmasterlist.add(objMap);
			}

			for (int i = 0; i < finalmasterlist.size(); i++) {
				if (checklist.isEmpty()) {
					System.out.println(finalmasterlist.get(i));
					checklist.add(finalmasterlist.get(i));
				} else {
					System.out.println("inside else");
					int i1 = 0;
					for (int j = 0; j < checklist.size(); j++) {
						System.out.println("inside loop");
						HashMap temp1 = new HashMap();
						temp1 = (HashMap) finalmasterlist.get(i);
						System.out.println("inside temp1" + temp1);
						HashMap temp2 = new HashMap();
						temp2 = (HashMap) checklist.get(j);
						System.out.println("inside temp2" + temp2);
						if (temp1.get("name").equals(temp2.get("name"))) {
							System.out.println("got matched");
							List objopt = (List) temp1.get("options");
							for (int k = 0; k < objopt.size(); k++) {
								HashMap objmap = (HashMap) objopt.get(k);
								((List) temp2.get("options")).add(objmap);
							}
							i1 = 1;
							break;
						}
					}
					if (i1 == 0)
						checklist.add(finalmasterlist.get(i));
				}
			}

			System.out.println(checklist);

			return checklist;
		}
		return null;

		// return ResponseEntity.ok(new MessageResponse("Demand deleted
		// successfully!"));
	}

}
