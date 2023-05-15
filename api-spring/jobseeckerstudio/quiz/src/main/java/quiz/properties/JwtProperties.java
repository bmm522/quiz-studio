package quiz.properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProperties {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token_prefix}")
    private String tokenPrefix;

    @Value("${jwt.refresh_prefix}")
    private String refreshPrefix;

    @Value("${jwt.header_jwt}")
    private String headerJwt;

    @Value("${jwt.header_refresh}")
    private String headerRefresh;

    @Value("${jwt.iss}")
    private String iss;


    public static String SECRET;
    public static String TOKEN_PREFIX;
    public static String REFRESH_PREFIX;
    public static String HEADER_JWT;
    public static String HEADER_REFRESH;
    public static String ISS;


    @PostConstruct
    public void init() {
        SECRET = secret;
        TOKEN_PREFIX = tokenPrefix;
        REFRESH_PREFIX = refreshPrefix;
        HEADER_JWT = headerJwt;
        HEADER_REFRESH = headerRefresh;
        ISS = iss;
    }
}

