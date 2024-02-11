package hexlet.code.controller;

import hexlet.code.dto.MainPage;
import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.ChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import java.net.URI;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;

public class UrlsController {

    public static void root(Context ctx) {
        ctx.consumeSessionAttribute("flash");
        ctx.consumeSessionAttribute("flashType");
        ctx.render("pages/mainPage.jte");
    }

    public static void create(Context ctx) throws SQLException {
        var urlPath = ctx.formParam("url");
        try {
            var uri = URI.create(urlPath);
            var name = uri.getScheme() + "://" + uri.getAuthority();
            var url = new Url(name);
            if (uri.getScheme() == null) {
                ctx.sessionAttribute("flash", "Некорректный URL");
                ctx.sessionAttribute("flashType", "danger");
                var page = new MainPage();
                page.setFlash(ctx.consumeSessionAttribute("flash"));
                page.setFlashType(ctx.consumeSessionAttribute("flashType"));
                ctx.render("pages/mainPage.jte", Collections.singletonMap("page", page));
                return;
            }
            if (UrlsRepository.exists(name)) {
                ctx.sessionAttribute("flash", "Страница уже существует");
                ctx.sessionAttribute("flashType", "info");
                ctx.redirect(NamedRoutes.urlsPath());
                return;
            }
            UrlsRepository.save(url);
            ctx.sessionAttribute("flash", "Страница успешно добавлена");
            ctx.sessionAttribute("flashType", "success");
            ctx.redirect(NamedRoutes.urlsPath());
        } catch (ValidationException e) {
            var page = new MainPage(urlPath, e.getErrors());
            ctx.render("pages/mainPage.jte", Collections.singletonMap("page", page));
        }

    }

    public static void index(Context ctx) throws SQLException {
        var urls = UrlsRepository.getEnteties();
        for (Url url : urls) {
            if (ChecksRepository.getLastCheck(url.getId()).isPresent()) {
                url.setLastCheckDate(ChecksRepository.getLastCheck(url.getId()).get().getCreatedAt());
                url.setLastCheckStatus(ChecksRepository.getLastCheck(url.getId()).get().getStatusCode());
            }
        }
        var page = new UrlsPage(urls);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        ctx.render("pages/urlsPage.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(id).orElseThrow(() ->
                new NotFoundResponse("Entity with id " + id + " not found"));
        var checks = ChecksRepository.getEntitiesById(id);
        Collections.sort(checks, Comparator.comparingLong(UrlCheck::getId).reversed());
        var page = new UrlPage(url, checks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        ctx.render("pages/urlPage.jte", Collections.singletonMap("page", page));
    }
}
