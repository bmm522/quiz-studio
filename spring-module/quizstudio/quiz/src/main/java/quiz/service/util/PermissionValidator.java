package quiz.service.util;

import quiz.global.exception.PermissionException;

public class PermissionValidator {

	/**
	 * 사용자 키를 검증하여 권한이 있는지 확인하는 메서드입니다.
	 *
	 * @param userKey      현재 사용자의 키
	 * @param checkUserKey 검증할 사용자의 키
	 * @throws PermissionException 권한이 없는 경우 발생하는 예외
	 */
	public static void validatePermissionFromUserKey(final String userKey,
		final String checkUserKey) {
		if (!checkUserKey.equals(userKey)) {
			throw new PermissionException("권한이 없는 회원입니다. (userKey 일치하지 않음)");
		}
	}

}
