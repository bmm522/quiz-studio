package quiz.e2e;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import quiz.controller.userCategory.dto.C_UserCategorySaveRequest;
import quiz.domain.category.Category;
import quiz.domain.userCategory.UserCategory;
import quiz.domain.userCategory.repository.UserCategoryRepository;
import quiz.properties.JwtProperties;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserCategoryE2ETest {

    @Autowired
    private TestRestTemplate rt;

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    private UserCategory userCategory;

    private ObjectMapper om;

    private Category category;

    private HttpHeaders headers;

    private final String testUserKey = "testUserKey";
    private final String testCategoryName = "testCategoryName";

    private final String testCategoryDescription = "testCategoryDescription";


    private final String saveTestUser = "saveTestUser";
    private final String url = "/api/v1/category";
    private String jwt;


    @BeforeAll
    void init() {
        category = Category.builder()
            .categoryName(testCategoryName)
            .categoryDescription(testCategoryDescription)
            .build();

        userCategory = UserCategory.builder()
            .userKey(testUserKey)
            .category(category)
            .build();

        userCategoryRepository.save(userCategory);

        jwt = JwtProperties.TOKEN_PREFIX + JWT.create()
            .withSubject("testUser")
            .withIssuer(JwtProperties.ISS)
            .withExpiresAt(new Date(System.currentTimeMillis() + 10000000))
            .withClaim("userKey", testUserKey)
            .sign(Algorithm.HMAC256(JwtProperties.SECRET));
        headers = new HttpHeaders();

        headers.set(JwtProperties.HEADER_JWT, jwt);
        headers.setContentType(MediaType.APPLICATION_JSON);


        om = new ObjectMapper();
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



}
