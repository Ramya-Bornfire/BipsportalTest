package com.bornfire.config;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Encryption {
	ObjectMapper objectMapper = new ObjectMapper();

	public String decrypt(String encryptedString, String psuDeviceID) throws Exception {
		String decryptedText = null;
		try {
			byte[] arrayBytes = fixKeyLength(psuDeviceID.getBytes(StandardCharsets.UTF_8));
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey key = skf.generateSecret(ks);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedText = Base64.getDecoder()
					.decode(encryptedString.replaceAll("\n", "").replaceAll("\r", ""));
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = new String(plainText, StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Decryption failed", e);
		}
		return decryptedText;
	}

	private byte[] fixKeyLength(byte[] keyBytes) {
		byte[] fixedKey = new byte[24];
		System.arraycopy(keyBytes, 0, fixedKey, 0, Math.min(keyBytes.length, fixedKey.length));
		return fixedKey;
	}

	public String encrypt(String unencryptedString, String psuDeviceID) throws Exception {
		String encryptedString = null;
		try {
			byte[] arrayBytes = fixKeyLength(psuDeviceID.getBytes(StandardCharsets.UTF_8));
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey key = skf.generateSecret(ks);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = unencryptedString.getBytes(StandardCharsets.UTF_8);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = Base64.getEncoder().encodeToString(encryptedText);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Encryption failed", e);
		}
		return encryptedString;
	}

	public String FailedResponseMessage(String message, String psuDeviceID) throws Exception {
		Map<String, String> response = new HashMap<>();
		response.put("Status", "Failed");
		response.put("Message", message);
		String jsonUserData = objectMapper.writeValueAsString(response);
		String encryptedData = encrypt(jsonUserData, psuDeviceID);
		//System.out.println("Encrypted data: " + encryptedData);
		return encryptedData;
	}

	public String SuccessResponseMessage(String message, String psuDeviceID) throws Exception {
		Map<String, String> response = new HashMap<>();
		response.put("Status", "Success");
		response.put("Message", message);
		String jsonUserData = objectMapper.writeValueAsString(response);
		String encryptedData = encrypt(jsonUserData, psuDeviceID);
		//System.out.println("Encrypted data: " + encryptedData);
		return encryptedData;
	}

	// For Device
	private static final String ALGORITHM = "DESede";
	private static final String TRANSFORMATION = "DESede/ECB/PKCS5Padding";

	public static String encryptDevice(String data, String keyString) throws Exception {
		SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(keyString), ALGORITHM);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedData = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encryptedData);
	}

	public static String decryptDevice(String encryptedData, String keyString) throws Exception {
		SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(keyString), ALGORITHM);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedData = Base64.getDecoder().decode(encryptedData);
		byte[] decryptedData = cipher.doFinal(decodedData);
		return new String(decryptedData);
	}

	public static String generateKey() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
		keyGen.init(168); // 168 bits for Triple DES
		SecretKey secretKey = keyGen.generateKey();
		return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}

}
