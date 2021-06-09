package com.finalProject.security;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Component;

@Component
public class PBKDF2Algorithm {

	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final String SALT = "fd79feaff86576d3a0fb4393d71c24bb";
	public static final int SALT_BYTE_SIZE = 32 / 2; // length salt
	public static final int HASH_BIT_SIZE = 128 * 4; // size of the ciphertext generating
	public static final int PBKDF2_ITERATIONS = 10000; // number of iterations

	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;
	}

	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0)
			return String.format("%0" + paddingLength + "d", 0) + hex;
		else
			return hex;
	}

	public boolean authenticate(String attemptedPassword, String encryptedPassword, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// encrypted password entered by the user the same salt
		String encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
		// The encrypted ciphertext and the original ciphertext comparison, the same
		// authentication is successful, otherwise fail
		return encryptedAttemptedPassword.equals(encryptedPassword);
	}

	public String generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);
		return toHex(salt);
	}

	public String getEncryptedPassword(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), fromHex(salt), PBKDF2_ITERATIONS, HASH_BIT_SIZE);
		SecretKeyFactory f = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return toHex(f.generateSecret(spec).getEncoded());
	}

}
