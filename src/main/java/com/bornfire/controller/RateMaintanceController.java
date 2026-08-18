package com.bornfire.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.entity.EncryptionEntity;
import com.bornfire.entity.RateMaintainanceEntity;
import com.bornfire.service.RateService;

@RestController
@RequestMapping("/api")
public class RateMaintanceController {

	@Autowired
	RateService service;
	
	// All Rate Maintenance list
	@GetMapping("/RateMaintenanceList")
	public List<RateMaintainanceEntity> getAllratedetails() throws Exception{
		return service.getAllrate();
	}

	// Creation/Addition of new rate
	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/AddNewRate")
	public String CreateRateData(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		//System.out.println("Inside");
		return service.CreateRateData(EncryptedString, psuDeviceID);
	}

	// UpdateRateData
	@RequestMapping(value = "UpdateExistRate", method = { RequestMethod.PUT })
	public String UpdateRate(@RequestBody EncryptionEntity updaterate, @RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception  {
		return service.updateRateService(updaterate, psuDeviceID);
	}

	@GetMapping("/UserManagementViewRate")
	public RateMaintainanceEntity UserManagementViewRate(@RequestParam String srl) {
		return service.getOneRate(srl);
	}
	
}
