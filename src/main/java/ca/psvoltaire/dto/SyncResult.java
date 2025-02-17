package ca.psvoltaire.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.util.List;

@Data
@Serdeable
public class SyncResult {
    List<Resource> resources;
    String revision;
    Source source;
}
