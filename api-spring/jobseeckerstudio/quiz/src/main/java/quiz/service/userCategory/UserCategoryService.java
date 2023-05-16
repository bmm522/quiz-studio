package quiz.service.userCategory;

import java.util.List;
import java.util.Optional;

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
import quiz.exception.NotFoundEntityException;
import quiz.exception.PermissionException;
import quiz.service.userCategory.dto.S_UserCategoryGetResponse;
import quiz.service.userCategory.dto.S_UserCategorySaveRequest;
import quiz.service.userCategory.dto.S_UserCategorySaveResponse;
import quiz.service.userCategory.dto.S_UserCategoryUpdateRequest;
import quiz.service.userCategory.dto.S_UserCategoryUpdateResponse;
import quiz.service.userCategory.mapper.S_UserCategoryMapper;

@Service
@RequiredArgsConstructor
public class UserCategoryService {

    private final UserCategoryRepository userCategoryRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public S_UserCategorySaveResponse save(S_UserCategorySaveRequest request) {
        checkDuplicateTitle(request.getUserKey(), request.getTitle());
        Category category = CategoryMapper.toEntityWhenSave(request);
        UserCategory relation = UserCategoryMapper.toEntityWhenSave(request);
        relation.addCategory(category);
        UserCategory savedData = userCategoryRepository.save(relation);
        return S_UserCategoryMapper.toSaveResponse(savedData);

    }

    @Transactional(readOnly = true)
    public S_UserCategoryGetResponse get(String userKey) {
        List<UserCategoryDto> userCategoryDtos = userCategoryRepository.findUserCategoryDtosByUserKey(userKey);
        return S_UserCategoryMapper.toGetResponse(userCategoryDtos);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public S_UserCategoryUpdateResponse update(S_UserCategoryUpdateRequest request) {
        UserCategory userCategory = getUserCategory(request);
        checkPermission(userCategory.getUserKey(), request.getUserKey());

        Category category = userCategory.getCategory();
        category.updateCategoryName(request.getUpdateTitle());
        category.updateCategoryDescription(request.getUpdateDescription());

        return S_UserCategoryMapper.toUpdateResponse(userCategory);
    }

    private UserCategory getUserCategory(S_UserCategoryUpdateRequest request) {
        Optional<UserCategory> userCategoryOptional = userCategoryRepository.findUserCategoryByUserCategoryId(request.getUserCategoryId());
        if(userCategoryOptional.isPresent()) {
            return userCategoryOptional.get();
        }
        throw new NotFoundEntityException("userKey로 해당 UserCategory 객체를 찾을 수 없습니다.");
    }

    private void checkPermission(String savedUserKey, String requestUserKey) {
        if(!savedUserKey.equals(requestUserKey)){
            throw new PermissionException("권한이 없는 회원입니다. (userKey 일치하지 않음)");
        }
    }

    private void checkDuplicateTitle(String userKey, String title) {
        Optional<UserCategory> userCategoryOptional = userCategoryRepository.findUserCategoryByUserKeyAndTitle(userKey, title);
        if(userCategoryOptional.isPresent()) {
            throw new DuplicateTitleException("중복된 카테고리 제목은 사용할 수 없습니다.");
        }
    }


}
