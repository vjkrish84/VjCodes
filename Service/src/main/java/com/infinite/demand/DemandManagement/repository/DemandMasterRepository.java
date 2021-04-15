package com.infinite.demand.DemandManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infinite.demand.DemandManagement.models.DemandMaster;


@Repository
public interface DemandMasterRepository extends JpaRepository<DemandMaster, Long> {
	
	List<DemandMaster> findByActiveOrderByDisplayOrderAsc(String active);
	
	List<DemandMaster> findByDemandkeyAndDemandvalue(String demandkey,String demandvalue);
	
	List<DemandMaster> findByDemandkey(String demandkey);
}