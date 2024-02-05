package hexlet.code.controller;

import hexlet.code.dto.UrlPage;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.ChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;

import java.net.URI;
import java.sql.SQLException;
import java.util.Collections;

public class ChecksController {
    public static void create(Context ctx) throws SQLException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(urlId).orElseThrow(() -> new NotFoundResponse("Entity with id "
                + urlId + " not found"));
        var urlPath = url.getName();
        var uri = URI.create(urlPath);
        if (uri.getHost() == null) {
            ctx.sessionAttribute("flash", "Некорректный адрес");
            ctx.sessionAttribute("flashType", "alert");
            ctx.redirect(NamedRoutes.urlPath(url.getId()));
        } else {
            var response = Unirest.get(urlPath).asString();
            var body = response.getBody();
            var responsePage = Jsoup.parse(body);
            var statusCode = response.getStatus();
            var title = responsePage.getElementsByTag("title").text();
            var h1 = responsePage.getElementsByTag("h1").text();
            String description = responsePage.select("meta[name=description]").attr("content");

            var urlCheck = new UrlCheck(title, h1, description, statusCode, urlId);

            try {
                ChecksRepository.save(urlCheck);
                ctx.sessionAttribute("flash", "Страница успешно проверена");
                ctx.sessionAttribute("flashType", "success");
                ctx.redirect("/urls/" + url.getId());
            } catch (ValidationException e) {
                var checks = ChecksRepository.getEntitiesById(urlId);
                var page = new UrlPage(url, checks);
                ctx.render("pages/urlPage.jte", Collections.singletonMap("page", page));
            }
        }
    }
}
