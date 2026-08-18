package com.bornfire.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.entity.BIPS_Charge_Back_Entity;
import com.bornfire.entity.BIPS_Charge_Back_Rep;
import com.bornfire.entity.OutwardTransactionMonitoringTable;
import com.bornfire.entity.OutwardTransactionMonitoringTableRep;

@RestController
@RequestMapping("/api")
public class ChargeBackController {
	
	@Autowired
	BIPS_Charge_Back_Rep chargebackrepo;

	@Autowired
	OutwardTransactionMonitoringTableRep outwardTransactionMonitoringTableRep;

	// Unit Admin

	@GetMapping("/UnitChargeBackListPendingForWeb")
	public List<BIPS_Charge_Back_Entity> getAllUnitChargeBacksDetailsPending(@RequestParam String merchant_id,
			@RequestParam String unit_id, @RequestParam String currentDate) {
		System.out.println("Cuurent Chargback Date" + currentDate);
		return chargebackrepo.getAllListUnitPendingForWeb(merchant_id, unit_id, currentDate, currentDate);
	}

	// Merchant Admin

	@GetMapping("/ChargeBackListPendingForWeb")
	public List<BIPS_Charge_Back_Entity> ChargeBackListPendingForWeb(@RequestParam String merchant_id,
			@RequestParam String currentDate) {
		return chargebackrepo.getAllListMerchatAdminPending(merchant_id, currentDate, currentDate);
	}

	// Select Initiate Drop down

	@GetMapping("/InitiateSingleDateforChargeback")
	public List<OutwardTransactionMonitoringTable> InitiateSingleDate(@RequestParam String merchant_id,
			@RequestParam String ValueDate, @RequestParam String unit_id) {

		if (Objects.nonNull(unit_id) && !unit_id.equalsIgnoreCase("null")) {
			return outwardTransactionMonitoringTableRep.findAllUnitTansactionBetweenDatesForChargeBack(merchant_id,
					unit_id, ValueDate, ValueDate);
		} else {
			return outwardTransactionMonitoringTableRep.findAllTansactionBetweenDatesForChargeBack(merchant_id,
					ValueDate, ValueDate);
		}
	}

}
