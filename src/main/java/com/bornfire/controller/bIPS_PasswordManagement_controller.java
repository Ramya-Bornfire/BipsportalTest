package com.bornfire.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.entity.BIPS_PasswordManagement_Repo;
import com.bornfire.entity.LoginEntity;
import com.bornfire.entity.LoginRepository;;

@RestController
@RequestMapping("/api")
public class bIPS_PasswordManagement_controller {
	@Autowired
	BIPS_PasswordManagement_Repo bIPS_PasswordManagement_Repo;
	@Autowired
	LoginRepository loginRepository;
	

	@RequestMapping(value = "passwordManagement", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> registerUser(
			 Model md,
			HttpServletRequest rq, @RequestBody LoginEntity LoginEntity) {
		//System.out.println(LoginEntity.getMerchant_user_id());
	
		try {
			// Check if user status is "Active" and set disable flag accordingly
			LoginEntity Setdata = loginRepository.getRepId(LoginEntity.getMerchant_rep_id());
			LoginEntity.setMerchant_corporate_name(Setdata.getMerchant_corporate_name());
			LoginEntity.setMerchant_legal_user_id(Setdata.getMerchant_legal_user_id());
			LoginEntity.setPassword(Setdata.getPassword());
			LoginEntity.setPassword_expiry_date(Setdata.getPassword_expiry_date());
			LoginEntity.setPassword_life(Setdata.getPassword_life());
			LoginEntity.setUser_disable_flag(Setdata.getUser_disable_flag());
			LoginEntity.setLogin_status(Setdata.getLogin_status());
			LoginEntity.setEntry_user(Setdata.getEntry_user());
			LoginEntity.setPwlog_flg(Setdata.getPwlog_flg());
			LoginEntity.setMaker_or_checker(Setdata.getMaker_or_checker()); 
			LoginEntity.setNo_of_attmp(Setdata.getNo_of_attmp());
			LoginEntity.setUser_locked_flg(Setdata.getUser_locked_flg());
			LoginEntity.setUser_category(Setdata.getUser_category());
			LoginEntity.setAuthentication_flg(Setdata.getAuthentication_flg());
			LoginEntity.setUnit_id(Setdata.getUnit_id());
			LoginEntity.setUnit_name(Setdata.getUnit_name());
			LoginEntity.setUnit_type(Setdata.getUnit_type());
			
			// Save the entity to the repository
			loginRepository.save(LoginEntity);

			// Return a success response
			return ResponseEntity.ok("Password modified successfully!");
		} catch (Exception e) {
			// If an exception occurs, return an error response
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user.");
		}
	}
	
	@GetMapping("/passwordviewdetail")
	public LoginEntity passwordviewdetail(@RequestParam String merchant_rep_id) {
		LoginEntity existingUser = loginRepository.getRepId(merchant_rep_id);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}
	
	@GetMapping("/passwordeditdetail")
	public LoginEntity passwordeditdetail(@RequestParam String merchant_rep_id) {
		LoginEntity existingUser = loginRepository.getRepId(merchant_rep_id);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}
	
	@GetMapping("/passwordverifydetail")
	public LoginEntity passwordverifydetail(@RequestParam String merchant_rep_id) {
		LoginEntity existingUser = loginRepository.getRepId(merchant_rep_id);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}
	
	@GetMapping("/RepQr")
	public String RepQr(@RequestParam String staticQrId) {
		String existingUser = bIPS_PasswordManagement_Repo.getRepQR(staticQrId);
        System.out.print(existingUser);
		return existingUser; 
	}
	
	@GetMapping("/CreateAuthFlg")
	public LoginEntity CreateAuthFlg(@RequestParam String USERID, HttpServletRequest req) {
		
		
		LoginEntity LoginEntity = loginRepository.findById(USERID).orElse(new LoginEntity());
		  System.out.print(LoginEntity.getAuthentication_flg());
		
		return LoginEntity ; 
	}


}
