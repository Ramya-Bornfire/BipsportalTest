package com.bornfire.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateValidity;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

@Component
public class Listener {
	private static KeyStore keyStore;
	private static Cipher c;
	private static Cipher c1;
	static {
		try {
			// one time instance creation
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}
	static {
		try {
			c = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	static {
		try {
			c1 = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	Environment env;

	public XMLGregorianCalendar getxmlGregorianCalender(String type) {
		String dataFormat = null;
		switch (type) {
		case "0":
			dataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date());
			break;
		case "1":
			dataFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			break;
		case "2":
			dataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000").format(new Date());
			break;
		}

		XMLGregorianCalendar xgc = null;
		try {
			xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(dataFormat);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return xgc;
	}

	public XMLGregorianCalendar convertDateToGreDate(Date date, String type) {
		String dataFormat = null;
		switch (type) {
		case "0":
			dataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(date);
			break;
		case "1":
			dataFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
			break;
		case "2":
			dataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000").format(date);
			break;
		case "3":
			dataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
			break;
		}

		XMLGregorianCalendar xgc = null;
		try {
			xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(dataFormat);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return xgc;
	}

	public Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	public String generateJsonFormat(String msg) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(msg);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	public String encrypt(String encData, String sec) throws Exception {

		byte[] skey = rekey(sec).getEncoded();

		byte[] data1 = Base64.getDecoder().decode(encData);
		SecretKeySpec secretkey = new SecretKeySpec(skey, "AES");
		c1.init(Cipher.ENCRYPT_MODE, secretkey);
		byte[] encryptedData = c1.doFinal(data1);
		return Base64.getEncoder().encodeToString(encryptedData);
	}

	public String decrypt(String encryptedData, String data) throws Exception {
		byte[] skey = rekey(data).getEncoded();
		byte[] dv = Base64.getDecoder().decode(encryptedData);
		SecretKeySpec secretkey = new SecretKeySpec(skey, "AES");
		c1.init(Cipher.DECRYPT_MODE, secretkey);
		byte[] decryptedData = c1.doFinal(dv);
		return Base64.getEncoder().encodeToString(decryptedData);
	}

	public KeyPair keypairGenerator() throws NoSuchAlgorithmException {

		SecureRandom random = new SecureRandom();
		KeyPairGenerator keypair = KeyPairGenerator.getInstance("RSA");
		keypair.initialize(2048, random);
		return keypair.generateKeyPair();

	}

	public Key rekey(String data) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		KeySpec spec = new PBEKeySpec(data.toCharArray(), new byte[16], 1024, 256);
		SecretKey skey = factory.generateSecret(spec);
		SecretKeySpec key = new SecretKeySpec(skey.getEncoded(), "AES");
		return key;
	}

	public String encryptDid(String data)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException,
			IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		PublicKey publicKey = KeyPairGeneratorFromJkS().getPublic();
		c.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encrypyedText = c.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encrypyedText);
	}

	public String decryptDid(String data)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException,
			IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] encdata = Base64.getDecoder().decode(data);
		Cipher c = Cipher.getInstance("RSA");
		c.init(Cipher.DECRYPT_MODE, KeyPairGeneratorFromJkS().getPrivate());
		return new String(c.doFinal(encdata));
	}

	public KeyPair KeyPairGeneratorFromJkS() throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, UnrecoverableEntryException {

		InputStream ins = null;
		try {
			String jksFilePath = env.getProperty("cimSSL.jks.file");
			String jksPassword = env.getProperty("cimSSL.jks.password");

			if (jksFilePath == null || jksPassword == null) {
				throw new IllegalArgumentException("JKS file path or password is not configured");
			}

			ins = new FileInputStream(jksFilePath);
			char[] pwdArray = jksPassword.toCharArray(); // JKS password

			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(ins, pwdArray);
			Enumeration<String> enumeration = keyStore.aliases();
			String alias = null;
			while (enumeration.hasMoreElements()) {
				alias = enumeration.nextElement();
				if (keyStore.isKeyEntry(alias)) {
					break;
				}
			}

			if (alias == null || alias.isEmpty()) {
				throw new KeyStoreException("No alias found in the keystore");
			}

			KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection(pwdArray);
			KeyStore.Entry entry = keyStore.getEntry(alias, keyPassword);

			if (entry == null || !(entry instanceof KeyStore.PrivateKeyEntry)) {
				throw new UnrecoverableEntryException("No private key entry found for alias: " + alias);
			}

			KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) entry;
			Certificate cert = keyStore.getCertificate(alias);

			if (cert == null) {
				throw new CertificateException("No certificate found for alias: " + alias);
			}

			PublicKey publicKey = cert.getPublicKey();
			PrivateKey privateKey = privateKeyEntry.getPrivateKey();

			if (publicKey == null || privateKey == null) {
				throw new KeyStoreException("Key pair is not valid for alias: " + alias);
			}

			return new KeyPair(publicKey, privateKey);
		} finally {
			if (ins != null) {
				ins.close();
			}
		}
	}

}
