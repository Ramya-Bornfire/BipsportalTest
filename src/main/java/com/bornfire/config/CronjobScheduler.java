package com.bornfire.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.OutwardTransactionMonitoringTable;
import com.bornfire.entity.OutwardTransactionMonitoringTableHist;
import com.bornfire.entity.OutwardTransactionMonitoringTableHistRep;
import com.bornfire.entity.OutwardTransactionMonitoringTableRep;

@Service
@Configuration
@EnableScheduling
public class CronjobScheduler {

	@Autowired
	OutwardTransactionMonitoringTableHistRep outHistRep;

	@Autowired
	OutwardTransactionMonitoringTableRep outRep;

	@Scheduled(cron = "0 2 0 * * *") // Run Every Day 12:02 AM
	@Transactional
	public void moveRecordsToHistorySL() {
		System.out.println("<------------------------ Scheduler Start ------------------------>");
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		System.out.println("Current Date: " + formattedDate);
		List<OutwardTransactionMonitoringTable> outdatedRecords = outRep.getOutDateRecord(formattedDate);
		if (!outdatedRecords.isEmpty()) {
			List<OutwardTransactionMonitoringTableHist> histTable = new ArrayList<>();
			for (OutwardTransactionMonitoringTable mainEntity : outdatedRecords) {
				OutwardTransactionMonitoringTableHist historyEntity = new OutwardTransactionMonitoringTableHist(
						mainEntity);
				histTable.add(historyEntity);
			}
			outHistRep.saveAll(histTable);
			System.out.println("Moved outdated records to Tran History Table");
			for (OutwardTransactionMonitoringTable mainEntity : outdatedRecords) {
				try {
					outRep.delete(mainEntity);
					System.out.println("Deleted record: " + mainEntity.getMaster_ref_id());
				} catch (OptimisticLockException e) {
					System.err.println(
							"Failed to delete record due to optimistic locking: " + mainEntity.getMaster_ref_id());
				}
			}
			System.out.println("Removed Old Records from Transaction Monitoring Table");
		} else {
			System.out.println("There is No outdated record found");
		}
		
		System.out.println("<-------------------- Scheduler End --------------------->");
	}

}
