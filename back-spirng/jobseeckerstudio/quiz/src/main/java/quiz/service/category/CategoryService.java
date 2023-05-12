package quiz.service.category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import quiz.domain.customCategory.CustomCategory;
import quiz.domain.customCategory.mapper.CustomCategoryMapper;
import quiz.domain.customCategory.repository.CustomCategoryRepository;
import quiz.domain.customCategory.repository.dto.CustomCategoryDto;
import quiz.exception.DuplicateTitleException;
import quiz.service.category.dto.S_CategoryGetResponse;
import quiz.service.category.dto.S_CategorySaveReqeust;
import quiz.service.category.dto.S_CategorySaveResponse;
import quiz.service.category.mapper.S_CategoryMapper;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CustomCategoryRepository customCategoryRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public S_CategorySaveResponse save(S_CategorySaveReqeust reqeust) {
        checkDuplicateTitle(reqeust.getUserKey(), reqeust.getTitle());
        CustomCategory category = CustomCategoryMapper.toEntityFromSaveRequest(reqeust);
        CustomCategory savedCategory = customCategoryRepository.save(category);
        return S_CategoryMapper.toSaveResponse(savedCategory);
    }

    public S_CategoryGetResponse get(String userKey) {
        List<CustomCategoryDto> customCategoryList = customCategoryRepository.findByUserKey(userKey)
            .stream()
            .map(CustomCategory::toDto)
            .collect(Collectors.toList());
        return S_CategoryMapper.toGetResponse(customCategoryList);
    }

    private void checkDuplicateTitle(String userKey, String title) {
        List<CustomCategory> entities = customCategoryRepository.findByUserKeyAndTitle(userKey, title);
        if(entities.size() > 0) {
            throw new DuplicateTitleException("중복된 카테고리 제목은 사용할 수 없습니다.");
        }
    }


}
