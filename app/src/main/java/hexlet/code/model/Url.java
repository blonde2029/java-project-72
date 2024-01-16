package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Url {
    private Long id;
    private String name;
    private String createdAt;
}
