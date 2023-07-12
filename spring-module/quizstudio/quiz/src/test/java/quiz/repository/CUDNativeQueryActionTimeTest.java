package quiz.repository;

import java.util.Map;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("prod")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CUDNativeQueryActionTimeTest {


	@Autowired
	TestEntityManager testEntityManager;

	EntityManager entityManager;
	Map<Long, String> updateMap;


}
