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
import org.springframework.web.bind.annotation.ResponseBody;
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

	@PostMapping("/category")
	public @ResponseBody CommonResponse<?> saveCategory(
		@RequestAttribute("userKey") String userKey,
		@RequestBody CategorySaveBody request) {
		return ResponseHandler.handle(HttpStatus.CREATED.value(), "카테고리 저장 성공",
			categoryService.save(
				CategoryConverter.toSaveRequest(userKey, request)));
	}

	@GetMapping("/category")
	public CommonResponse<?> getCategories(
		@RequestAttribute("userKey") String userKey,
		@RequestParam("page") int page) {
		return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 불러오기 성공",
			categoryService.get(userKey, page));
	}

	@PatchMapping("/category")
	public CommonResponse<?> updateCategories(@RequestAttribute("userKey") String userKey,
		@RequestBody CategoryUpdateBody request) {
		return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 업데이트 성공",
			categoryService.update(
				CategoryConverter.toUpdateReqeust(userKey, request)));
	}

}
