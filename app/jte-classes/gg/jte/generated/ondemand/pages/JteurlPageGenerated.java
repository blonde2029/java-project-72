package gg.jte.generated.ondemand.pages;
import hexlet.code.util.NamedRoutes;;
import hexlet.code.dto.UrlPage;
import java.time.format.DateTimeFormatter;
import java.util.Date;
public final class JteurlPageGenerated {
	public static final String JTE_NAME = "pages/urlPage.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,6,6,7,7,7,10,10,12,12,12,15,15,18,18,18,23,23,23,27,27,27,31,31,31,37,37,37,37,51,51,54,54,54,57,57,57,60,60,60,63,63,63,66,66,66,69,69,69,72,72,80,80,80};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n<body class=\"d-flex flex-column min-vh-100\">\n<main class=\"flex-grow-1\">\n    ");
				if (page != null && page.getFlash() != null) {
					jteOutput.writeContent("\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-info\" role=\"alert\">\n            <p class=\"m-0\">");
					jteOutput.setContext("p", null);
					jteOutput.writeUserContent(page.getFlash());
					jteOutput.writeContent("</p>\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n        </div>\n    ");
				}
				jteOutput.writeContent("\n    <section>\n        <div class=\"container-lg mt-5\">\n            <h1>Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\n            <table class=\"table table-bordered table-hover mt-3\">\n                <tbody>\n                <tr>\n                    <td>ID</td>\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\n                </tr>\n                <tr>\n                    <td>Имя</td>\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\n                </tr>\n                <tr>\n                    <td>Дата создания</td>\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getCreatedAt().toLocalDateTime().format(formatter));
				jteOutput.writeContent("</td>\n                </tr>\n                </tbody>\n            </table>\n\n            <h2 class=\"mt-5\">Проверки</h2>\n            <form method=\"post\" action=\"/urls/");
				jteOutput.setContext("form", "action");
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.setContext("form", null);
				jteOutput.writeContent("/checks\">\n                <button type=\"submit\" class=\"btn btn-primary\">Запустить проверку</button>\n            </form>\n\n            <table class=\"table table-bordered table-hover mt-3\">\n                <thead>\n                <tr><th class=\"col-1\">ID</th>\n                    <th class=\"col-1\">Код ответа</th>\n                    <th>title</th>\n                    <th>h1</th>\n                    <th>description</th>\n                    <th class=\"col-2\">Дата проверки</th>\n                </tr></thead>\n                <tbody>\n                ");
				for (var check : page.getUrlChecks()) {
					jteOutput.writeContent("\n                <tr>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getId());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getStatusCode());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getTitle());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getH1());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getDescription());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getCreatedAt().toLocalDateTime().format(formatter));
					jteOutput.writeContent("\n                    </td>\n                </tr>\n                    ");
				}
				jteOutput.writeContent("\n                </tbody>\n            </table>\n        </div>\n\n    </section>\n</main>\n</body>\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
