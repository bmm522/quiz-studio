package quiz.domain.userCategory.repository;

import java.util.List;

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


public class UserCategoryQueryRepositoryImpl implements UserCategoryQueryRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory;
    QUserCategory userCategory = QUserCategory.userCategory;
    QCategory category = QCategory.category;
    public UserCategoryQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory =new JPAQueryFactory(entityManager);
    }

    @Override
    public List<UserCategory> findByUserKeyAndTitle(String userKey, String title) {

        return queryFactory
             .selectFrom(userCategory)
             .join(category)
             .where(userCategory.userKey.eq(userKey)
                 .and(category.categoryName.eq(title)))
             .fetch();
    }
}


