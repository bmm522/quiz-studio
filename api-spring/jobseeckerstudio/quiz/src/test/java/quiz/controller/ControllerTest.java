package quiz.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import quiz.controller.category.CategoryController;
import quiz.controller.quiz.QuizController;
import quiz.properties.JwtProperties;
import quiz.service.quiz.QuizService;
import quiz.service.usercategory.UserCategoryService;

@WebMvcTest({QuizController.class, CategoryController.class})
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	QuizService quizService;
	@MockBean
	UserCategoryService userCategoryService;

	ObjectMapper om;

	String jwt;
	HttpHeaders headers;

	protected void setUp() {
		setUpJwt();
		setUpHeader();
		om = new ObjectMapper();
	}

	protected void setUpJwt() {
		JwtProperties jwtProperties = new JwtProperties();
		jwtProperties.setSecret("asdfgffff112kdffj3532fgffffgsffsff");
		jwtProperties.setTokenPrefix("testTokenPrefix");
		jwtProperties.setHeaderJwt("testJwt");
		jwtProperties.setIss("testIss");
		jwtProperties.init();

		jwt = JwtProperties.TOKEN_PREFIX + JWT.create()
			.withSubject("testUser")
			.withIssuer(JwtProperties.ISS)
			.withExpiresAt(new Date(System.currentTimeMillis() + 10000000))
			.withClaim("userKey", "testUser")
			.sign(Algorithm.HMAC256(JwtProperties.SECRET));
	}

	protected void setUpHeader() {
		headers = new HttpHeaders();
		headers.set(JwtProperties.HEADER_JWT, jwt);
	}

	protected String decodeBody(ResultActions perform) throws Exception {
		String charset = "UTF-8";
		byte[] responseBytes = perform.andDo(print()).andReturn().getResponse()
			.getContentAsByteArray();
		return new String(responseBytes, charset);
	}

}
