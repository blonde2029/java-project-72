package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import static hexlet.code.App.readResourceFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
//    Javalin app;
    private static Javalin app;
    private static String baseUrl;
    private static MockWebServer mockServer;
    private static int port;

//    @BeforeAll
//    public static void beforeAll() throws IOException {
//        MockWebServer  mockServer = new MockWebServer();
//        var baseUrl = mockServer.url("/").toString();
//        var port = mockServer.getPort();
//        var mockResponse = new MockResponse().setBody(readResourceFile("index.html"));
//        mockServer.enqueue(mockResponse);
//        mockServer.start();
//    }

    @BeforeEach
    public final void beforeEach() throws SQLException, IOException {
        app = App.getApp();
    }

//    @AfterAll
//    public static void afterAll() throws IOException {
//        mockServer.shutdown();
//    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
            response.close();
        });
    }

    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, ((server, client) -> {
            var requestBody = "url=https://www.ya.ru";
            client.post(NamedRoutes.urlsPath(), requestBody);
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
            var responseBody = response.body().string();
            assertThat(responseBody.contains("https://www.ya.ru"));
            response.close();
        }));
    }

    @Test
    public void testUrlPage() throws SQLException {
        JavalinTest.test(app, ((server, client) -> {
            var requestBody = "url=https://www.ya.ru";
            client.post(NamedRoutes.urlsPath(), requestBody);

            var response = client.get("/urls/1");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://www.ya.ru");
            response.close();
        }));
    }

    @Test
    public void testCreateUrl() {
        JavalinTest.test(app, ((server, client) -> {
            var requestBody = "url=https://test2";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://test2");
        }));
    }
    @Test
    public void testNotFound() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls/99999");
            assertThat(response.code()).isEqualTo(404);
            response.close();
        }));
    }

    @Test
    public void testCheckUrl() {
        JavalinTest.test(app, ((server, client) -> {
            var requestBody = "url=https://www.rbc.ru";
            client.post(NamedRoutes.urlsPath(), requestBody);

            var response = client.post("/urls/1/checks");
            assertThat(response.code()).isEqualTo(200);
            response = client.get("/urls/1");
            var bodyString = response.body().string();
            assertThat(bodyString).contains("свежие новости на РБК");
            assertThat(bodyString).contains("РосБизнесКонсалтинг");
        }));
    }

}
