package com.company.movie.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

import com.company.movie.exception.AppException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncryptionUtil {

	private static final String ALGORITHM = "PBEWithMD5AndDES";
	private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33,
			(byte) 0x10, (byte) 0x12, };

	public static String encrypt(String data, String salt) {
		return encrypt(data, salt, false);
	}

	public static String decrypt(String data, String salt) {
		return decrypt(data, salt, false);
	}

	private static String encrypt(String data, String salt, boolean isUrlSafe) {

		try {

			System.out.println("-->encrypt");
			// log.trace("-->encrypt");
			Cipher pbeCipher = Cipher.getInstance(ALGORITHM);
			pbeCipher.init(Cipher.ENCRYPT_MODE, getKey(salt), new PBEParameterSpec(SALT, 20));

			String encryptedData = base64Encode(pbeCipher.doFinal(data.getBytes("ISO-8859-1")), isUrlSafe);
			System.out.println("<--encrypt");
			// log.trace("<--encrypt");
			return encryptedData;
		} catch (Exception e) {
			throw new AppException("");
		}
	}

	private static String decrypt(String encryptedData, String salt, boolean isUrlSafe) {

		try {
			System.out.println("\"-->decrypt\"");
			// log.trace("-->decrypt");
			Cipher pbeCipher = Cipher.getInstance(ALGORITHM);
			pbeCipher.init(Cipher.DECRYPT_MODE, getKey(salt), new PBEParameterSpec(SALT, 20));

			String data = new String(pbeCipher.doFinal(base64Decode(encryptedData, isUrlSafe)), "ISO-8859-1");
			System.out.println("<--decrypt");
			// log.trace("<--decrypt");
			return data;
		} catch (Exception e) {
			throw new AppException("");
		}
	}

	private static SecretKey getKey(String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(salt.toCharArray()));
		return key;
	}

	private static String base64Encode(byte[] binaryData, boolean isUrlSafe) {
		return new Base64(isUrlSafe).encodeToString(binaryData);
	}

	private static byte[] base64Decode(String base64String, boolean isUrlSafe) throws IOException {
		return new Base64(isUrlSafe).decode(base64String);
	}

}
