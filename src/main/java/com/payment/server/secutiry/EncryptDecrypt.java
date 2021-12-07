package com.payment.server.secutiry;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecrypt {

	private static final String SECRETKEY = "Workshop#license";
	 
	private static SecretKeySpec secretKey;
	private static byte[] key;

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/************ here we are performing encryption ************/
	private static String encrypt(String stringToEncrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error arised while encrypting: " + e.toString());
		}
		return null;
	}
	
	public static String encrypt(String stringToEncrypt) {
		return encrypt(stringToEncrypt, SECRETKEY);
	}

	/************ here we are performing decryption ************/
	private static String decrypt(String stringToDecrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error arised while decrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String stringToDecrypt) {
		return decrypt(stringToDecrypt, SECRETKEY);
	}
	
	public static void main(String[] args) {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoia2V0YW4uZ290ZSIsImlhdCI6MTYyOTI3MDk2NywiZXhwIjoxNjI5Mjk5NzY3fQ.IETDRV-z3fbe3VBWUZlhryJA-5vmZfMa8DhMDjJD9iY";
		System.out.println(encrypt(token));
		System.out.println(decrypt("0H9zBcpQDtEWWUaggd7NpfgfH+BYmBnm+tw/4AB9CuAGuE5s4k/U7jPVz5EBYSWkF3j19MxkhjZCN7Prg2TiaV4wip1NJzHK7GEHE+LwN3M9dyzPrsL4wRET/G7DtehPKdblAsAxcMKJP9fOH260eYaG3XdyujSQGhd2kpe4ZNr447t/p1bwuSUg//oiDYhv61v5UwSfTu+5kpZmqc4HPrDl7ft15Olw7E4ygM4sI20="));
	}
}
