package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.controller.UrlsController;
import hexlet.code.repository.BaseRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.sql.SQLException;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import io.javalin.rendering.template.JavalinJte;
import gg.jte.resolve.ResourceCodeResolver;
@Slf4j
public class App {
    public static Javalin getApp() throws IOException, SQLException {
      var hikariConfig = new HikariConfig();
      hikariConfig.setJdbcUrl(getDataBaseUrl());
      var dataSource = new HikariDataSource(hikariConfig);
      var sql = readResourceFile("schema.sql");
      log.info(sql);
      try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
      }
      BaseRepository.dataSource = dataSource;

      var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
      });
      JavalinJte.init(createTemplateEngine());
      app.get(NamedRoutes.rootPath(), ctx -> {
          ctx.consumeSessionAttribute("flash");
          ctx.render("pages/mainPage.jte");
      });
      app.post(NamedRoutes.urlsPath(), UrlsController::create);
      app.get(NamedRoutes.urlsPath(), UrlsController::index);
      app.get(NamedRoutes.urlPath("{id}"), UrlsController::show);
      return app;
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }


    public static String getDataBaseUrl() {
        postgres://postgreesql_project_72_user:bbxOL47b52Bw8eTdPj2evdHxstmMo4JU@dpg-cmibggud3nmc73cii490-a.oregon-postgres.render.com/postgreesql_project_72
        return System.getenv().getOrDefault("JDBC_DATABASE_URL","jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");
    }
    private static String readResourceFile(String fileName) throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();
        app.start(getPort());
    }
}
