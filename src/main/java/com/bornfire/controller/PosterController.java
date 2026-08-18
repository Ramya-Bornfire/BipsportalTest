package com.bornfire.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.InfoTableEntity;
import com.bornfire.entity.InfoTableRepo;
import com.bornfire.entity.PosterEntity;
import com.bornfire.entity.PosterRepository;

@RestController
@RequestMapping("/images")
public class PosterController {

	private static final Logger logger = LoggerFactory.getLogger(PosterController.class);
	
    @Autowired
    private PosterRepository imageRepository;
    
    @Autowired
    private SequenceGenerator uniqueIdGenerator;
    
    //Poster List
    @GetMapping("/PosterList")
	public List<PosterEntity> getAlldevicedetails(@RequestParam String merchant_user_id){
		List<PosterEntity> existingUser = imageRepository.findByAll(merchant_user_id);    	
		return existingUser;
	}
    
    //Upload photos
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam String unit_name, @RequestParam("date") @DateTimeFormat(pattern="dd-MM-yyyy") Date date,
    		@RequestParam String unit_id,@RequestParam String merchant_id, @RequestParam String merchant_rep_id, @RequestParam String frequency, 
    		@RequestParam("from_date") @DateTimeFormat(pattern="dd-MM-yyyy") Date from_date, @RequestParam("to_date") @DateTimeFormat(pattern="dd-MM-yyyy") Date to_date) {
        try {
        	String posterId = uniqueIdGenerator.generateUniqueId();
            PosterEntity image = new PosterEntity();
            image.setPoster_id(posterId);
            image.setFrequency(frequency);
            image.setFrom_date(from_date);
            image.setTo_date(to_date);
            image.setUnit_name(unit_name);
            image.setImage_name(file.getOriginalFilename());
            image.setPoster_date(date);
            image.setUnit_id(unit_id);
            image.setMerchant_id(merchant_id);
            image.setMerchant_rep_id(merchant_rep_id);
            image.setFile_name(file.getBytes());
            imageRepository.save(image);
            logger.debug("Poster Uploaded Successfully");
            return ResponseEntity.status(HttpStatus.OK).body("Image Uploaded Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("Failed to Upload");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Upload image");
        }
    }
}
