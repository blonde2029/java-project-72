package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.ChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.InternalServerErrorResponse;
import io.javalin.http.NotFoundResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.jsoup.Jsoup;

import java.sql.SQLException;

public class ChecksController {
    public static void create(Context ctx) throws SQLException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(urlId).orElseThrow(() -> new NotFoundResponse("Entity with id "
                + urlId + " not found"));

        try {
            var response = Unirest.get(url.getName()).asString();
            var statusCode = response.getStatus();
            var body = response.getBody();
            var responsePage = Jsoup.parse(body);
            var title = responsePage.getElementsByTag("title").text();
            var h1 = responsePage.getElementsByTag("h1").text();
            var description = responsePage.select("meta[name=description]").attr("content");
            var urlCheck = new UrlCheck(title, h1, description, statusCode, urlId);
            try {
                ChecksRepository.save(urlCheck);
            } catch (SQLException e) {
                throw new InternalServerErrorResponse();
            }
            ctx.sessionAttribute("flash", "Страница успешно проверена");
            ctx.sessionAttribute("flashType", "success");
        } catch (UnirestException e) {
            ctx.sessionAttribute("flash", "Некорректный адрес");
            ctx.sessionAttribute("flashType", "danger");
        }
        ctx.redirect(NamedRoutes.urlPath(url.getId()));
    }
}
