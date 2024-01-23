package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UrlCheck {
    private Long id;
    private Timestamp createdAt;
    private String title, h1, description;
    private Integer statusCode;

    private Long urlId;

    public UrlCheck(Timestamp createdAt, String title, String h1, String description,
                    Integer statusCode, Long urlId) {
        this.createdAt = createdAt;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.statusCode = statusCode;
        this.urlId = urlId;
    }
}
