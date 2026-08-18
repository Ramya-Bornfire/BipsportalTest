package com.bornfire.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.entity.BIPS_PasswordManagement_Repo;
import com.bornfire.entity.DeviceManagementEntity;
import com.bornfire.entity.DeviceManagementRepository;
import com.bornfire.entity.LoginEntity;
import com.bornfire.entity.LoginRepository;
import com.bornfire.entity.UnitManagementEntity;
import com.bornfire.entity.UserManagementEntity;
import com.bornfire.entity.UserManagementRepository;

@RestController
@RequestMapping("/api")
public class UnitDeviceUserPasswordListController {
	@Autowired
	LoginRepository loginRepository;

	@Autowired
	UserManagementRepository Usermanagementrepository;

	@Autowired
	com.bornfire.entity.UnitManagementRepository UnitManagementRepository;

	@Autowired
	DeviceManagementRepository devicemanagement;

	@Autowired
	BIPS_PasswordManagement_Repo bIPS_PasswordManagement_Repo;

	@GetMapping("/Allentitylist")
	public List<DeviceManagementEntity> getAllDeviceDetails(@RequestParam String merchant_user_id,
			@RequestParam String unitidacess, @RequestParam String merchant_acct_no, Model md, HttpServletRequest req) {

		if (!unitidacess.equals("null")) {
			List<LoginEntity> bipsPasswordManagementEntities = loginRepository.getmerunit(merchant_acct_no,
					unitidacess);
			if (bipsPasswordManagementEntities != null && !bipsPasswordManagementEntities.isEmpty()) {
				for (LoginEntity bipsPasswordManagementEntity : bipsPasswordManagementEntities) {
					String isDelFlagActive = bipsPasswordManagementEntity.getPwlog_flg();
					if (isDelFlagActive.equals("UNIT")) {
						md.addAttribute("MerchantUnit",
								UnitManagementRepository.getUnitId(merchant_acct_no, unitidacess));
						md.addAttribute("pro", Usermanagementrepository.getUserManageId(merchant_acct_no, unitidacess));
						md.addAttribute("MerchantDevi", devicemanagement.getaddDeviceId(merchant_acct_no, unitidacess));
						md.addAttribute("propass",
								bIPS_PasswordManagement_Repo.getPassmerId(merchant_acct_no, unitidacess));
					} else {
						md.addAttribute("MerchantUnit",
								UnitManagementRepository.getUnitId(merchant_acct_no, unitidacess));
						md.addAttribute("pro", Usermanagementrepository.getUserManageId(merchant_acct_no, unitidacess));
						md.addAttribute("MerchantDevi", devicemanagement.getaddDeviceId(merchant_acct_no, unitidacess));
						md.addAttribute("propass",
								bIPS_PasswordManagement_Repo.getPassmerId(merchant_acct_no, unitidacess));
					}
				}
			} else {
				//System.out.println("No entities found for the given merchant account number.");
			}
		} else {
			List<LoginEntity> bipsPasswordManagementEntitiess = loginRepository.getmersecondif(merchant_acct_no);
			if (bipsPasswordManagementEntitiess != null) {
				for (LoginEntity bipsPasswordManagementEntity : bipsPasswordManagementEntitiess) {
					String isDelFlagActive = bipsPasswordManagementEntity.getPwlog_flg();
					if (isDelFlagActive.equals("UNIT")) {
						md.addAttribute("MerchantUnit", UnitManagementRepository.getUnitlist(merchant_acct_no));
						md.addAttribute("pro", Usermanagementrepository.getUserManage1(merchant_acct_no));
						md.addAttribute("MerchantDevi", devicemanagement.getaddDevice(merchant_acct_no));
						md.addAttribute("propass", bIPS_PasswordManagement_Repo.getPassmer(merchant_acct_no));
					} else {
						md.addAttribute("MerchantUnit",
								UnitManagementRepository.getUnitId(merchant_acct_no, unitidacess));
					}
				}
			} else {
				//System.out.println("No entities found for the given merchant account number.");
			}
		}
		return new ArrayList<>();
	}

	@GetMapping("/MerchantUnit")
	public List<UnitManagementEntity> MerchantUnit(@RequestParam String merchant_user_id,
			@RequestParam String unitidacess, @RequestParam String merchant_acct_no, Model md, HttpServletRequest req) {

		List<UnitManagementEntity> existingdevicelist = new ArrayList<>();
		if (!unitidacess.equals("null")) {
			List<LoginEntity> bipsPasswordManagementEntities = loginRepository.getmerunit(merchant_acct_no,
					unitidacess);
			if (bipsPasswordManagementEntities != null && !bipsPasswordManagementEntities.isEmpty()) {
				for (LoginEntity bipsPasswordManagementEntity : bipsPasswordManagementEntities) {
					String isDelFlagActive = bipsPasswordManagementEntity.getPwlog_flg();
					if ("UNIT".equals(isDelFlagActive)) {
						existingdevicelist = UnitManagementRepository.getUnitId(merchant_acct_no, unitidacess);
						break;
					} else {
						existingdevicelist = UnitManagementRepository.getUnitlist(merchant_acct_no);
						break;
					}
				}
			} else {
				//System.out.println("No entities found for the given merchant account number and unitidacess.");
			}
		} else {
			List<LoginEntity> bipsPasswordManagementEntitiess = loginRepository.getmersecondif(merchant_acct_no);
			if (bipsPasswordManagementEntitiess != null && !bipsPasswordManagementEntitiess.isEmpty()) {
				for (LoginEntity bipsPasswordManagementEntity : bipsPasswordManagementEntitiess) {
					String isDelFlagActive = bipsPasswordManagementEntity.getPwlog_flg();
					if ("MERCHANT".equals(isDelFlagActive)) {
						existingdevicelist = UnitManagementRepository.getUnitlist(merchant_acct_no);
						break;
					} else {
						existingdevicelist = UnitManagementRepository.getUnitlist(merchant_acct_no);
						break;
					}
				}
			} else {
				//System.out.println("No entities found for the given merchant account number.");
			}
		}
		return existingdevicelist;
	}

	@GetMapping("/Merchantuser")
	public List<UserManagementEntity> Merchantuser(@RequestParam String merchant_user_id,
			@RequestParam String unitidacess, @RequestParam String merchant_acct_no, Model md, HttpServletRequest req) {

		List<UserManagementEntity> existingdevicelist1 = new ArrayList<>();
		if (!unitidacess.equals("null")) {
			List<LoginEntity> bipsPasswordManagementEntities = loginRepository.getmerunit(merchant_acct_no,
					unitidacess);
			if (bipsPasswordManagementEntities != null && !bipsPasswordManagementEntities.isEmpty()) {
				for (LoginEntity bipsPasswordManagementEntity : bipsPasswordManagementEntities) {
					String isDelFlagActive = bipsPasswordManagementEntity.getPwlog_flg();
					if ("UNIT".equals(isDelFlagActive)) {
						//System.out.println("UNIT is active");
						existingdevicelist1 = Usermanagementrepository.getUserManageId(merchant_acct_no, unitidacess);
						break;
					} else {
						//System.out.println("Other case for MERCHANT");
						existingdevicelist1 = Usermanagementrepository.getUserManage1(merchant_acct_no);
						break;
					}
				}
			} else {
				//System.out.println("No entities found for the given merchant account number and unitidacess.");
			}
		} else {
			List<LoginEntity> bipsPasswordManagementEntitiess = loginRepository.getmersecondif(merchant_acct_no);
			if (bipsPasswordManagementEntitiess != null && !bipsPasswordManagementEntitiess.isEmpty()) {
				for (LoginEntity bipsPasswordManagementEntity : bipsPasswordManagementEntitiess) {
					String isDelFlagActive = bipsPasswordManagementEntity.getPwlog_flg();
					if ("MERCHANT".equals(isDelFlagActive)) {
						existingdevicelist1 = Usermanagementrepository.getUserManage1(merchant_acct_no);
						break;
					} else {
						existingdevicelist1 = Usermanagementrepository.getUserManage1(merchant_acct_no);
						break;
					}
				}
			} else {
				//System.out.println("No entities found for the given merchant account number.");
			}
		}
		return existingdevicelist1;
	}

	@GetMapping("/Merchantdevice")
	public List<DeviceManagementEntity> Merchantdevice(@RequestParam String merchant_user_id,
			@RequestParam String unitidacess, @RequestParam String merchant_acct_no, Model md, HttpServletRequest req) {

		List<DeviceManagementEntity> existingdevicelist2 = new ArrayList<>();
		if (!unitidacess.equals("null")) {
			List<LoginEntity> bipsPasswordManagementEntities = loginRepository.getmerunit(merchant_acct_no,
					unitidacess);
			if (bipsPasswordManagementEntities != null && !bipsPasswordManagementEntities.isEmpty()) {
				for (LoginEntity bipsPasswordManagementEntity : bipsPasswordManagementEntities) {
					String isDelFlagActive = bipsPasswordManagementEntity.getPwlog_flg();
					if ("UNIT".equals(isDelFlagActive)) {
						existingdevicelist2 = devicemanagement.getaddDeviceId(merchant_acct_no, unitidacess);
						break;
					} else {
						existingdevicelist2 = devicemanagement.getaddDevice(merchant_acct_no);
						break;
					}
				}
			} else {
				//System.out.println("No entities found for the given merchant account number and unitidacess.");
			}
		} else {
			List<LoginEntity> bipsPasswordManagementEntitiess = loginRepository.getmersecondif(merchant_acct_no);
			if (bipsPasswordManagementEntitiess != null && !bipsPasswordManagementEntitiess.isEmpty()) {
				for (LoginEntity bipsPasswordManagementEntity : bipsPasswordManagementEntitiess) {
					String isDelFlagActive = bipsPasswordManagementEntity.getPwlog_flg();
					if ("MERCHANT".equals(isDelFlagActive)) {
						existingdevicelist2 = devicemanagement.getaddDevice(merchant_acct_no);
					} else {
						existingdevicelist2 = devicemanagement.getaddDevice(merchant_acct_no);
						break;
					}
				}
			} else {
				//System.out.println("No entities found for the given merchant account number.");
			}
		}
		return existingdevicelist2;
	}

	@GetMapping("/Merchantpassword")
	public List<LoginEntity> Merchantpassword(@RequestParam String merchant_user_id, @RequestParam String unitidacess,
			@RequestParam String merchant_acct_no, Model md, HttpServletRequest req) {

		List<LoginEntity> existingdevicelist3 = new ArrayList<>();
		if (!unitidacess.equals("null")) {
			List<LoginEntity> bipsPasswordManagementEntities = loginRepository.getmerunit(merchant_acct_no,
					unitidacess);
			if (bipsPasswordManagementEntities != null && !bipsPasswordManagementEntities.isEmpty()) {
				for (LoginEntity bipsPasswordManagementEntity : bipsPasswordManagementEntities) {
					String isDelFlagActive = bipsPasswordManagementEntity.getPwlog_flg();
					if ("UNIT".equals(isDelFlagActive)) {
						existingdevicelist3 = loginRepository.getPassmerId(merchant_acct_no, unitidacess);
						break;
					} else {
						existingdevicelist3 = loginRepository.getPassmer(merchant_acct_no);
						break;
					}
				}
			} else {
				//System.out.println("No entities found for the given merchant account number and unitidacess.");
			}
		} else {
			List<LoginEntity> bipsPasswordManagementEntitiess = loginRepository.getmersecondif(merchant_acct_no);
			if (bipsPasswordManagementEntitiess != null && !bipsPasswordManagementEntitiess.isEmpty()) {
				for (LoginEntity bipsPasswordManagementEntity : bipsPasswordManagementEntitiess) {
					String isDelFlagActive = bipsPasswordManagementEntity.getPwlog_flg();
					if ("MERCHANT".equals(isDelFlagActive)) {
						existingdevicelist3 = loginRepository.getPassmer(merchant_acct_no);
					} else {
						existingdevicelist3 = loginRepository.getPassmer(merchant_acct_no);
						break;
					}
				}
			} else {
				//System.out.println("No entities found for the given merchant account number.");
			}
		}
		return existingdevicelist3;
	}

}
