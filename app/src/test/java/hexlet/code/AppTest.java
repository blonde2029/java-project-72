package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.ChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    private static Javalin app;

    @BeforeEach
    public final void beforeEach() throws SQLException, IOException {
        app = App.getApp();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.rootPath());
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
            assertThat(UrlsRepository.exists("https://www.ya.ru")).isTrue();
            response.close();
        }));
    }

    @Test
    public void testUrlPage() throws SQLException {
        JavalinTest.test(app, ((server, client) -> {
            var requestBody = "url=https://www.ya.ru";
            client.post(NamedRoutes.urlsPath(), requestBody);
            var response = client.get(NamedRoutes.urlPath("1"));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://www.ya.ru");
            assertThat(UrlsRepository.exists("https://www.ya.ru")).isTrue();
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
            assertThat(UrlsRepository.exists("https://test2")).isTrue();
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
    public void mockTest() throws IOException {
        var body = Files.readString(Paths.get("src/test/resources/test.html")).trim();
        try (MockWebServer mockServer = new MockWebServer()) {
            MockResponse mockedResponse = new MockResponse()
                    .setResponseCode(200)
                    .setBody(body);
            mockServer.enqueue(mockedResponse);
            mockServer.start();
            String testUrl = mockServer.url("/").toString();

            JavalinTest.test(app, (server, client) -> {
                var actualUrl = new Url(testUrl);
                UrlsRepository.save(actualUrl);
                var idString = actualUrl.getId().toString();
                client.post(NamedRoutes.checksPath(idString));
                var checkUrl = ChecksRepository.getEntitiesById(actualUrl.getId()).get(0);
                assertThat(checkUrl.getTitle()).isEqualTo("TestTitle");
                assertThat(checkUrl.getH1()).isEqualTo("TestH1");
                assertThat(checkUrl.getDescription()).isEqualTo("TestDescription");
            });
        }
    }

}
