package com.jobseeckerstudio.user.encrypt;

import com.jobseeckerstudio.user.encrypt.properties.EncryptionProperties;
import com.jobseeckerstudio.user.exception.CryptoException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {


	protected static final String ALGORITHM = "AES";

	/**
	 * 암호화 및 복호화에 사용되는 Cipher 객체를 가져오는 메서드입니다.
	 *
	 * @param algorithm Cipher 알고리즘
	 * @param mode      Cipher 모드 (암호화 또는 복호화)
	 * @return Cipher 객체
	 * @throws CryptoException Cipher 객체를 가져오는 동안 발생한 예외
	 */
	protected static Cipher getCipher(final String algorithm, final int mode) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(EncryptionProperties.KEY.getBytes(),
				ALGORITHM);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(mode, secretKeySpec);
			return cipher;
		} catch (Exception e) {
			throw new CryptoException(e.getMessage());
		}
	}


}
