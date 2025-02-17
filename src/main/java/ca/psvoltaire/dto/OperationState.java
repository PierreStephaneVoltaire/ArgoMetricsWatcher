package ca.psvoltaire.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Data
@Serdeable
public class OperationState {
    String finishedAt;
    String message;
    Operation operation;
    String phase;
    String startedAt;
    SyncResult syncResult;
}
