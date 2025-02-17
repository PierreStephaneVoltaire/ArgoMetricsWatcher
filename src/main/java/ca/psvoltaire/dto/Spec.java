package ca.psvoltaire.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Data
@Serdeable
public class Spec {
    Destination destination;
    String project;
    Source source;
    SyncPolicy syncPolicy;
}
