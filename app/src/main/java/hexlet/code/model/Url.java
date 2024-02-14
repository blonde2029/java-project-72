package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import hexlet.code.repository.ChecksRepository;

import java.sql.Timestamp;

@Getter
@Setter
public final class Url {
    private Long id;
    private String name;
    private Timestamp createdAt;

    public Url(String name) {
        this.name = name;
    }

    public Integer getLastCheckStatus() {
        if (ChecksRepository.getLastCheck(this.id).isPresent()) {
            return ChecksRepository.getLastCheck(this.id).get().getStatusCode();
        }
        return null;
    }

    public Timestamp getLastCheckDate() {
        if (ChecksRepository.getLastCheck(this.id).isPresent()) {
            return ChecksRepository.getLastCheck(this.id).get().getCreatedAt();
        }
        return null;
    }
}
