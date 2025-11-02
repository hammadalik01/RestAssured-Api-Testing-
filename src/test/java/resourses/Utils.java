package resourses;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {

    private static RequestSpecification reqspec;
    private static Properties prop;

    /**
     * Load configuration from .env file or fallback to global.properties
     */
    private static void loadProperties() throws Exception {
        if (prop == null) {
            prop = new Properties();
            File envFile = new File(".env");
            FileInputStream fis = null;

            try {
                if (envFile.exists()) {
                    fis = new FileInputStream(envFile);
                    prop.load(fis);
                } else {
                    fis = new FileInputStream("global.properties");
                    prop.load(fis);
                }
            } finally {
                if (fis != null) fis.close();
            }
        }
    }

    /**
     * Build reusable RequestSpecification
     */
    public static RequestSpecification requestSpecification() throws Exception {
        if (reqspec == null) {
            loadProperties();

            String baseUrl = prop.getProperty("BASE_URL", "https://api.trello.com/1");
            String apiKey = prop.getProperty("API_KEY");
            String apiToken = prop.getProperty("API_TOKEN");

            if (apiKey == null || apiToken == null) {
                throw new RuntimeException("API_KEY or API_TOKEN is not set in .env or global.properties!");
            }

            // Logging requests/responses
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

            reqspec = new RequestSpecBuilder()
                    .setBaseUri(baseUrl)
                    .addQueryParam("key", apiKey)
                    .addQueryParam("token", apiToken)
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
        }
        return reqspec;
    }

    /**
     * Reusable ResponseSpecification
     */
    public ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    /**
     * Extract value from JSON response by key
     */
    public String getJsonValue(String response, String key) {
        return new JsonPath(response).getString(key);
    }
}