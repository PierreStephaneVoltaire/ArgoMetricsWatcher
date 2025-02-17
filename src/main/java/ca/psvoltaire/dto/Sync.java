package ca.psvoltaire.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.util.List;

@Data
@Serdeable
public class Sync {
    boolean prune;
    List<Resource> resources;
    String revision;
}
