package application.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA256 {

	public static String getEncrypt(String source, String salt) {
		return getEncrypt(source, salt.getBytes());
	}
	
	public static String getEncrypt(String source, byte[] salt) {

		String result = "";

		byte[] a = source.getBytes();
		byte[] bytes = new byte[a.length + salt.length];

		// arraycopy(Object 원본배열, int 원본배열에서 copy의 시작위치, Object 타겟배열, int 타겟배열에서 copy시작
		// 위치, int copy할길이);
		System.arraycopy(a, 0, bytes, 0, a.length);
		System.arraycopy(salt, 0, bytes, a.length, salt.length);

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytes);

			byte[] byteData = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(String.format("%02x", byteData[i]));
			}
			result = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String generateSalt() {

		String value = "";

		try {
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			secureRandom.nextBytes(salt);
			value = salt.toString();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < salt.length; i++) {
				sb.append(String.format("%02x", salt[i]));
			}
			value = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return value;
	}
}
