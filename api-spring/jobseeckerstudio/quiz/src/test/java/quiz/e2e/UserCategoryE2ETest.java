package quiz.e2e;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.Date;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import quiz.controller.usercategory.dto.C_UserCategorySaveRequest;
import quiz.controller.usercategory.dto.C_UserCategoryUpdateRequest;
import quiz.domain.category.Category;
import quiz.domain.userCategory.UserCategory;
import quiz.properties.JwtProperties;


public class UserCategoryE2ETest extends E2ETest {


	private final String saveTestUser = "saveTestUser";
	private final String url = "/api/v1/category";


	@BeforeAll
	void init() {
		setUp();
		category = Category.builder()
			.categoryName(testCategoryName)
			.categoryDescription(testCategoryDescription)
			.build();

		userCategory = UserCategory.builder()
			.userKey(testUserKey)
			.category(category)
			.build();

		userCategoryRepository.save(userCategory);
	}

	@AfterAll
	void deleteData() {
		userCategoryRepository.deleteByUserKey(saveTestUser);
		userCategoryRepository.deleteByUserKey(testUserKey);
	}

	@Test
	@DisplayName("save 테스트")
	void saveTest() throws JsonProcessingException {

		String jwt2 = JwtProperties.TOKEN_PREFIX + JWT.create()
			.withSubject("testUser")
			.withIssuer(JwtProperties.ISS)
			.withExpiresAt(new Date(System.currentTimeMillis() + 10000000))
			.withClaim("userKey", saveTestUser)
			.sign(Algorithm.HMAC256(JwtProperties.SECRET));
		C_UserCategorySaveRequest dto = C_UserCategorySaveRequest.builder()
			.title("testTitle")
			.description("testDescription")
			.build();
		headers.remove(JwtProperties.HEADER_JWT);
		headers.set(JwtProperties.HEADER_JWT, jwt2);

		String body = om.writeValueAsString(dto);
		HttpEntity<String> request = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, request, String.class);

		DocumentContext dc = JsonPath.parse(response.getBody());

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String title = dc.read("$.data.title");
		String description = dc.read("$.data.description");
		String userKey = dc.read("$.data.userKey");

		assertThat(status).isEqualTo(201);
		assertThat(msg).isEqualTo("카테고리 저장 성공");
		assertThat(title).isEqualTo("testTitle");
		assertThat(description).isEqualTo("testDescription");
		assertThat(userKey).isEqualTo(saveTestUser);
	}

	@Test
	@DisplayName("get 테스트")
	void getTest() throws JsonProcessingException {

		headers.remove(JwtProperties.HEADER_JWT);
		headers.set(JwtProperties.HEADER_JWT, jwt);
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, request, String.class);
		DocumentContext dc = JsonPath.parse(response.getBody());

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String title = dc.read("$.data.categories[0].title");
		String description = dc.read("$.data.categories[0].description");
		String userKey = dc.read("$.data.categories[0].userKey");

		assertThat(status).isEqualTo(200);
		assertThat(msg).isEqualTo("카테고리 불러오기 성공");
		assertThat(userKey).isEqualTo(testUserKey);
		assertThat(title).isEqualTo("testCategoryName");
		assertThat(description).isEqualTo("testCategoryDescription");

	}

	@Test
	@DisplayName("update테스트")
	void updateTest() throws JsonProcessingException {
		Category category3 = Category.builder()
			.categoryName("updateBeforeTitle")
			.categoryDescription("updateBeforeDescription")
			.build();

		UserCategory userCategory3 = UserCategory.builder()
			.userKey(testUserKey)
			.category(category3)
			.build();

		long savedUserCategoryId = userCategoryRepository.save(userCategory3).getUserCategoryId();

		rt.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		C_UserCategoryUpdateRequest dto = C_UserCategoryUpdateRequest.builder()
			.userCategoryId(savedUserCategoryId)
			.updateTitle("updateAfterTitle")
			.updateDescription("updateAfterDescription")
			.build();
		headers.remove(JwtProperties.HEADER_JWT);
		headers.set(JwtProperties.HEADER_JWT, jwt);

		String body = om.writeValueAsString(dto);
		HttpEntity<String> request = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = rt.exchange(url, HttpMethod.PATCH, request, String.class);
		DocumentContext dc = JsonPath.parse(response.getBody());

		int status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String title = dc.read("$.data.updateTitle");
		String description = dc.read("$.data.updateDescription");
		String userKey = dc.read("$.data.userKey");

		assertThat(status).isEqualTo(200);
		assertThat(msg).isEqualTo("카테고리 업데이트 성공");
		assertThat(userKey).isEqualTo(testUserKey);
		assertThat(title).isEqualTo("updateAfterTitle");
		assertThat(description).isEqualTo("updateAfterDescription");

	}


}
