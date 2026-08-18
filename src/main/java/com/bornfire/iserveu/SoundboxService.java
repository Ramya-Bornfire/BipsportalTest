package com.bornfire.iserveu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class SoundboxService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate = new RestTemplate();

    // ============================================================
    // ISERVEU API URL
    // ============================================================

    private static final String API_URL =
            "https://auth-dev-stage.iserveu.online/common/isu_soundbox/txn/device/trigger";

    // ============================================================
    // ISERVEU CREDENTIALS
    // ============================================================

  
    private static final String PASS_KEY = "QC62FQKXT2DQTO43LMWH5A44UKVPQ7LK5Y6HVHRQ3XTIKLDTB6HA";

    private static final String AES_KEY = "a6T8tOCYiSzDTrcqPvCbJfy0wSQOVcfaevH0gtwCtoU=";

    private static final String CLIENT_ID = "EMI8PIj5Esi7T5Q4LVH5X5LHe5uNwqIp0BKdL3sCl8WHlAAb";

    private static final String CLIENT_SECRET = "Q4jAlwLuSUcNNE87D2W3b8PQHv25aKxiYrotlXcxcyX6AOx8BdcLprJCqFGHGVXG";

    // ============================================================
    // TRIGGER SOUNDBOX
    // ============================================================

    public String triggerSoundbox(
            SoundboxTriggerRequest request) throws Exception {

        // ========================================================
        // 1. CREATE TRANSACTION JSON
        // ========================================================

   

        Map<String, String> transaction = new LinkedHashMap<>();

        transaction.put("column1", request.getColumn1());
        transaction.put("column12", request.getColumn12());
        transaction.put("column15", request.getColumn15());
        transaction.put("column3", request.getColumn3());
        transaction.put("column41", request.getColumn41());
        transaction.put("column7", request.getColumn7());
        transaction.put("column8", request.getColumn8());

        String transactionJson =
                objectMapper.writeValueAsString(transaction);

        System.out.println("\n================================");
        System.out.println("TRANSACTION JSON:");
        System.out.println(transactionJson);
       


        // ========================================================
        // 2. ENCRYPT TRANSACTION JSON
        // ========================================================

        /*
         * AESUtils.encrypt() does:
         *
         * 1. Base64 decode AES key
         * 2. Generate random 16-byte IV
         * 3. AES-256-CBC encryption
         * 4. Combine IV + ciphertext
         * 5. Standard Base64 encoding
         */

        String encryptedRequestData =
                AESUtils.encrypt(
                        transactionJson,
                        AES_KEY
                );


        System.out.println("ENCRYPTED REQUEST DATA:");
        System.out.println(encryptedRequestData);


        // ========================================================
        // 3. CREATE HEADER SECRETS
        // ========================================================

        /*
         * iServeU expects epoch in seconds.
         *
         * Example:
         * 1750000000
         *
         * NOT milliseconds:
         * 1750000000000
         */

        long epoch =
                Instant.now().getEpochSecond();


        Map<String, String> headerSecrets =
                new LinkedHashMap<>();


        // IMPORTANT:
        // Keep client_id in lowercase.

        headerSecrets.put(
                "client_id",
                CLIENT_ID
        );

        headerSecrets.put(
                "client_secret",
                CLIENT_SECRET
        );

        headerSecrets.put(
                "epoch",
                String.valueOf(epoch)
        );


        String headerSecretsJson =
                objectMapper.writeValueAsString(
                        headerSecrets
                );


        System.out.println("HEADER SECRETS JSON:");
        System.out.println(headerSecretsJson);


        // ========================================================
        // 4. ENCRYPT HEADER SECRETS
        // ========================================================

        /*
         * IMPORTANT:
         *
         * Your current AESUtils has:
         *
         * AESUtils.encrypt()
         *
         * It does NOT have:
         *
         * AESUtils.encryptUrlSafe()
         *
         * Therefore use AESUtils.encrypt() here.
         */

        String encryptedHeaderSecrets =
                AESUtils.encrypt(
                        headerSecretsJson,
                        AES_KEY
                );


        System.out.println("ENCRYPTED HEADER SECRETS:");
        System.out.println(encryptedHeaderSecrets);


        // ========================================================
        // 5. CREATE REQUEST BODY
        // ========================================================

        Map<String, String> body =
                new LinkedHashMap<>();


        body.put(
                "RequestData",
                encryptedRequestData
        );


        String requestBody =
                objectMapper.writeValueAsString(body);


        System.out.println("FINAL REQUEST BODY:");
        System.out.println(requestBody);


        // ========================================================
        // 6. CREATE HTTP HEADERS
        // ========================================================

        HttpHeaders headers =
                new HttpHeaders();


        headers.setContentType(
                MediaType.APPLICATION_JSON
        );


        headers.set(
                "pass_key",
                PASS_KEY
        );


        headers.set(
                "header_secrets",
                encryptedHeaderSecrets
        );


        // ========================================================
        // 7. CREATE HTTP ENTITY
        // ========================================================

        HttpEntity<String> entity =
                new HttpEntity<>(
                        requestBody,
                        headers
                );


        // ========================================================
        // 8. CALL ISERVEU API
        // ========================================================

        System.out.println("\n=================================");
        System.out.println("CALLING ISERVEU API:");
        System.out.println(API_URL);


        try {

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            API_URL,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );


            // ====================================================
            // 9. RESPONSE
            // ====================================================

            System.out.println(
                    "HTTP STATUS = "
                            + response.getStatusCode()
            );

            System.out.println(
                    "RESPONSE = "
                            + response.getBody()
            );


            String responseBody =
                    response.getBody();


            // ====================================================
            // 10. SUCCESS RESPONSE
            // ====================================================

            if (
                    response.getStatusCode().is2xxSuccessful()
                            &&
                    responseBody != null
                            &&
                    !responseBody.trim().isEmpty()
            ) {

                JsonNode responseJson =
                        objectMapper.readTree(responseBody);


                // =================================================
                // CHECK ResponseData
                // =================================================

                if (
                        responseJson.has("ResponseData")
                        &&
                        !responseJson.get("ResponseData").isNull()
                ) {

                    String encryptedResponse =
                            responseJson
                                    .get("ResponseData")
                                    .asText();


                    System.out.println(
                            "ENCRYPTED RESPONSE:"
                    );

                    System.out.println(
                            encryptedResponse
                    );


                    // =============================================
                    // DECRYPT RESPONSE
                    // =============================================

                    String decryptedResponse =
                            AESUtils.decrypt(
                                    encryptedResponse,
                                    AES_KEY
                            );


                    System.out.println(
                            "DECRYPTED RESPONSE:"
                    );

                    System.out.println(
                            decryptedResponse
                    );


                    return decryptedResponse;
                }


                // =================================================
                // NO ResponseData
                // =================================================

                System.out.println(
                        "ResponseData not found in response."
                );

                return responseBody;
            }


            return responseBody;


        } catch (HttpClientErrorException e) {

            System.err.println(
                    "\n========== ISERVEU ERROR =========="
            );

            System.err.println(
                    "HTTP STATUS: "
                            + e.getStatusCode()
            );

            System.err.println(
                    "STATUS CODE: "
                            + e.getRawStatusCode()
            );

            System.err.println(
                    "RESPONSE BODY:"
            );

            System.err.println(
                    e.getResponseBodyAsString()
            );

            System.err.println(
                    "RESPONSE HEADERS:"
            );

            System.err.println(
                    e.getResponseHeaders()
            );

            System.err.println(
                    "=================================="
            );


            throw new RuntimeException(
                    "iServeU API error: "
                            + e.getResponseBodyAsString(),
                    e
            );
        }
    }
}