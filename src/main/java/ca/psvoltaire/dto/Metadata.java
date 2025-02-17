package ca.psvoltaire.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.util.Map;

@Data
@Serdeable
public class Metadata {
    Map<String, String> annotations;
    String creationTimestamp;
    int generation;
    String name;
    String namespace;
    String resourceVersion;
    String uid;
}
