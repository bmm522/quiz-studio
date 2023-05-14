package quiz.controller.category.mapper;

import quiz.controller.category.dto.C_CategorySaveRequest;
import quiz.exception.ExistCategorySaveException;
import quiz.exception.InvalidParameterFromDtoException;
import quiz.service.category.dto.S_CategorySaveReqeust;

public class C_CategoryMapper {

    public static S_CategorySaveReqeust toSaveRequest(String userKey, C_CategorySaveRequest request) {

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

        return S_CategorySaveReqeust.builder()
            .userKey(userKey)
            .title(request.getTitle())
            .description(request.getDescription())
            .build();
    }
}
