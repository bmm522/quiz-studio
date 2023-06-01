package com.jobseeckerstudio.user.encrypt;

import com.jobseeckerstudio.user.exception.EncryptionException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Encryptor extends Crypto {

	/**
	 * 문자열을 암호화하는 메서드입니다.
	 *
	 * @param str 암호화할 문자열
	 * @return 암호화된 문자열
	 * @throws EncryptionException 암호화 중에 발생한 예외
	 */
	public static String encrypt(final String str) {
		try {

			Cipher cipher = getCipher(ALGORITHM, Cipher.ENCRYPT_MODE);
			byte[] encryptedBytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
//            log.error(e.getMessage());
			throw new EncryptionException(e.getMessage());
		}

	}

}
