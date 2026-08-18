package com.bornfire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.entity.DeviceManagementEntity;
import com.bornfire.entity.DeviceManagementRepository;
import com.bornfire.entity.EncryptionEntity;
import com.bornfire.service.DeviceService;

@RestController
@RequestMapping("/api")
public class DeviceManagementController {

	@Autowired
	DeviceService service;

	@Autowired
	DeviceManagementRepository devicemanagement;
	
	@GetMapping("/AllDeviceList")
	public List<DeviceManagementEntity> getAllDevice(@RequestParam String merchant_user_id,
			@RequestParam String unit_id) {
		return service.getAllDevice(merchant_user_id, unit_id);
	}
	// Device Management list
	@GetMapping("/DeviceManagementList")
	public List<DeviceManagementEntity> getAllDeviceDetails(@RequestParam String merchant_user_id) {
		return service.getAllDeviceDetailsService(merchant_user_id);
	}

	// Unit Device Management List
	@GetMapping("/GetAllUnitDeviceList")
	public List<DeviceManagementEntity> getAllUnitDeviceDetails(@RequestParam String merchant_user_id,
			@RequestParam String unit_id) {
		return service.getAllUnitDeviceDetailsService(merchant_user_id, unit_id);
	}

	// CheckDeviceID
	@GetMapping("/CheckDeviceId")
	public String getCheckDeviceDetails(@RequestParam String device_id) {
		return service.getcheckDeviceDetailsService(device_id);
	}
	@PostMapping("/DeleteDeviceData")
	public String DeleteUserData(@RequestParam String deviceid,@RequestParam String remark, @RequestParam String verifyuser) throws Exception {
		return service.DeleteDeviceDataService(deviceid, remark,verifyuser);
	}
	// Creation/Addition of new device
	@PostMapping("/AddDevice")
	public String AddDeviceData(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		return service.AddDeviceDataService(EncryptedString, psuDeviceID);
	}

	@RequestMapping(value = "VerifyDevicedetails", method = { RequestMethod.POST })
	public String VerifyExisitingUser(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		return service.VerifyDeviceDetails(EncryptedString, psuDeviceID);
	}

	// Update the Existing Device
	@RequestMapping(value = "UpdateDeviceData", method =  {RequestMethod.POST, RequestMethod.PUT})
	public String UpdateDevice(@RequestBody EncryptionEntity deviceUpdateRequest,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		return service.UpdateDeviceService(deviceUpdateRequest, psuDeviceID);
	}

	@GetMapping("/devicemanagementidlist")
	public List<DeviceManagementEntity> devicemanagementidlist(@RequestParam String merchant_id) {
		List<DeviceManagementEntity> existingUser = devicemanagement.getdeviceId1(merchant_id);
		System.out.print(existingUser);
		// Iterate over the list and print a specific column (field)
		for (DeviceManagementEntity entity : existingUser) {
			//System.out.println(entity.getDevice_id()); // Replace getDeviceId() with the actual method name to get the
														// specific column you want to print
		}
		// logger.debug(existingUser.toString());
		return existingUser;
	}

	@GetMapping("/deviceviewdetail")
	public DeviceManagementEntity deviceviewdetail(@RequestParam String device_id) {
		DeviceManagementEntity existingUser = devicemanagement.getdevice(device_id);
		System.out.print(existingUser);
		// logger.debug(existingUser.toString());
		return existingUser;
	}
	
	//For Web
	@GetMapping("/deviceviewdetailWeb")
	public DeviceManagementEntity deviceviewdetailWeb(@RequestParam String device_id) {
		DeviceManagementEntity existingUser = devicemanagement.getdeviceWeb(device_id);
		System.out.print(existingUser);
		// logger.debug(existingUser.toString());
		return existingUser;
	}

	@GetMapping("/deviceeditdetailWeb")
	public DeviceManagementEntity deviceeditdetailWeb(@RequestParam String device_id) {
		DeviceManagementEntity existingUser = devicemanagement.getdeviceWeb(device_id);
		System.out.print(existingUser);
		// logger.debug(existingUser.toString());
		return existingUser;
	}
	
	@GetMapping("/devicverifydetailWeb")
	public DeviceManagementEntity devicverifydetailWeb(@RequestParam String device_id) {
		DeviceManagementEntity existingUser = devicemanagement.getdeviceWeb(device_id);
		System.out.print(existingUser);
		// logger.debug(existingUser.toString());
		return existingUser;
	}
	
	@GetMapping("/deviceeditdetail")
	public DeviceManagementEntity deviceeditdetail(@RequestParam String device_id) {
		DeviceManagementEntity existingUser = devicemanagement.getdevice(device_id);
		System.out.print(existingUser);
		// logger.debug(existingUser.toString());
		return existingUser;
	}

	@GetMapping("/devicverifydetail")
	public DeviceManagementEntity devicverifydetail(@RequestParam String device_id) {
		DeviceManagementEntity existingUser = devicemanagement.getdevice(device_id);
		System.out.print(existingUser);
		// logger.debug(existingUser.toString());
		return existingUser;
	}
	
	@GetMapping("/defaultDeviceid")                                                     
	public List<String> defaultDeviceid(@RequestParam String merchant_id) {
		List<String> existingUser = devicemanagement.getdeviceId(merchant_id);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}

}
