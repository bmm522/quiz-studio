package quiz.controller.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quiz.controller.category.dto.CategorySaveBody;
import quiz.controller.category.dto.CategoryUpdateBody;
import quiz.controller.category.util.CategoryConverter;
import quiz.controller.dto.CommonResponse;
import quiz.controller.dto.ResponseHandler;
import quiz.service.category.CategoryService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	/**
	 * 카테고리 저장 메서드
	 *
	 * @param userKey 사용자 키
	 * @param request 카테고리 저장 요청 정보
	 * @return CommonResponse 객체
	 */
	@PostMapping("/category")
	public CommonResponse<?> saveCategory(
		@RequestAttribute("userKey") final String userKey,
		@RequestBody final CategorySaveBody request) {
		return ResponseHandler.handle(HttpStatus.CREATED.value(), "카테고리 저장 성공",
			categoryService.save(CategoryConverter.toSaveRequest(userKey, request)));
	}

	/**
	 * 카테고리 조회 메서드
	 *
	 * @param userKey 사용자 키
	 * @param page    페이지 번호
	 * @return CommonResponse 객체
	 */
	@GetMapping("/category")
	public CommonResponse<?> getCategories(
		@RequestAttribute("userKey") final String userKey,
		@RequestParam("page") final int page) {
		return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 불러오기 성공",
			categoryService.get(userKey, page));
	}

	/**
	 * 카테고리 옵션 조회 메서드
	 *
	 * @param userKey 사용자 키
	 * @return CommonResponse 객체
	 */
	@GetMapping("/category-option")
	public CommonResponse<?> getCategoriesWhenOption(
		@RequestAttribute("userKey") final String userKey
	) {
		return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 불러오기 성공(옵션)",
			categoryService.getCategoriesWhenSelectOption(userKey));
	}

	/**
	 * 카테고리 업데이트 메서드
	 *
	 * @param userKey 사용자 키
	 * @param request 카테고리 업데이트 요청 정보
	 * @return CommonResponse 객체
	 */
	@PatchMapping("/category")
	public CommonResponse<?> updateCategories(
		@RequestAttribute("userKey") final String userKey,
		@RequestBody final CategoryUpdateBody request) {
		return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 업데이트 성공",
			categoryService.update(CategoryConverter.toUpdateRequest(userKey, request)));
	}

}
