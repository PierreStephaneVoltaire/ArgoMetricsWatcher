package ca.psvoltaire.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Data
@Serdeable
public class Destination {
    String namespace;
    String server;
}
