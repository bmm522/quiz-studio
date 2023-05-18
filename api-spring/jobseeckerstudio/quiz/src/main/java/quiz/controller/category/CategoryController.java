package quiz.controller.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quiz.controller.category.dto.C_CategorySaveRequest;
import quiz.controller.category.dto.C_CategoryUpdateRequest;
import quiz.controller.category.mapper.C_CategoryMapper;
import quiz.controller.dto.CommonResponse;
import quiz.controller.dto.ResponseHandler;
import quiz.service.usercategory.UserCategoryService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

	private final UserCategoryService userCategoryService;

	@PostMapping("/category")
	public CommonResponse<?> saveCategory(@RequestAttribute("userKey") String userKey,
		@RequestBody C_CategorySaveRequest request) {
		return ResponseHandler.handle(HttpStatus.CREATED.value(), "카테고리 저장 성공",
			userCategoryService.save(
				C_CategoryMapper.toSaveRequest(userKey, request)));
	}

	@GetMapping("/category")
	public CommonResponse<?> getCategories(@RequestAttribute("userKey") String userKey) {
		return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 불러오기 성공",
			userCategoryService.get(userKey));
	}

	@PatchMapping("/category")
	public CommonResponse<?> updateCategories(@RequestAttribute("userKey") String userKey,
		@RequestBody C_CategoryUpdateRequest reqeust) {
		return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 업데이트 성공",
			userCategoryService.update(
				C_CategoryMapper.toUpdateReqeust(userKey, reqeust)));
	}

}
