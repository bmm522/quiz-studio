package quiz.service.userCategory;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import quiz.domain.category.Category;
import quiz.domain.category.mapper.CategoryMapper;
import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.mapper.UserCategoryMapper;
import quiz.domain.userCategory.repository.UserCategoryRepository;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;
import quiz.exception.DuplicateTitleException;
import quiz.service.userCategory.dto.S_UserCategoryGetResponse;
import quiz.service.userCategory.dto.S_UserCategorySaveReqeust;
import quiz.service.userCategory.dto.S_UserCategorySaveResponse;
import quiz.service.userCategory.mapper.S_UserCategoryMapper;

@Service
@RequiredArgsConstructor
public class UserCategoryService {

    private final UserCategoryRepository userCategoryRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public S_UserCategorySaveResponse save(S_UserCategorySaveReqeust reqeust) {
        checkDuplicateTitle(reqeust.getUserKey(), reqeust.getTitle());
        Category category = CategoryMapper.toEntityWhenSave(reqeust);
        UserCategory relation = UserCategoryMapper.toEntityWhenSave(reqeust);
        relation.addCategory(category);
        UserCategory savedData = userCategoryRepository.save(relation);
        return S_UserCategoryMapper.toSaveResponse(savedData);

    }

    public S_UserCategoryGetResponse get(String userKey) {
        List<UserCategoryDto> userCategoryDtos = userCategoryRepository.findByUserKey(userKey);
        return S_UserCategoryMapper.toGetResponse(userCategoryDtos);
    }

    private void checkDuplicateTitle(String userKey, String title) {
        List<UserCategory> entities = userCategoryRepository.findByUserKeyAndTitle(userKey, title);
        if(entities.size() > 0) {
            throw new DuplicateTitleException("중복된 카테고리 제목은 사용할 수 없습니다.");
        }
    }


}
