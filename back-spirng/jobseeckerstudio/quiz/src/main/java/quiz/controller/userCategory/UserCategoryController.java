package quiz.controller.userCategory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import quiz.controller.dto.CommonResponse;
import quiz.controller.userCategory.dto.C_UserCategorySaveRequest;
import quiz.controller.userCategory.mapper.C_UserCategoryMapper;

import quiz.controller.dto.ResponseHandler;
import quiz.service.userCategory.UserCategoryService;
import quiz.service.userCategory.dto.S_UserCategoryGetResponse;
import quiz.service.userCategory.dto.S_UserCategorySaveResponse;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserCategoryController {

    private final UserCategoryService userCategoryService;

    @PostMapping("/category")
    public CommonResponse<?> saveCategory(@RequestAttribute("userKey")String userKey, @RequestBody C_UserCategorySaveRequest request) {
        S_UserCategorySaveResponse response = userCategoryService.save(
            C_UserCategoryMapper.toSaveRequest(userKey, request));
        return ResponseHandler.handle(HttpStatus.CREATED.value(), "카테고리 저장 성공", response);
    }

    @GetMapping("/category")
    public CommonResponse<?> getCategories(@RequestAttribute("userKey")String userKey){
        S_UserCategoryGetResponse response = userCategoryService.get(userKey);
        return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 불러오기 성공", response);
    }



}
