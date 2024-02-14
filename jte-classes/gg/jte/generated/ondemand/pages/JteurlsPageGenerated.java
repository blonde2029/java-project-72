package gg.jte.generated.ondemand.pages;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.UrlsPage;
public final class JteurlsPageGenerated {
	public static final String JTE_NAME = "pages/urlsPage.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,4,4,5,5,5,8,8,9,9,9,9,10,10,10,13,13,27,27,31,31,31,34,34,34,34,34,34,34,34,34,34,34,34,37,37,38,38,38,39,39,42,42,43,43,43,44,44,48,48,55,55,55,56};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		var formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <body class=\"d-flex flex-column min-vh-100\">\r\n    <main class=\"flex-grow-1\">\r\n        ");
				if (page != null && page.getFlash() != null) {
					jteOutput.writeContent("\r\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-");
					jteOutput.setContext("div", "class");
					jteOutput.writeUserContent(page.getFlashType());
					jteOutput.setContext("div", null);
					jteOutput.writeContent("\" role=\"alert\">\r\n            <p class=\"m-0\">");
					jteOutput.setContext("p", null);
					jteOutput.writeUserContent(page.getFlash());
					jteOutput.writeContent("</p>\r\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n        </div>\r\n        ");
				}
				jteOutput.writeContent("\r\n\r\n        <section>\r\n            <div class=\"container-lg mt-5\">\r\n                <h1>Сайты</h1>\r\n                <table class=\"table table-bordered table-hover mt-3\">\r\n                    <thead>\r\n                    <tr>\r\n                        <th class=\"col-1\">ID</th>\r\n                        <th>Имя</th>\r\n                        <th class=\"col-2\">Последняя проверка</th>\r\n                        <th class=\"col-1\">Код ответа</th>\r\n                    </tr>\r\n                    </thead>\r\n                    ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\r\n                    <tbody>\r\n                    <tr>\r\n                        <td>\r\n                            ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("\r\n                        </td>\r\n                        <td>\r\n                            <a");
					var __jte_html_attribute_0 = NamedRoutes.urlPath(url.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a>\r\n                        </td>\r\n                        <td>\r\n                            ");
					if (url.getLastCheckDate() != null) {
						jteOutput.writeContent("\r\n                                ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getLastCheckDate().toLocalDateTime().format(formatter));
						jteOutput.writeContent("\r\n                            ");
					}
					jteOutput.writeContent("\r\n                        </td>\r\n                        <td>\r\n                            ");
					if (url.getLastCheckStatus() != null) {
						jteOutput.writeContent("\r\n                                ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getLastCheckStatus());
						jteOutput.writeContent("\r\n                            ");
					}
					jteOutput.writeContent("\r\n                        </td>\r\n                    </tr>\r\n                    </tbody>\r\n                    ");
				}
				jteOutput.writeContent("\r\n                </table>\r\n            </div>\r\n\r\n        </section>\r\n    </main>\r\n    </body>\r\n");
			}
		}, page);
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
