package quiz.controller.userCategory.mapper;

import quiz.controller.userCategory.dto.C_UserCategorySaveRequest;
import quiz.exception.ExistCategorySaveException;
import quiz.exception.InvalidParameterFromDtoException;
import quiz.service.userCategory.dto.S_UserCategorySaveReqeust;

public class C_UserCategoryMapper {

    public static S_UserCategorySaveReqeust toSaveRequest(String userKey, C_UserCategorySaveRequest request) {

        if(request.getTitle() == null || request.getTitle().isEmpty()) {
            throw new InvalidParameterFromDtoException("save 요청에 title이 담기지 않았습니다.");
        }

        if(request.getDescription() == null || request.getDescription().isEmpty()) {
            throw new InvalidParameterFromDtoException("save 요청에 description이 담기지 않았습니다.");
        }
        String checkStr = request.getTitle().toLowerCase().trim().replace(" ","");

        if("java".equals(checkStr) || "datastructure".equals(checkStr)){
            throw new ExistCategorySaveException("기존의 카테고리 이름은 사용할 수 없습니다.");
        }

        return S_UserCategorySaveReqeust.builder()
            .userKey(userKey)
            .title(request.getTitle())
            .description(request.getDescription())
            .build();
    }
}
