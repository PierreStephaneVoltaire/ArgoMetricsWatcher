package ca.psvoltaire.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.util.List;

@Data
@Serdeable
public class Status {
    String controllerNamespace;
    Health health;
    List<History> history;
    OperationState operationState;
    String phase;
    String reconciledAt;
    SyncResult syncResult;
}
