package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UrlCheck {
    private Long id;
    private Timestamp createdAt;
    private String title;
    private String h1;
    private String description;
    private Integer statusCode;

    private Long urlId;

    public UrlCheck(String title, String h1, String description,
                    Integer statusCode, Long urlId) {
        this.createdAt = createdAt;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.statusCode = statusCode;
        this.urlId = urlId;
    }
}
