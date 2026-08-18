package com.bornfire.iserveu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/soundbox")
public class SoundboxController {

    private final SoundboxService soundboxService;

    public SoundboxController(SoundboxService soundboxService) {
        this.soundboxService = soundboxService;
    }

    @PostMapping("/trigger")
    public ResponseEntity<?> trigger(@RequestBody SoundboxTriggerRequest request) {
        try {
            String response = soundboxService.triggerSoundbox(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Soundbox trigger failed: " + e.getMessage());
        }
    }
}