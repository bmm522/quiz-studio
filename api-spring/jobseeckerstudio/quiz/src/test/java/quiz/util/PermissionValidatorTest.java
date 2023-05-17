package quiz.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quiz.global.exception.PermissionException;
import quiz.service.util.PermissionValidator;

public class PermissionValidatorTest {

	@Test
	@DisplayName("userKey 일치하지 않을때")
	void validatePermissionTest() {
		Exception exception = assertThrows(PermissionException.class, () -> {
			PermissionValidator.validatePermissionFromUserKey("testUser", "testUserUser");
		});
		assertThat(exception.getMessage()).isEqualTo("권한이 없는 회원입니다. (userKey 일치하지 않음)");
	}
}
