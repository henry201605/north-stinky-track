package com.nepa.wc.commons.utils;


public class MD5Encrypt {
	public static String encrypt(String value) {
		return byte2hex(byte2hex(value));
	}

	private static String byte2hex(String value) {
		try {
			java.security.MessageDigest alg = java.security.MessageDigest
					.getInstance("MD5");
			alg.update(value.getBytes());
			byte[] b = alg.digest();
			String hs = "";
			String stmp = "";
			for (int n = 0; n < b.length; n++) {
				stmp = (Integer.toHexString(b[n] & 0XFF));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
			}
			String temp = hs.toUpperCase();

			StringBuffer output = new StringBuffer(temp.substring(23, 26))
					.append(temp.substring(1, 3))
					.append(temp.substring(20, 21))
					.append(temp.substring(21, 23))
					.append(temp.substring(10, 11))
					.append(temp.substring(26, 30))
					.append(temp.substring(16, 20))
					.append(temp.substring(30, 32))
					.append(temp.substring(0, 1))
					.append(temp.substring(13, 16))
					.append(temp.substring(3, 6))
					.append(temp.substring(11, 13))
					.append(temp.substring(6, 10));
					
			return output.toString();
		} catch (java.security.NoSuchAlgorithmException ex) {
			return "";
		}
	}
}
