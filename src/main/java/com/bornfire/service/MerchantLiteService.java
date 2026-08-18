package com.bornfire.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.CustomerDetailsRepository;
import com.bornfire.entity.IPSAuditRepo;
import com.bornfire.entity.IPSAuditTable;
import com.bornfire.entity.RegisterUserEntity;
import com.bornfire.entity.RegisterUserRepository;
import com.bornfire.entity.UserManagementEntity;
import com.bornfire.entity.UserManagementRepository;
@Service
@ConfigurationProperties("output")
@Transactional
public class MerchantLiteService {

		@Autowired
	    private RegisterUserRepository registerUserRepository;
		

		@Autowired
		SequenceGenerator sequence;
		
		@Autowired
		IPSAuditRepo ipsAuditTableRep;
		
		@Autowired
		UserManagementRepository usermanagementrepository;
		
		 @Autowired
		 private CustomerDetailsRepository customerDetailsRepository;

		public String customerExistsByMobile(String mobileNumber) {
			 String result = customerDetailsRepository.findByMobileNumber(mobileNumber);
			 return result != null ? result : "";
		}

	    public RegisterUserEntity saveUser(RegisterUserEntity user) {
	       
	        UserManagementEntity userMgmt = new UserManagementEntity();
	        userMgmt.setMerchant_user_id(user.getCustomerId());
	        userMgmt.setMerchant_name(user.getCustomerName());
	        userMgmt.setUser_id(user.getCustomerId());
	        userMgmt.setUser_name(user.getCustomerName());
	        userMgmt.setEntry_user(user.getEntryUser());
	        userMgmt.setPassword1("BobUser@123"); // Optional encryption here
	        userMgmt.setUser_status1("ACTIVE");
	        userMgmt.setLogin_status1("N");
	        userMgmt.setUser_disable_flag1("N");
	        userMgmt.setDel_flag1("N");
	        userMgmt.setEntry_flag("N");
	        userMgmt.setModify_flag("N");
	        userMgmt.setUser_locked_flg("N");
	        userMgmt.setNo_of_attmp(BigDecimal.ZERO);
	        userMgmt.setEntry_time(new Date());
	        userMgmt.setUser_category("User");
	        userMgmt.setMake_or_checker("Maker");
	        userMgmt.setPassword_life1("180");
	        userMgmt.setAuthentication_flg("N");

	        // Optionally set expiry dates
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.MONTH, 6);
	        userMgmt.setPassword_expiry_date1(cal.getTime());

	        Calendar cal1 = Calendar.getInstance();
	        cal1.add(Calendar.YEAR, 1);
	        userMgmt.setAccount_expiry_date1(cal1.getTime());

	        usermanagementrepository.save(userMgmt);
	        user.setEntryTime(new Date());
	        user.setEntityFlag("Y");
	        user.setDelFlag("N");
	        
	        String audit_ref_no = sequence.generateRequestUUId();
	        if (audit_ref_no == null) {
	            throw new IllegalStateException("Failed to generate audit reference number");
	        }

	        IPSAuditTable audit = new IPSAuditTable();
	        audit.setAudit_date(new Date());
	        audit.setEntry_time(new Date());
	        audit.setEntry_user(user.getEntryUser());
	        audit.setFunc_code("USER CREATION");
	        audit.setRemarks(user.getCustomerId() + " : User Created Successfully");
	        audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
	        audit.setAudit_screen("USER DETAILS");
	        audit.setEvent_id(user.getCustomerId());
	        audit.setEvent_name(user.getCustomerName());
	        audit.setModi_details("-");
	        audit.setAudit_ref_no(audit_ref_no);

	        ipsAuditTableRep.save(audit);

	        return registerUserRepository.save(user);
	    }
}
