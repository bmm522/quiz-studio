package quiz.model.category.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import quiz.model.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
