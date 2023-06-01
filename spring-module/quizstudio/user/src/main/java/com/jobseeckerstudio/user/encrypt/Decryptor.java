package com.jobseeckerstudio.user.encrypt;

import com.jobseeckerstudio.user.exception.DecryptionException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;

public class Decryptor extends Crypto {

	/**
	 * 암호화된 문자열을 복호화하는 메서드입니다.
	 *
	 * @param encryptedStr 암호화된 문자열
	 * @return 복호화된 문자열
	 * @throws DecryptionException 복호화 중에 발생한 예외
	 */
	public static String decrypt(final String encryptedStr) {
		try {
			byte[] encryptedBytes = Base64.getDecoder()
				.decode(encryptedStr.getBytes(StandardCharsets.UTF_8));
			Cipher cipher = getCipher(ALGORITHM, Cipher.DECRYPT_MODE);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			String str = new String(decryptedBytes, StandardCharsets.UTF_8);
			return str;
		} catch (Exception e) {
			throw new DecryptionException(e.getMessage());
		}

	}
}
