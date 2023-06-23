package quiz.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import quiz.controller.category.mapper.ControllerCategoryMapper;
import quiz.global.exception.ExistCategorySaveException;

public class ControllerCategoryMapperTest {


	@Test
	void 자바_카테고리_체크() {

		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			ControllerCategoryMapper.checkExistCategoryName("java");
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	void 자료구조_카테고리_체크() {

		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			ControllerCategoryMapper.checkExistCategoryName("datastructure");
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	void 데이터베이스_카테고리_체크() {

		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			ControllerCategoryMapper.checkExistCategoryName("database");
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	void 스프링_카테고리_체크() {

		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			ControllerCategoryMapper.checkExistCategoryName("spring");
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	void 네트워크_카테고리_체크() {

		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			ControllerCategoryMapper.checkExistCategoryName("network");
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}

	@Test
	void 인터뷰_카테고리_체크() {

		Exception exception = assertThrows(ExistCategorySaveException.class, () -> {
			ControllerCategoryMapper.checkExistCategoryName("interview");
		});

		assertThat(exception.getMessage()).isEqualTo("기존의 카테고리 이름은 사용할 수 없습니다.");
	}
}
