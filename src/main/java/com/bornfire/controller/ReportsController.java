package com.bornfire.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.entity.BIPS_Charge_Back_Entity;
import com.bornfire.entity.BIPS_Charge_Back_Rep;
import com.bornfire.entity.MerchantMaster;
import com.bornfire.entity.MerchantMasterRep;
import com.bornfire.entity.OutwardTransactionMonitoringTable;
import com.bornfire.entity.OutwardTransactionMonitoringTableRep;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@RestController
@RequestMapping("/api")
public class ReportsController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminModule.class);
	@Autowired
	OutwardTransactionMonitoringTableRep outwardTransactionMonitoringTableRep;
	
	@Autowired
	BIPS_Charge_Back_Rep bIPS_Charge_Back_Rep;
	
	@Autowired
	Environment env;

	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	MerchantMasterRep merchantMasterRep;
	
	@GetMapping("/transactionListReports")
	public List<OutwardTransactionMonitoringTable> transactionListReports(@RequestParam String merchant_id,@RequestParam String tran_type,
			@RequestParam String from_date,@RequestParam String to_date,@RequestParam(required = false) String unit_id) {
		if (Objects.nonNull(unit_id)) {
			if(tran_type.equals("ALL")) {
				return outwardTransactionMonitoringTableRep.findAllUnitTansactionBetweenDates(merchant_id,unit_id,from_date,to_date);
			}else {
				return outwardTransactionMonitoringTableRep.findAllUnitTansactionBetweenDates(merchant_id,tran_type,unit_id,from_date,to_date);
			}
		}else {
			
			if(tran_type.equals("ALL")) {
				return outwardTransactionMonitoringTableRep.findAllTansactionBetweenDates(merchant_id,from_date,to_date);
			}else {
				return outwardTransactionMonitoringTableRep.findAllTansactionBetweenDates(merchant_id,tran_type,from_date,to_date);
			}
			
		}
	}
	
	
	@GetMapping("/chargebackTransactionListReports")
	public List<BIPS_Charge_Back_Entity> chargebackTransactionListReports(@RequestParam String merchant_id,@RequestParam String tran_type,
			@RequestParam String from_date,@RequestParam String to_date,@RequestParam(required = false) String unit_id) {
		if (Objects.nonNull(unit_id)) {
			if(tran_type.equals("ALL")) {
				return bIPS_Charge_Back_Rep.findAllUnitTansactionBetweenDates(merchant_id,unit_id,from_date,to_date);
			}else {
				return bIPS_Charge_Back_Rep.findAllUnitTansactionBetweenDates(merchant_id,tran_type,unit_id,from_date,to_date);
			}
		}else {
			
			if(tran_type.equals("ALL")) {
				return bIPS_Charge_Back_Rep.findAllTansactionBetweenDates(merchant_id,from_date,to_date);
			}else {
				return bIPS_Charge_Back_Rep.findAllTansactionBetweenDates(merchant_id,tran_type,from_date,to_date);
			}
			
		}
	}
	
	
	@GetMapping("/TransactionReportDownload")
	public ResponseEntity<byte[]> TransactionReportDownload(
	        @RequestParam String merchant_id,
	        @RequestParam String tran_type,
	        @RequestParam String from_date,
	        @RequestParam String to_date,
	        @RequestParam(required = false) String unit_id,
	        @RequestParam String filetype) {

	    String path = env.getProperty("output.exportpath");
	    MerchantMaster merchant_bank_add=merchantMasterRep.findByIdCustom(merchant_id);
	    String bank_address = null;
	    
	    if(Objects.nonNull(merchant_bank_add.getDetailed_address1())) {
	    	bank_address=merchant_bank_add.getDetailed_address1();
	    }else {
	    	bank_address = env.getProperty("default.bank_address");
	    }    
	    String fileName = "Transaction_List_" + from_date + "_" + to_date;
	    String fullPath;
	    byte[] outputFile;
	    System.out.println(merchant_id + tran_type + unit_id+from_date+to_date+filetype);
	  //tran_type should be SUCCESS or FAILURE
	    try {
	        InputStream jasperFile;
	        HashMap<String, Object> parameters = new HashMap<>();
	        parameters.put("TRAN_DATE1", from_date);
	        parameters.put("TRAN_DATE2", to_date);
	        parameters.put("merchant_id", merchant_id);
	        parameters.put("DETAILED_ADDRESS", bank_address);
	        
	        if (!Objects.nonNull(unit_id)) {
	        	parameters.put("unit_id", unit_id);
	        	if (tran_type.equals("ALL")) {
	                jasperFile = this.getClass().getResourceAsStream("/static/jasper/bips_transaction_unit_all.jrxml");
	            } else {
	                parameters.put("tran_status", tran_type);
	                jasperFile = this.getClass().getResourceAsStream("/static/jasper/bips_transaction_unit.jrxml");
	            }
	           
	        } else {
	        	 if (tran_type.equals("ALL")) {
		                jasperFile = this.getClass().getResourceAsStream("/static/jasper/bips_transaction_allv2.jrxml");
		            } else {
		                parameters.put("tran_status", tran_type);
		                jasperFile = this.getClass().getResourceAsStream("/static/jasper/bips_transaction.jrxml");
		            }
	            
	        }

	        JasperReport jr = JasperCompileManager.compileReport(jasperFile);
	        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, srcdataSource.getConnection());

	        if ("pdf".equalsIgnoreCase(filetype)) {
	            fileName += ".pdf";
	            fullPath = path + fileName;
	            JasperExportManager.exportReportToPdfFile(jp, fullPath);
	        } else if ("excel".equalsIgnoreCase(filetype)) {
	            fileName += ".xlsx";
	            fullPath = path + fileName;
	            JRXlsxExporter exporter = new JRXlsxExporter();
	            exporter.setExporterInput(new SimpleExporterInput(jp));
	            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fullPath));
	            exporter.exportReport();
	        } else {
	            throw new IllegalArgumentException("Invalid file type: " + filetype);
	        }

	        // Read the file content
	        File file = new File(fullPath);
	        outputFile = Files.readAllBytes(file.toPath());

	        // Prepare the response headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType("pdf".equalsIgnoreCase(filetype) ? MediaType.APPLICATION_PDF : MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());

	        return new ResponseEntity<>(outputFile, headers, HttpStatus.OK);

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } catch (JRException | SQLException | IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}


	@GetMapping("/ChargebackTransactionReportDownload")
	public ResponseEntity<byte[]> ChargebackTransactionReportDownload(
	        @RequestParam String merchant_id,
	        @RequestParam String tran_type,
	        @RequestParam String from_date,
	        @RequestParam String to_date,
	        @RequestParam(required = false) String unit_id,
	        @RequestParam String filetype) {

	    String path = env.getProperty("output.exportpath");
	    MerchantMaster merchant_bank_add=merchantMasterRep.findByIdCustom(merchant_id);
	    String bank_address = null;
	    
	    if(Objects.nonNull(merchant_bank_add.getDetailed_address1())) {
	    	bank_address=merchant_bank_add.getDetailed_address1();
	    }else {
	    	bank_address = env.getProperty("default.bank_address");
	    }  
	    
	    String fileName = "Chargeback_Transaction_List_" + from_date + "_" + to_date;
	    String fullPath;
	    byte[] outputFile;
	    System.out.println(merchant_id + tran_type + unit_id+from_date+to_date+filetype);
	    //tran_type should be REVERTED or PENDING
	    try {
	        InputStream jasperFile;
	        HashMap<String, Object> parameters = new HashMap<>();
	        parameters.put("TRAN_DATE1", from_date);
	        parameters.put("TRAN_DATE2", to_date);
	        parameters.put("merchant_id", merchant_id);
	        parameters.put("DETAILED_ADDRESS", bank_address);
	        if (!Objects.nonNull(unit_id)) {
	        	parameters.put("unit_id", unit_id);
	        	if (tran_type.equals("ALL")) {
	                jasperFile = this.getClass().getResourceAsStream("/static/jasper/ChargebackUnit_all.jrxml");
	            } else {
	                parameters.put("tran_status", tran_type);
	                jasperFile = this.getClass().getResourceAsStream("/static/jasper/Chargeback_unit.jrxml");
	            }
	           
	        } else {
	        	 if (tran_type.equals("ALL")) {
		                jasperFile = this.getClass().getResourceAsStream("/static/jasper/Chargeback_all.jrxml");
		            } else {
		                parameters.put("tran_status", tran_type);
		                jasperFile = this.getClass().getResourceAsStream("/static/jasper/Chargeback.jrxml");
		            }
	            
	        }

	        JasperReport jr = JasperCompileManager.compileReport(jasperFile);
	        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, srcdataSource.getConnection());

	        if ("pdf".equalsIgnoreCase(filetype)) {
	            fileName += ".pdf";
	            fullPath = path + fileName;
	            JasperExportManager.exportReportToPdfFile(jp, fullPath);
	        } else if ("excel".equalsIgnoreCase(filetype)) {
	            fileName += ".xlsx";
	            fullPath = path + fileName;
	            JRXlsxExporter exporter = new JRXlsxExporter();
	            exporter.setExporterInput(new SimpleExporterInput(jp));
	            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fullPath));
	            exporter.exportReport();
	        } else {
	            throw new IllegalArgumentException("Invalid file type: " + filetype);
	        }

	        // Read the file content
	        File file = new File(fullPath);
	        outputFile = Files.readAllBytes(file.toPath());

	        // Prepare the response headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType("pdf".equalsIgnoreCase(filetype) ? MediaType.APPLICATION_PDF : MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());

	        return new ResponseEntity<>(outputFile, headers, HttpStatus.OK);

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } catch (JRException | SQLException | IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
}
