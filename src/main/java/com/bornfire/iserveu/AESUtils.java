package com.bornfire.iserveu;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AESUtils {

    private static final int IV_LENGTH = 16;
    private static final int KEY_LENGTH = 32;

    /**
     * ---------------------------------------------------------
     * AES-256-CBC ENCRYPTION
     * ---------------------------------------------------------
     *
     * Process:
     * 1. Decode Base64 encoded AES key
     * 2. Generate random 16-byte IV
     * 3. Encrypt plain text using AES/CBC/PKCS5Padding
     * 4. Combine IV + encrypted data
     * 5. Encode complete result using standard Base64
     */
    public static String encrypt(String plainText, String base64Key) throws Exception {

        // 1. Decode Base64 encoded key
        byte[] byteKey = Base64.getDecoder().decode(base64Key);

        // Validate AES-256 key
        if (byteKey.length != KEY_LENGTH) {
            throw new IllegalArgumentException(
                    "AES key must be exactly 32 bytes after Base64 decoding. Got: "
                            + byteKey.length + " bytes"
            );
        }

        // 2. Generate random IV
        byte[] iv = new byte[IV_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        // 3. Initialize AES cipher
        SecretKeySpec secretKey = new SecretKeySpec(byteKey, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(
                Cipher.ENCRYPT_MODE,
                secretKey,
                ivParameterSpec
        );

        // Encrypt plain text
        byte[] encryptedBytes = cipher.doFinal(
                plainText.getBytes(StandardCharsets.UTF_8)
        );

        // 4. Combine IV + encrypted data
        byte[] result = new byte[iv.length + encryptedBytes.length];

        System.arraycopy(
                iv,
                0,
                result,
                0,
                iv.length
        );

        System.arraycopy(
                encryptedBytes,
                0,
                result,
                iv.length,
                encryptedBytes.length
        );

        // 5. Return Base64 encoded result
        return Base64.getEncoder().encodeToString(result);
    }


    /**
     * ---------------------------------------------------------
     * AES-256-CBC DECRYPTION
     * ---------------------------------------------------------
     *
     * Process:
     * 1. Decode Base64 encrypted input
     * 2. Decode Base64 AES key
     * 3. Extract first 16 bytes as IV
     * 4. Extract remaining bytes as ciphertext
     * 5. Decrypt using AES/CBC/PKCS5Padding
     * 6. Convert decrypted bytes to UTF-8
     * 7. Remove any extra noise after JSON object/array
     */
    public static String decrypt(
            String encryptedString,
            String base64Key
    ) throws Exception {

        // 1. Decode Base64 encoded encrypted input
        byte[] byteCipherText =
                Base64.getDecoder().decode(encryptedString);

        // 2. Decode Base64 encoded key
        byte[] byteKey =
                Base64.getDecoder().decode(base64Key);

        // Validate AES-256 key
        if (byteKey.length != KEY_LENGTH) {
            throw new IllegalArgumentException(
                    "AES key must be exactly 32 bytes after Base64 decoding. Got: "
                            + byteKey.length + " bytes"
            );
        }

        // Validate encrypted data
        if (byteCipherText.length <= IV_LENGTH) {
            throw new IllegalArgumentException(
                    "Invalid encrypted data. IV/ciphertext length: "
                            + byteCipherText.length
            );
        }

        // 3. Extract IV from first 16 bytes
        byte[] iv = Arrays.copyOfRange(
                byteCipherText,
                0,
                IV_LENGTH
        );

        // 4. Extract ciphertext after IV
        byte[] cipherText = Arrays.copyOfRange(
                byteCipherText,
                IV_LENGTH,
                byteCipherText.length
        );

        // Initialize AES key
        SecretKeySpec secretKey =
                new SecretKeySpec(byteKey, "AES");

        // Initialize IV
        IvParameterSpec ivParams =
                new IvParameterSpec(iv);

        // Initialize AES cipher
        Cipher cipher =
                Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(
                Cipher.DECRYPT_MODE,
                secretKey,
                ivParams
        );

        // 5. Decrypt ciphertext
        byte[] bytePlainText =
                cipher.doFinal(cipherText);

        // 6. Convert decrypted bytes to UTF-8
        String plainText =
                new String(
                        bytePlainText,
                        StandardCharsets.UTF_8
                );

        // 7. Remove any extra noise
        return removeNoise(plainText);
    }


    
    private static String removeNoise(String data) {

        // Find last closing curly brace
        int lastCurlyBrace =
                data.lastIndexOf('}');

        // Find last closing square bracket
        int lastSquareBracket =
                data.lastIndexOf(']');

        // Get whichever occurs later
        int lastIndex =
                Math.max(
                        lastCurlyBrace,
                        lastSquareBracket
                );

        // If JSON ending is found
        if (lastIndex != -1) {

            return data
                    .substring(0, lastIndex + 1)
                    .trim();
        }

        // If no JSON ending is found,
        // return original data
        return data.trim();
    }
}
