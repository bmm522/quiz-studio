package quiz.service.util;

import quiz.global.exception.PermissionException;

public class PermissionValidator {

	public static void validatePermissionFromUserKey(final String userKey,
		final String checkUserKey) {
		if (!checkUserKey.equals(userKey)) {
			throw new PermissionException("권한이 없는 회원입니다. (userKey 일치하지 않음)");
		}
	}

}
