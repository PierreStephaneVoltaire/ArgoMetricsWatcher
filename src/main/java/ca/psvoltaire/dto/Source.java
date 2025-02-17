package ca.psvoltaire.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Data
@Serdeable
public class Source {
    String path;
    String repoURL;
    String targetRevision;
}
