package quiz.controller.userCategory.mapper;

import quiz.controller.userCategory.dto.C_UserCategorySaveRequest;
import quiz.controller.userCategory.dto.C_UserCategoryUpdateRequest;
import quiz.exception.ExistCategorySaveException;
import quiz.exception.InvalidParameterFromDtoException;
import quiz.service.userCategory.dto.S_UserCategorySaveRequest;
import quiz.service.userCategory.dto.S_UserCategoryUpdateRequest;
import quiz.service.userCategory.dto.S_UserCategoryUpdateResponse;

public class C_UserCategoryMapper {

    public static S_UserCategorySaveRequest toSaveRequest(String userKey, C_UserCategorySaveRequest request) {
        validateTitleAndDescription(request.getTitle(), request.getDescription());
        checkExistCategoryName(request.getTitle());
        return S_UserCategorySaveRequest.builder()
            .userKey(userKey)
            .title(request.getTitle())
            .description(request.getDescription())
            .build();
    }

    public static S_UserCategoryUpdateRequest toUpdateReqeust(String userKey, C_UserCategoryUpdateRequest request) {
        checkExistCategoryName(request.getUpdateTitle());
        return S_UserCategoryUpdateRequest.builder()
            .userCategoryId(request.getUserCategoryId())
            .userKey(userKey)
            .updateTitle(request.getUpdateTitle())
            .updateDescription(request.getUpdateDescription())
            .build();
    }

    private static void validateTitleAndDescription(String title, String description) {
        if (title == null || title.isEmpty()) {
            throw new InvalidParameterFromDtoException("save 요청에 title이 담기지 않았습니다.");
        }

        if (description == null || description.isEmpty()) {
            throw new InvalidParameterFromDtoException("save 요청에 description이 담기지 않았습니다.");
        }

    }

    private static void checkExistCategoryName(String title) {
        String checkStr = title.toLowerCase().trim().replace(" ", "");

        if ("java".equals(checkStr) || "datastructure".equals(checkStr)) {
            throw new ExistCategorySaveException("기존의 카테고리 이름은 사용할 수 없습니다.");
        }
    }

}
