package quiz.e2e;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import quiz.domain.category.Category;
import quiz.domain.category.repository.CategoryRepository;
import quiz.properties.JwtProperties;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class E2ETest {

	@Autowired
	protected TestRestTemplate rt;

	@Autowired
	protected CategoryRepository categoryRepository;


	protected final String testUserKey = "testUserKey";
	protected final String testCategoryName = "testCategoryName";

	protected final String testCategoryDescription = "testCategoryDescription";

	protected Category category;

	protected ObjectMapper om;

	protected HttpHeaders headers;

	protected String jwt;

	protected void setUp() {
		setUpJwt();
		setUpHeader();
		om = new ObjectMapper();
	}

	protected void setUpHeader() {
		headers = new HttpHeaders();

		headers.set(JwtProperties.HEADER_JWT, jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	private void setUpJwt() {
		jwt = JwtProperties.TOKEN_PREFIX + JWT.create()
			.withSubject("testUser")
			.withIssuer(JwtProperties.ISS)
			.withExpiresAt(new Date(System.currentTimeMillis() + 10000000))
			.withClaim("userKey", testUserKey)
			.sign(Algorithm.HMAC256(JwtProperties.SECRET));
	}
}
