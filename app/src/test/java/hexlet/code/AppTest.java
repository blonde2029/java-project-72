package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    Javalin app;

    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }

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
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
            response.close();
        }));
    }

    @Test
    public void testUrlPage() throws SQLException {
        var url = new Url("https://test", new Timestamp(new Date().getTime()));
        UrlsRepository.save(url);
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains(url.getName());
            response.close();
        }));
    }

    @Test
    public void testCreateUrl() {
        JavalinTest.test(app, ((server, client) -> {
            var requestBody = "url=https://test2";
            var response = client.post("/urls", requestBody);
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

}
