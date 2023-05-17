package quiz.controller.usercategory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quiz.controller.dto.CommonResponse;
import quiz.controller.dto.ResponseHandler;
import quiz.controller.usercategory.dto.C_UserCategorySaveRequest;
import quiz.controller.usercategory.dto.C_UserCategoryUpdateRequest;
import quiz.controller.usercategory.mapper.C_UserCategoryMapper;
import quiz.service.usercategory.UserCategoryService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserCategoryController {

	private final UserCategoryService userCategoryService;

	@PostMapping("/category")
	public CommonResponse<?> saveCategory(@RequestAttribute("userKey") String userKey,
		@RequestBody C_UserCategorySaveRequest request) {
		return ResponseHandler.handle(HttpStatus.CREATED.value(), "카테고리 저장 성공",
			userCategoryService.save(
				C_UserCategoryMapper.toSaveRequest(userKey, request)));
	}

	@GetMapping("/category")
	public CommonResponse<?> getCategories(@RequestAttribute("userKey") String userKey) {
		return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 불러오기 성공",
			userCategoryService.get(userKey));
	}

	@PatchMapping("/category")
	public CommonResponse<?> updateCategories(@RequestAttribute("userKey") String userKey,
		@RequestBody C_UserCategoryUpdateRequest reqeust) {
		return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 업데이트 성공",
			userCategoryService.update(
				C_UserCategoryMapper.toUpdateReqeust(userKey, reqeust)));
	}

}
