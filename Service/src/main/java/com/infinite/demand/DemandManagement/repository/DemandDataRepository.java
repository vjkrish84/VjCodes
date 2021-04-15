package com.infinite.demand.DemandManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infinite.demand.DemandManagement.models.DemandData;
import com.infinite.demand.DemandManagement.models.DemandMaster;

@Repository
public interface DemandDataRepository extends JpaRepository<DemandData, Long> {

	List<DemandData> findByBusinessUnitOrAccountOrEngagementOrRoleOrTotalOrCountryOrProbabilityOrPriorityOrSpocOrFullFilledOrDemandRequestDateOrRequestedByOrConduentHiringManagerOrWorkLocationOrLevelOrBillRateOrDurationOrProfilesCountOrTocridOrTypeOrEtaOrStatusOrLocationOrPositionTitleOrSourceTypeOrSourceName(
			String vertical, String account, String engagement, String role, String total, String country, String probability, String priority, String spoc, String fullfilled,
			String demandrequestdate, String requestedby, String conduenthiringmanager, String worklocation, String level, String billrate, String duration,
			String profilescount, String tocrid, String type, String eta, String status, String location, String positiontitle, String sourcetype, String sourcename);
}