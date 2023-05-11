package quiz.service.category;

import org.springframework.stereotype.Service;

import quiz.service.category.dto.S_CategorySaveReqeust;
import quiz.service.category.dto.S_CategorySaveResponse;

@Service
public class CategoryService {

    public S_CategorySaveResponse save(S_CategorySaveReqeust toSaveRequest) {
        return S_CategorySaveResponse.builder().build();
    }
}
