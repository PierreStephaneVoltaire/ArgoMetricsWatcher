package ca.psvoltaire.job;
import ca.psvoltaire.service.ArgoService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.CoreV1Event;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Singleton
public class EventWatcherJob {

    private final ArgoService argoService;

    @Inject
    public EventWatcherJob(ArgoService argoService) {
        this.argoService = argoService;
    }

    @Scheduled(fixedDelay = "180s")
    void logNamespacedEvents() {
        List<String> namespaces = null;
        try {
            namespaces = argoService.getAllNameSpaces();
            log.info("Namespaces in cluster: {}", namespaces);
            for (String namespace : namespaces) {
               for (CoreV1Event event : argoService.getNameSpacedEvents(namespace).getItems()){
                   log.info(event.toString());
               }
            }
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
