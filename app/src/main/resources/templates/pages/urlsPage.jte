
@import hexlet.code.util.NamedRoutes
@import hexlet.code.model.Url
@import hexlet.code.dto.UrlsPage

@param Url url
@param UrlsPage page
@template.layout.page(page = page, content = @`
    <body class="d-flex flex-column min-vh-100">
    <main class="flex-grow-1">
        @if (page != null && page.getFlash() != null)
        <div class="rounded-0 m-0 alert alert-dismissible fade show alert-info" role="alert">
            <p class="m-0">${page.getFlash()}</p>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        @endif

        <section>
            <div class="container-lg mt-5">
                <h1>Сайты</h1>
                <table class="table table-bordered table-hover mt-3">
                    <thead>
                    <tr>
                        <th class="col-1">ID</th>
                        <th>Имя</th>
                        <th class="col-2">Последняя проверка</th>
                        <th class="col-1">Код ответа</th>
                    </tr>
                    </thead>
                    @for (var url : page.getUrls())
                    <tbody>
                    <tr>

                        <td>
                            ${url.getId()}
                        </td>
                        <td>
                            <a href="/urls/${url.getId()}">${url.getName()}</a>
                        </td>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>
                    </tbody>
                    @endfor
                </table>
            </div>

        </section>
    </main>
    </body>

`)
