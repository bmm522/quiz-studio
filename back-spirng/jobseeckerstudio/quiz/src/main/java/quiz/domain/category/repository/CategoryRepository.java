package quiz.domain.category.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import quiz.domain.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
