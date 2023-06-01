package quiz.domain.category.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import quiz.domain.category.Category;
import quiz.repository.category.CategoryQueryRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryQueryRepository {

	@Transactional
	void deleteByUserKey(String testUserKey);
}
