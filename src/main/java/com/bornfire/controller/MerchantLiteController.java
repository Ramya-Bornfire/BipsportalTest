package com.bornfire.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bornfire.entity.RegisterUserEntity;
import com.bornfire.service.MerchantLiteService;

@RestController
@RequestMapping("/api")
public class MerchantLiteController {
	 @Autowired
	    private MerchantLiteService merchantLiteService;

	    @PostMapping("/users/register")
	    public ResponseEntity<RegisterUserEntity> registerUser(@RequestBody RegisterUserEntity user) {
	        RegisterUserEntity savedUser = merchantLiteService.saveUser(user);
	        return ResponseEntity.ok(savedUser);
	    }
	    @GetMapping("/check")
	    public ResponseEntity<String> checkCustomerExistence(@RequestParam String mobileNumber) {
	    	 String exists = merchantLiteService.customerExistsByMobile(mobileNumber);
	    	    if (exists == null || exists.isEmpty()) {
	    	        return ResponseEntity.ok("Customer does not exist.");
	    	    } else {
	    	        return ResponseEntity.ok("Customer exists.");
	    	    }
	    }
}

