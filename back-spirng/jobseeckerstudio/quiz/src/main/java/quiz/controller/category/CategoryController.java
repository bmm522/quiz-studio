package quiz.controller.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import quiz.controller.dto.CommonResponse;
import quiz.controller.category.dto.C_CategorySaveRequest;
import quiz.controller.category.mapper.C_CategoryMapper;

import quiz.controller.dto.ResponseHandler;
import quiz.service.category.CategoryService;
import quiz.service.category.dto.S_CategoryGetResponse;
import quiz.service.category.dto.S_CategorySaveResponse;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public CommonResponse<?> saveCategory(@RequestAttribute("userKey")String userKey, @RequestBody C_CategorySaveRequest request) {
        S_CategorySaveResponse response = categoryService.save(C_CategoryMapper.toSaveRequest(userKey, request));
        return ResponseHandler.handle(HttpStatus.CREATED.value(), "카테고리 저장 성공", response);
    }

    @GetMapping("/category")
    public CommonResponse<?> getCategories(@RequestAttribute("userKey")String userKey){
        S_CategoryGetResponse response = categoryService.get(userKey);
        return ResponseHandler.handle(HttpStatus.OK.value(), "카테고리 불러오기 성공", response);
    }



}
