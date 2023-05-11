package quiz.service.category;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import quiz.domain.customCategory.CustomCategory;
import quiz.domain.customCategory.mapper.CustomCategoryMapper;
import quiz.domain.customCategory.repository.CustomCategoryRepository;
import quiz.service.category.dto.S_CategorySaveReqeust;
import quiz.service.category.dto.S_CategorySaveResponse;
import quiz.service.category.mapper.S_CategoryMapper;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CustomCategoryRepository customCategoryRepository;

    public S_CategorySaveResponse save(S_CategorySaveReqeust reqeust) {
        CustomCategory category = CustomCategoryMapper.toEntityFromSaveRequest(reqeust);
        return S_CategoryMapper.toSaveResponse(customCategoryRepository.save(category));
    }
}
