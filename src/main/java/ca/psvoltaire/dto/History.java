package ca.psvoltaire.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Data
@Serdeable
public class History {
    String deployStartedAt;
    String deployedAt;
    int id;
    InitiatedBy initiatedBy;
    String revision;
    Source source;
}
