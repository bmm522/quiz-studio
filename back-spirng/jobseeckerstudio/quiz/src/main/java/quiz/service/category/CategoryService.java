package quiz.service.category;

import java.util.List;
import java.util.stream.Collectors;

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
import quiz.service.category.dto.S_CategoryGetResponse;
import quiz.service.category.dto.S_CategorySaveReqeust;
import quiz.service.category.dto.S_CategorySaveResponse;
import quiz.service.category.mapper.S_CategoryMapper;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final UserCategoryRepository userCategoryRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public S_CategorySaveResponse save(S_CategorySaveReqeust reqeust) {
        checkDuplicateTitle(reqeust.getUserKey(), reqeust.getTitle());
        Category category = CategoryMapper.toEntityWhenSave(reqeust);
        UserCategory relation = UserCategoryMapper.toEntityWhenSave(reqeust);
        relation.addCategory(category);
        UserCategory savedData = userCategoryRepository.save(relation);
        return S_CategoryMapper.toSaveResponse(savedData);

    }

    public S_CategoryGetResponse get(String userKey) {
        // List<UserCategoryDto> customCategoryList = userCategoryRepository.findByUserKey(userKey)
        //     .stream()
        //     .map(UserCategory::toDto)
        //     .collect(Collectors.toList());
        // return S_CategoryMapper.toGetResponse(customCategoryList);
        return null;
    }

    private void checkDuplicateTitle(String userKey, String title) {
        List<UserCategory> entities = userCategoryRepository.findByUserKeyAndTitle(userKey, title);
        if(entities.size() > 0) {
            throw new DuplicateTitleException("중복된 카테고리 제목은 사용할 수 없습니다.");
        }
    }


}
