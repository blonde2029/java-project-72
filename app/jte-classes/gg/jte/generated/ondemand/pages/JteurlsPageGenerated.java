package gg.jte.generated.ondemand.pages;
import hexlet.code.util.NamedRoutes;
import hexlet.code.model.Url;
import hexlet.code.dto.UrlsPage;
public final class JteurlsPageGenerated {
	public static final String JTE_NAME = "pages/urlsPage.jte";
	public static final int[] JTE_LINE_INFO = {1,1,2,3,5,5,5,7,7,7,10,10,12,12,12,15,15,29,29,34,34,34,37,37,37,37,37,37,37,45,45,53,53,53,54};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Url url, UrlsPage page) {
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <body class=\"d-flex flex-column min-vh-100\">\n    <main class=\"flex-grow-1\">\n        ");
				if (page != null && page.getFlash() != null) {
					jteOutput.writeContent("\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-info\" role=\"alert\">\n            <p class=\"m-0\">");
					jteOutput.setContext("p", null);
					jteOutput.writeUserContent(page.getFlash());
					jteOutput.writeContent("</p>\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n        </div>\n        ");
				}
				jteOutput.writeContent("\n\n        <section>\n            <div class=\"container-lg mt-5\">\n                <h1>Сайты</h1>\n                <table class=\"table table-bordered table-hover mt-3\">\n                    <thead>\n                    <tr>\n                        <th class=\"col-1\">ID</th>\n                        <th>Имя</th>\n                        <th class=\"col-2\">Последняя проверка</th>\n                        <th class=\"col-1\">Код ответа</th>\n                    </tr>\n                    </thead>\n                    ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\n                    <tbody>\n                    <tr>\n\n                        <td>\n                            ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("\n                        </td>\n                        <td>\n                            <a href=\"/urls/");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(url.getId());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a>\n                        </td>\n                        <td>\n                        </td>\n                        <td>\n                        </td>\n                    </tr>\n                    </tbody>\n                    ");
				}
				jteOutput.writeContent("\n                </table>\n            </div>\n\n        </section>\n    </main>\n    </body>\n\n");
			}
		}, page);
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Url url = (Url)params.get("url");
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, url, page);
	}
}
