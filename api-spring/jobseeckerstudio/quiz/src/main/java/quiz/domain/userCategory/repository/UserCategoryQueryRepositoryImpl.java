package quiz.domain.userCategory.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import quiz.domain.category.Category;
import quiz.domain.category.QCategory;
import quiz.domain.userCategory.QUserCategory;
import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.dto.UserCategoryDto;
import quiz.domain.userCategory.repository.mapper.R_UserCategoryMapper;

public class UserCategoryQueryRepositoryImpl implements UserCategoryQueryRepository{

    private final JPAQueryFactory queryFactory;
    QUserCategory userCategory = QUserCategory.userCategory;
    QCategory category = QCategory.category;
    public UserCategoryQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory =new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<UserCategory> findUserCategoryByUserKeyAndTitle(String userKey, String title) {
        return Optional.ofNullable(queryFactory
            .selectFrom(userCategory)
            .join(category).on(userCategory.category.categoryId.eq(category.categoryId))
            .where(userCategory.userKey.eq(userKey))
            .where(category.categoryName.eq(title))
            .fetchOne());
    }

    @Override
    public List<UserCategoryDto> findUserCategoryDtosByUserKey(String userKey) {
        List<UserCategory> userCategories = queryFactory.selectFrom(userCategory)
            .join(userCategory.category, category)
            .where(userCategory.userKey.eq(userKey))
            .fetch();
        return R_UserCategoryMapper.toDto(userCategories);
    }

    @Override
    public Optional<UserCategory> findUserCategoryByUserCategoryId(Long userCategoryId) {
        return Optional.ofNullable(queryFactory.selectFrom(userCategory)
            .join(userCategory.category, category)
            .where(userCategory.userCategoryId.eq(userCategoryId))
            .fetchOne());
    }
}

