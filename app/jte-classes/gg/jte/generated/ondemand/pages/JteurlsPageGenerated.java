package gg.jte.generated.ondemand.pages;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.UrlsPage;
import hexlet.code.repository.ChecksRepository;
public final class JteurlsPageGenerated {
	public static final String JTE_NAME = "pages/urlsPage.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,4,4,4,5,5,6,6,6,9,9,10,10,10,10,11,11,11,14,14,28,28,32,32,32,35,35,35,35,35,35,35,35,35,35,35,35,38,38,39,43,43,43,43,43,43,44,44,47,47,48,50,50,50,50,51,51,55,55,63,63,63,65};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		var formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <body class=\"d-flex flex-column min-vh-100\">\n    <main class=\"flex-grow-1\">\n        ");
				if (page != null && page.getFlash() != null) {
					jteOutput.writeContent("\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-");
					jteOutput.setContext("div", "class");
					jteOutput.writeUserContent(page.getFlashType());
					jteOutput.setContext("div", null);
					jteOutput.writeContent("\" role=\"alert\">\n            <p class=\"m-0\">");
					jteOutput.setContext("p", null);
					jteOutput.writeUserContent(page.getFlash());
					jteOutput.writeContent("</p>\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n        </div>\n        ");
				}
				jteOutput.writeContent("\n\n        <section>\n            <div class=\"container-lg mt-5\">\n                <h1>Сайты</h1>\n                <table class=\"table table-bordered table-hover mt-3\">\n                    <thead>\n                    <tr>\n                        <th class=\"col-1\">ID</th>\n                        <th>Имя</th>\n                        <th class=\"col-2\">Последняя проверка</th>\n                        <th class=\"col-1\">Код ответа</th>\n                    </tr>\n                    </thead>\n                    ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\n                    <tbody>\n                    <tr>\n                        <td>\n                            ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("\n                        </td>\n                        <td>\n                            <a");
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
					jteOutput.writeContent("</a>\n                        </td>\n                        <td>\n                            ");
					if (ChecksRepository.getLastCheck(url.getId()).isPresent()) {
						jteOutput.writeContent("\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(ChecksRepository.getLastCheck(url.getId())
                                .get()
                                .getCreatedAt()
                                .toLocalDateTime()
                                .format(formatter));
						jteOutput.writeContent("\n                            ");
					}
					jteOutput.writeContent("\n                        </td>\n                        <td>\n                            ");
					if (ChecksRepository.getLastCheck(url.getId()).isPresent()) {
						jteOutput.writeContent("\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(ChecksRepository.getLastCheck(url.getId())
                                .get()
                                .getStatusCode());
						jteOutput.writeContent("\n                            ");
					}
					jteOutput.writeContent("\n                        </td>\n                    </tr>\n                    </tbody>\n                    ");
				}
				jteOutput.writeContent("\n                </table>\n            </div>\n\n        </section>\n    </main>\n    </body>\n\n");
			}
		}, page);
		jteOutput.writeContent("\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
