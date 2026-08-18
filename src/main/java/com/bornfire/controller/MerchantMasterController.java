package com.bornfire.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.entity.BIPS_MerUserManagement_Repo;
import com.bornfire.entity.BIPS_Mer_User_Management_Entity;
import com.bornfire.entity.MerchantFeeServiceChargeRepo;
import com.bornfire.entity.MerchantFeesServiceCharges;
import com.bornfire.entity.MerchantMaster;
import com.bornfire.entity.MerchantMasterRep;
import com.bornfire.entity.UnitManagementRepository;

@RestController
@RequestMapping("/api")
public class MerchantMasterController {
	@Autowired
	MerchantMasterRep MerchantMasterReps;
	
	@Autowired
	MerchantFeeServiceChargeRepo merchantFeeServiceChargeRepo;
	
	@Autowired
	BIPS_MerUserManagement_Repo bIPS_MerUserManagement_Repo;
	
	@Autowired
	UnitManagementRepository unitManagementRepository;
	
	@GetMapping("/Merchantmasterdetail")
	public List<MerchantMaster> GetAllUser(@RequestParam String merchant_user_id) {
		List<MerchantMaster> existingUser = MerchantMasterReps.ALLDATAs(merchant_user_id);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}
	
	@GetMapping("/Merchantmasterviewdetail")
	public MerchantMaster Merchantmasterview(@RequestParam String merchant_user_id) {
		MerchantMaster existingUser = MerchantMasterReps.findByIdCustom(merchant_user_id);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}
	
	@GetMapping("/MerchantmasterviewFeedetail")
	public List<MerchantFeesServiceCharges> MerchantmasterviewFeedetail(@RequestParam String merchant_user_id) {
		List<MerchantFeesServiceCharges> existingUser = merchantFeeServiceChargeRepo.merchantDetails(merchant_user_id);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}

	
	@GetMapping("/MerchantQrList")
	public BIPS_Mer_User_Management_Entity MerchantQrList(@RequestParam String user_id) {
		BIPS_Mer_User_Management_Entity existingUser =bIPS_MerUserManagement_Repo.getuser(user_id);
        System.out.print(existingUser);
		return existingUser; 
	}
	
	
	@GetMapping("/MerchantMasterForOne")
	public MerchantMaster MerchantMasterForOne(@RequestParam String accountNumber) {
		MerchantMaster existingUser = MerchantMasterReps.findByIdCustom(accountNumber);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}
	
	@GetMapping("/getDynamicQrMerMaucasFormat")
	public MerchantMaster getDynamicQrMerMaucasFormat(@RequestParam String merchant_id) {
		MerchantMaster existingUser = MerchantMasterReps.findByIdCustom(merchant_id);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}
	
	@GetMapping("/unitidDetails")
	public List<String> unitidDetails(@RequestParam String merchant_id) {
		List<String> existingUser = unitManagementRepository.getpartUnitId(merchant_id);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}

	@RequestMapping(value = "/getUnitDetails", method = RequestMethod.GET)
	public Object[] getUnitDetails(@RequestParam(required = false) String unitId) 
			throws IOException, SQLException {
		 
		return unitManagementRepository.getUnitDetail(unitId);
	}

}
