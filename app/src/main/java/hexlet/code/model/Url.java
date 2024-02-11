package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public final class Url {
    private Long id;
    private String name;
    private Timestamp createdAt;
    private Timestamp lastCheckDate;
    private Integer lastCheckStatus;
    public Url(String name) {
        this.name = name;
    }
}
